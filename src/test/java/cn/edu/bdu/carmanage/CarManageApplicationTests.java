package cn.edu.bdu.carmanage;

import cn.edu.bdu.carmanage.utils.Md5;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarManageApplicationTests {

    @Test
    public void test01()
    {
        String password = "abc123";
        String s = Md5.encodeByMD5(password);
        System.out.println(s);
    }

}
