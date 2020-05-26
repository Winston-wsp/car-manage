package cn.edu.bdu.carmanage;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.edu.bdu.carmanage.entity.car.CarParking;
import cn.edu.bdu.carmanage.entity.sms.SmsModel;
import cn.edu.bdu.carmanage.listener.ExcelOptionsService;
import cn.edu.bdu.carmanage.mapper.CarParkingMapper;
import cn.edu.bdu.carmanage.service.cms.sms.SendSmsService;
import cn.edu.bdu.carmanage.util.ExcelTool;
import cn.edu.bdu.carmanage.utils.Md5;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class CarManageApplicationTests {

    @Autowired
    private CarParkingMapper carParkingMapper;
    @Autowired
    private ExcelOptionsService excelOptionsService;
    @Autowired
    private SendSmsService sendSmsService;
    @Test
    public void test01() {
        String password = "abc123";
        String s = Md5.encodeByMD5(password);
        System.out.println(s);
    }

    @Test
    public void test02() {
        List<CarParking> list = carParkingMapper.getMyCarParing("4d3e6c9cbec284d6c5bc1311923e68f0'");
        list.forEach(carParking -> {
            System.out.println(carParking);
        });
    }

    @Test
    public void test04() throws IOException {
        // FieldEntity是我用来接收Excel数据的对象
        List<FieldEntity> jsonList = new ArrayList<>();
        String path = "E://abc.xlsx";
        File file = new File("C:\\Users\\WU\\Desktop\\6a7ecfe2d66c424183911d2ae44ed75a (1).xlsx");
        // XSSFWorkbook只能读取.xlsx结尾的表格
        Workbook workbook = new XSSFWorkbook(new FileInputStream(file));
        // 按索引获取
        Sheet sheet = workbook.getSheetAt(0);
        // 行索引row和列索引col都是以 0 起始
        for (int i = 2; i < sheet.getLastRowNum() + 1; i++) {
            FieldEntity fieldEntity = new FieldEntity();
            Cell cell1 = sheet.getRow(i).getCell(0);
            Cell cell2 = sheet.getRow(i).getCell(1);
            Cell cell3 = sheet.getRow(i).getCell(2);
            Cell cell4 = sheet.getRow(i).getCell(3);
            Cell cell5 = sheet.getRow(i).getCell(4);
            Cell cell6 = sheet.getRow(i).getCell(5);
            Cell cell7 = sheet.getRow(i).getCell(6);
            jsonList.add(fieldEntity);

        }
        String jsonString = JSONArray.toJSONString(jsonList);
        System.out.println(jsonString);

    }

    @Test
    public void test05() throws Exception {
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(2);
        importParams.setHeadRows(1);
        //test03241703 (1)(1).xlsx
        File file = new File("C:\\Users\\WU\\Desktop\\test03241703 (1)(1).xlsx");
        List<FieldEntity> list = ExcelImportUtil.importExcel(new FileInputStream(file), FieldEntity.class, importParams);
        System.out.println(list);
    }

    @Test
    public void test06() {
        File file = new File("C:\\Users\\WU\\Desktop\\test03241703 (1)(1).xlsx");

        List<List<String>> result = new ArrayList<>();
        try {
            result = excelOptionsService.writeWithoutHead(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<FieldEntity> fieldEntityList = new ArrayList<>();
        result.forEach(list -> {
            FieldEntity fieldEntity = new FieldEntity();
            fieldEntity.setMobile(list.get(0));
            fieldEntity.setFullName(list.get(1));
            fieldEntity.setUserAuthority(list.get(2));
            fieldEntity.setIdNum(list.get(3));
            fieldEntity.setUserGroup(list.get(4));
            fieldEntity.setUserAuthority(list.get(5));
            fieldEntity.setEmployeeNumber(list.get(6));

            fieldEntityList.add(fieldEntity);
        });

        System.out.println("读取结果：" + result);

    }

    @Test
    public void test6() {
        File file = new File("C:\\Users\\WU\\Desktop\\test03241703 (1)(1).xlsx");

        ExcelTool excelTool = new ExcelTool();
        List<?> objects = excelTool.simpleRead("C:\\Users\\WU\\Desktop\\test03241703 (1)(1).xlsx", User.class);
        System.out.println(objects);
    }

    @Test
    public void test7() {
        List<Map<String, String>> books = new ArrayList<>();
        Map<String, String> info = new HashMap<>();
        info.put("name", "骆驼祥子");
        info.put("price", "25.00");
        info.put("author", "老舍");

        Map<String, String> info1 = new HashMap<>();
        info1.put("name", "骆驼祥子1");
        info1.put("price", "25.00");
        info1.put("author", "老舍1");

        Map<String, String> info2 = new HashMap<>();
        info2.put("name", "骆驼祥子2");
        info2.put("price", "25.00");
        info2.put("author", "老舍2");

        Map<String, String> info3 = new HashMap<>();
        info3.put("name", "骆驼祥子3");
        info3.put("price", "25.00");
        info3.put("author", "老舍3");

        books.add(info);
        books.add(info1);
        books.add(info2);
        books.add(info3);

        List<String> name = books.stream().map(map -> {
            return map.get("name");
        }).collect(Collectors.toList());
        System.out.println(name);

    }

    @Test
    public void test8() {
        List<String> strList = new ArrayList<String>();
        strList.add("a");
        strList.add("b");
//        strList.add("c");
//        strList.add("d");
        List<String> strList2 = new ArrayList<String>();
//        strList2.add("b");
        strList2.add("c");
        strList2.add("d");
        strList2.add("e");

        strList.retainAll(strList2);
        System.out.println(strList);

    }

    @Test
    public void test9() {
        DateTime parse = DateUtil.parse("2020-04-01");
        Timestamp timestamp = parse.toTimestamp();

        DateTime date1 = DateUtil.date(1587475023538L);
        DateTime date2 = DateUtil.date(1611235023000L);
        String name = date1.monthEnum().name();
        int year = date2.year() - date1.year();
        int month = date2.month() - date1.month();
        int result = Math.abs(month + year * 12);
        DateTime dateTime = DateUtil.offsetMonth(date1, 1);
        System.out.println();
    }

    @Test
    public void test10() {
        DateTime start = DateUtil.date();
        DateTime end = DateUtil.offsetDay(start, -1);

        String fromDate = DateUtil.format(start, "yyyy/MM/dd");
        String toDate = DateUtil.format(end, "yyyy/MM/dd");
        System.out.println();
    }

    @Test
    public void test11() {
        DateTime date = DateUtil.date(1121321312);
        DateUtil.format(date, "yyyy-MM-dd");
        System.out.println();

    }

    @Test
    public void test12() {
        String account = "6217000140012931001";
        String s = hideData(account, 4, 4);
        System.out.println(s);

    }

    /**
     * 对重要数据进行隐藏处理
     *
     * @param data         //要隐藏的数据
     * @param beforeLength //前面保留位数
     * @param afterLength  //后面保留位数
     * @return
     */
    public static String hideData(String data, int beforeLength, int afterLength) {
        if (StringUtils.isBlank(data)) {
            return data;
        }
        int length = data.length();
        //替换字符串，当前使用“*”
        String replaceSymbol = "*";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            if (i >= length - afterLength || i < beforeLength) {
                sb.append(data.charAt(i));
            } else {
                sb.append(replaceSymbol);
            }
        }
        return sb.toString();
    }

    @Test
    public void test13()
    {
        SmsModel smsModel = new SmsModel();
        smsModel.setMobile("17749707027");
        smsModel.setTemplateCode("您的验证码为：${code}，该验证码5分钟内有效，请勿泄漏于他人！");
        smsModel.setTemplateParam("135566");
        smsModel.setTemplateParam(null);
//        sendSmsService.send(smsModel);
    }

    @Test
    public void test14()
    {
        String id = "https://www.bilibili.com/video/BV15741177Eh?p=";

        for (int i = 1; i < 232; i++) {
            System.out.println(id+i);
        }
    }
}
