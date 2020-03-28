package cn.edu.bdu.carmanage.util;

import cn.edu.bdu.carmanage.User;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * @description:
 * @author: Winston
 * @createTime: 2020/3/27 21:30
 */
@Component
@Slf4j
public class ExcelTool {
    /**
     * @author yy
     * @version 2.0
     * @date 2019/7/30 16:27
     * @deprecated 版本升级为对应的2.0.0以上
     * 性能更加高效  导出数据更加稳定
     * 支持 64M内存1分钟内读取75M(46W行25列)
     **/
    public static final Logger LOGGER = LoggerFactory.getLogger(ExcelTool.class);

    /**
     * 导出 Excel ：一个 sheet，带表头.
     *
     * @param response  HttpServletResponse
     * @param data      数据 list，每个元素为一个 BaseRowModel
     * @param fileName  导出的文件名
     * @param sheetName 导入文件的 sheet 名
     * @param model     映射实体类，Excel 模型
     * @throws Exception 异常
     */


    public void writeExcel(HttpServletResponse response, List<? extends Object> data,
                           String fileName, String sheetName, Class model) throws Exception {

        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //设置表头居中对齐
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //设置内容靠左对齐
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(getOutputStream(fileName, response), model)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet(sheetName)
                .registerWriteHandler(horizontalCellStyleStrategy)
                //最大长度自适应 目前没有对应算法优化 建议注释掉不用 会出bug
//                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .doWrite(data);

    }


    /**
     * 导出文件时为Writer生成OutputStream.
     *
     * @param fileName 文件名
     * @param response response
     * @return ""
     */
    private OutputStream getOutputStream(String fileName,
                                         HttpServletResponse response) throws Exception {
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "max-age=0");
            return response.getOutputStream();
        } catch (IOException e) {
            throw new Exception("导出excel表格失败!", e);
        }
    }

    /**
     * 简单的读
     *
     * @param fileName
     * @param clazz
     * @return
     */
    public List<?> simpleRead(String fileName, Class clazz) {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, clazz, new DataListener()).sheet().doRead();
        return new DataListener().saveData();
    }

    /**
     * ***由于本身listener类没有归spring管理 所以无法获取容器内部的bean实例***
     */
    class DataListener extends AnalysisEventListener<User> {
        /**
         * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
         */
        private final int BATCH_COUNT = 10;
        private List<User> list = new ArrayList<>();

        public List<User> getList() {
            return list;
        }

        /**
         * 这个每一条数据解析都会来调用
         *
         * @param data    one row value. It is same as {@link AnalysisContext#readRowHolder()}
         * @param context
         */
        @Override
        public void invoke(User data, AnalysisContext context) {
            LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
            list.add(data);
            // 达到BATCH_COUNT了，就需要对list进行清空，防止数据几万条数据在内存，容易OOM
            if (list.size() >= BATCH_COUNT) {
                saveData();
                // 存储完成清理 list
                list.clear();
            }
        }

        /**
         * 所有数据解析完成了 都会来调用
         *
         * @param context
         */
        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            // 这里也要保存数据，确保最后遗留的数据也存储到数据库
//            saveData();
            LOGGER.info("所有数据解析完成！");
        }

        /**
         * 加上存储数据库
         */
        private List<?> saveData() {
            LOGGER.info("{}条数据，开始存储数据库！", list.size());
            return list;
        }
    }
}
