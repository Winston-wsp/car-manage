package cn.edu.bdu.carmanage;

import cn.edu.bdu.carmanage.entity.car.CarParking;
import cn.edu.bdu.carmanage.mapper.CarParkingMapper;
import cn.edu.bdu.carmanage.utils.Md5;
import com.alibaba.fastjson.JSONArray;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CarManageApplicationTests {

    @Autowired
    private CarParkingMapper carParkingMapper;


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
        List<FieldEntity> jsonList = new ArrayList<>();
        String path = "E://abc.xlsx";
        Workbook workbook = new XSSFWorkbook(new FileInputStream(path));
        // 按索引获取
        Sheet sheet = workbook.getSheetAt(0);
        // 行索引row和列索引col都是以 0 起始
        for (int i = 1; i < sheet.getLastRowNum()+1 ; i++) {
            FieldEntity fieldEntity = new FieldEntity();
            Cell cell1 = sheet.getRow(i).getCell(0);
            Cell cell2 = sheet.getRow(i).getCell(1);
            fieldEntity.setKey((int)cell1.getNumericCellValue());
            fieldEntity.setSort(i);
          /* if(i<56){
               fieldEntity.setProjectType("A");
           }else if(i>=56 && i<112  ){
               fieldEntity.setProjectType("B");
           }else {
               fieldEntity.setProjectType("C");
           }*/
            fieldEntity.setValue(cell2.getStringCellValue());
            jsonList.add(fieldEntity);

        }
        String jsonString = JSONArray.toJSONString(jsonList);
        System.out.println(jsonString);

    }

}
