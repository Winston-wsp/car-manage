package cn.edu.bdu.carmanage.controller.user;

import cn.edu.bdu.carmanage.entity.car.CarParkingCost;
import cn.edu.bdu.carmanage.service.cms.car.CarParksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author WU
 * @Date 2020/2/24 11:25
 * @Version 1.0
 */
@Controller
@RequestMapping("user/car")
public class UserCarController {

    @Autowired
    private CarParksService carParksService;


    @GetMapping("/getCarParkingCostRule")
    @ResponseBody
    public CarParkingCost getCarParkingCostRule(){
        CarParkingCost carParkingCost = carParksService.getCarParkingCost();
        return carParkingCost;
    }

}
