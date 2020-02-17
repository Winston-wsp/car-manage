package cn.edu.bdu.carmanage;

import cn.edu.bdu.carmanage.entity.car.CarParking;
import cn.edu.bdu.carmanage.mapper.CarParkingMapper;
import cn.edu.bdu.carmanage.service.cms.car.CarParksService;
import cn.edu.bdu.carmanage.utils.Md5;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CarManageApplicationTests {

    @Autowired
    private CarParkingMapper carParkingMapper;


    @Test
    public void test01()
    {
        String password = "abc123";
        String s = Md5.encodeByMD5(password);
        System.out.println(s);
    }

    @Test
    public void test02()
    {
        List<CarParking> list = carParkingMapper.getMyCarParing("4d3e6c9cbec284d6c5bc1311923e68f0'");
        list.forEach(carParking -> {
            System.out.println(carParking );
        });
    }

}
