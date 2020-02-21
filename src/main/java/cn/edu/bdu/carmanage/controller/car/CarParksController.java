package cn.edu.bdu.carmanage.controller.car;

import cn.edu.bdu.carmanage.entity.car.CarParking;
import cn.edu.bdu.carmanage.entity.car.CarParks;
import cn.edu.bdu.carmanage.entity.user.User;
import cn.edu.bdu.carmanage.entity.user.UserPay;
import cn.edu.bdu.carmanage.entity.user.UserVO;
import cn.edu.bdu.carmanage.service.cms.car.CarParksService;
import cn.edu.bdu.carmanage.service.cms.user.UserService;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
    @Autowired
    private UserService userService;


    @PostMapping("/addCarParks")
    @ResponseBody
    public int addCarParks(CarParks carParks) {
        int row = carParksService.addCarParks(carParks);
        return row;
    }

    @GetMapping("/getCarParksList")
    public String getCarParksList(@RequestParam(value = "area", required = false, defaultValue = "") String area, @RequestParam(value = "parkNumber", required = false, defaultValue = "") String parkNumber, @RequestParam(value = "currentPage", defaultValue = "1") Long currentPage, @RequestParam(value = "size", defaultValue = "8") Long size, Model model) {
        model.addAttribute("area", area);
        model.addAttribute("parkNumber", parkNumber);
        UserVO<CarParks> carParksVO = carParksService.getCarParksList(area, parkNumber, currentPage, size);
        model.addAttribute("carParksVO", carParksVO);
        return "/manage/carparks";
    }

    @GetMapping("/getCarParksListToUser")
    public String getCarParksListToUser(@RequestParam(value = "area", required = false, defaultValue = "") String area, @RequestParam(value = "parkNumber", required = false, defaultValue = "") String parkNumber, @RequestParam(value = "currentPage", defaultValue = "1") Long currentPage, @RequestParam(value = "size", defaultValue = "8") Long size, Model model) {

        model.addAttribute("area", area);
        model.addAttribute("parkNumber", parkNumber);
        UserVO<CarParks> carParksVO = carParksService.getCarParksListToUser(area, parkNumber, currentPage, size);
        model.addAttribute("carParksVO", carParksVO);
//        model.addAttribute("userId", userId);
        return "/user/carparks";
    }

    @GetMapping("/getCarParksListToAdmin")
    public String getCarParksListToAdmin(@RequestParam(value = "area", required = false, defaultValue = "") String area, @RequestParam(value = "parkNumber", required = false, defaultValue = "") String parkNumber, @RequestParam(value = "currentPage", defaultValue = "1") Long currentPage, @RequestParam(value = "size", defaultValue = "8") Long size, Model model) {
        UserVO<CarParks> carParksVO = carParksService.getCarParksListToUser(area, parkNumber, currentPage, size);
        model.addAttribute("carParksVO", carParksVO);
        return "/manage/carstop";
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
     * 管理员获取历史车辆的信息
     *
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
                                 @RequestParam(value = "userId", required = false) String userId,
                                 @RequestParam(value = "currentPage", defaultValue = "1") Long currentPage,
                                 @RequestParam(value = "size", defaultValue = "8") Long size,
                                 Model model) {
        UserVO<CarParking> carParkingVO = this.carParksService.getCarParkings(area, parkNumber, username, currentPage, size);
        model.addAttribute("carParkingVO", carParkingVO);
        return "/manage/carparking";
    }

    @GetMapping("/getCarParkingById/{id}")
    @ResponseBody
    public CarParking getCarParkingById(@PathVariable("id") String id) {
        CarParking carParking = this.carParksService.getCarParkingById(id);
        return carParking;
    }

    /**
     * 根据id，更新出库时间
     *
     * @param id
     * @return
     */
    @PutMapping("/updateCarParkingById/{carparkingId}")
    @ResponseBody
    public int updateCarParkingById(@PathVariable("carparkingId") String id, @RequestParam("outTime") Date outTime, @RequestParam("money") Double money, @RequestParam("context") String context) {
        CarParking carParking = this.carParksService.getCarParkingById(id);
        if (money > 0) {
            int row = this.userService.userConsume(carParking.getUserId(), money);
            if (row < 0) {
                return row;
            }
        }
        int result = this.carParksService.updateCarParkingById(id, outTime, money, context);

        // 生成缴费账单
        if (result > 0) {
            result = this.userService.userConsume(carParking.getUserId(), money);
            if (result > 0 && money > 0) {
                this.userService.addPayOrder(carParking.getUserId(), context, -money);
            }
        }
        return result;
    }

    @PostMapping("/addCarCard/{userId}")
    @ResponseBody
    public int addCarCard(@PathVariable("userId") String userId, @RequestParam("type") int type, @RequestParam("money") Double money, @RequestParam("context") String context) {
        int row = this.carParksService.addCarCard(userId, type);
        if (row > 0) {
            User user = this.userService.getUserById(userId);
            row = this.userService.userConsume(userId, money);
            if (row > 0) {
                this.userService.addPayOrder(userId, context, -money);
            }
        }
        return row;
    }

    /**
     * 根据用户id查找账号类型
     *
     * @param userId
     * @return
     */
    @GetMapping("/getCarCardByUserId/{userId}")
    @ResponseBody
    public Long getCarCardByUserId(@PathVariable("userId") String userId, @RequestParam("endTime") Date endTime) {
        Long result = this.carParksService.getCarCardByUserId(userId, endTime);
        return result;
    }

}
