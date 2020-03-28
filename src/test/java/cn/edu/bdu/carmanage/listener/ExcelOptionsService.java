package cn.edu.bdu.carmanage.listener;

import cn.edu.bdu.carmanage.FieldEntity;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * @description:
 * @author: Winston
 * @createTime: 2020/3/27 9:05
 */
@Service
public class ExcelOptionsService {

    /**
     * 根据excel输入流，读取excel文件
     *
     * @param inputStream exece表格的输入流
     * @return 返回双重list的集合
     **/
    public List<List<String>> writeWithoutHead(InputStream inputStream) {
        StringExcelListener listener = new StringExcelListener();
        ExcelReader excelReader = EasyExcelFactory.read(inputStream, null, listener).headRowNumber(2).build();
        excelReader.read();
        List<List<String>> datas = listener.getDatas();
        excelReader.finish();
        return datas;
    }
}
