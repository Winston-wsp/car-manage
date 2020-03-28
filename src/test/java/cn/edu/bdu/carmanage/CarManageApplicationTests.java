package cn.edu.bdu.carmanage;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.edu.bdu.carmanage.entity.car.CarParking;
import cn.edu.bdu.carmanage.listener.ExcelOptionsService;
import cn.edu.bdu.carmanage.mapper.CarParkingMapper;
import cn.edu.bdu.carmanage.util.ExcelTool;
import cn.edu.bdu.carmanage.utils.Md5;
import com.alibaba.fastjson.JSONArray;
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
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CarManageApplicationTests {

    @Autowired
    private CarParkingMapper carParkingMapper;
    @Autowired
    private ExcelOptionsService excelOptionsService;

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

}
