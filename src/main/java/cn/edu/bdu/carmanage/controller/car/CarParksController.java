package cn.edu.bdu.carmanage.controller.car;

import cn.edu.bdu.carmanage.entity.car.CarParking;
import cn.edu.bdu.carmanage.entity.car.CarParks;
import cn.edu.bdu.carmanage.entity.user.UserVO;
import cn.edu.bdu.carmanage.service.cms.car.CarParksService;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @Author WU
 * @Date 2020/2/11 18:36
 * @Version 1.0
 */
@Controller
@RequestMapping("/car")
public class CarParksController {

    @Autowired
    private CarParksService carParksService;


    @PostMapping("/addCarParks")
    @ResponseBody
    public int addCarParks(CarParks carParks) {
        int row = carParksService.addCarParks(carParks);
        return row;
    }

    @GetMapping("/getCarParksList")
    public String getCarParksList(@RequestParam(value = "area", required = false, defaultValue = "") String area, @RequestParam(value = "parkNumber", required = false, defaultValue = "") String parkNumber, @RequestParam(value = "currentPage", defaultValue = "1") Long currentPage, @RequestParam(value = "size", defaultValue = "8") Long size, Model model) {
        UserVO<CarParks> carParksVO = carParksService.getCarParksList(area, parkNumber, currentPage, size);
        model.addAttribute("carParksVO", carParksVO);
        return "/manage/carparks";
    }

    @GetMapping("/getCarParksListToUser")
    public String getCarParksListToUser(@RequestParam(value = "area", required = false, defaultValue = "") String area, @RequestParam(value = "parkNumber", required = false, defaultValue = "") String parkNumber, @RequestParam("userId") String userId, @RequestParam(value = "currentPage", defaultValue = "1") Long currentPage, @RequestParam(value = "size", defaultValue = "8") Long size, Model model) {
        UserVO<CarParks> carParksVO = carParksService.getCarParksListToUser(area, parkNumber, currentPage, size);
        model.addAttribute("carParksVO", carParksVO);
        model.addAttribute("userId", userId);
        return "/user/carparks";
    }

    @GetMapping("/getCarParksById/{id}")
    @ResponseBody
    public ResponseEntity<CarParks> getCarParksById(@PathVariable("id") String id) {
        CarParks carParks = this.carParksService.getCarParksById(id);
        if (carParks == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carParks);
    }

    @PutMapping("/updateCarParks")
    @ResponseBody
    public int updateCarParks(CarParks carParks) {
        int row = this.carParksService.updateCarParks(carParks);
        return row;
    }

    @DeleteMapping("/deleteCarParks/{id}")
    @ResponseBody
    public int deleteCarParks(@PathVariable("id") String id) {
        int row = this.carParksService.deleteCarParks(id);
        return row;
    }


    /*
        新增停车车辆
     */
    @PostMapping("/addCarParing")
    @ResponseBody
    public int addCarParing(CarParking carParking) {
        carParking.setStartTime(new Date());
        int row = this.carParksService.addCarParing(carParking);
        return row;
    }

    /**
     * 用户查询自己的在场车辆
     *
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("/getMyCarParking")
    public String getMyCarParking(@RequestParam("userId") String userId, Model model) {
        List<CarParking> carParkingList = this.carParksService.getMyCarParking(userId);
        if (carParkingList != null) {
            model.addAttribute("carParkingList", carParkingList);
        }
        return "/user/mycarparking";
    }

    /**
     * 管理员获取在场车辆的信息
     * @param area
     * @param parkNumber
     * @param username
     * @param nicheng
     * @param userId
     * @param currentPage
     * @param size
     * @param model
     * @return
     */
    @GetMapping("/getCarParkings")
    public String getCarParkings(@RequestParam(value = "area", required = false, defaultValue = "") String area,
                                 @RequestParam(value = "parkNumber", required = false, defaultValue = "") String parkNumber,
                                 @RequestParam(value = "username", required = false, defaultValue = "") String username,
                                 @RequestParam(value = "nicheng", required = false, defaultValue = "") String nicheng,
                                 @RequestParam(value = "userId", required = false) String userId,
                                 @RequestParam(value = "currentPage", defaultValue = "1") Long currentPage,
                                 @RequestParam(value = "size", defaultValue = "8") Long size,
                                 Model model) {
        UserVO<CarParking> carParkingVO = this.carParksService.getCarParkings(area, parkNumber, username, nicheng, currentPage, size);
        model.addAttribute("carParkingVO", carParkingVO);
        return "/manage/carparking";
    }

    @GetMapping("/getCarParkingById/{id}")
    @ResponseBody
    public CarParking getCarParkingById(@PathVariable("id") String id){
        CarParking carParking = this.carParksService.getCarParkingById(id);
        return carParking;
    }

}
