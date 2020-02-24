package cn.edu.bdu.carmanage.controller.adminuser;

import cn.edu.bdu.carmanage.entity.car.CarParkingCost;
import cn.edu.bdu.carmanage.service.cms.car.CarParksService;
import cn.edu.bdu.carmanage.service.cms.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Author WU
 * @Date 2020/2/23 21:13
 * @Version 1.0
 */
@Controller
@RequestMapping("admin/car")
public class AdminCarController {

    @Autowired
    private UserService userService;
    @Autowired
    private CarParksService carParksService;

    /**
     * 管理员添加入库车辆信息
     *
     * @param carParksId
     * @param username
     * @param plateNumber
     * @return
     */
    @PostMapping("/addCarParking/{id}")
    @ResponseBody
    public int addCarParking(@PathVariable("id") String carParksId,
                             @RequestParam("username") String username,
                             @RequestParam("plateNumber") String plateNumber) {
        int row = this.carParksService.addCarParking(carParksId, username, plateNumber);

        return row;
    }

    @GetMapping("/getCarParkingCost")
    public String getCarParkingCost(Model model) {
        CarParkingCost carParkingCost = carParksService.getCarParkingCost();
        model.addAttribute("carParkingCost", carParkingCost);
        return "/manage/carParkingCost";
    }

    @PutMapping("/setCarParkingCost")
    @ResponseBody
    public int setCarParkingCost(CarParkingCost carParkingCost){
        int row = this.carParksService.setCarParkingCost(carParkingCost);
        return row;
    }

}
