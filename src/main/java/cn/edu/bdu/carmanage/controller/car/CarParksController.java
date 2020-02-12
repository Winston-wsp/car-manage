package cn.edu.bdu.carmanage.controller.car;

import cn.edu.bdu.carmanage.entity.car.CarParks;
import cn.edu.bdu.carmanage.entity.user.UserVO;
import cn.edu.bdu.carmanage.service.cms.car.CarParksService;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String getCarParksList(@RequestParam(value = "currentPage", defaultValue = "1") Long currentPage, @RequestParam(value = "size", defaultValue = "8") Long size, Model model) {
        UserVO<CarParks> carParksVO = carParksService.getCarParksList(currentPage, size);
        model.addAttribute("carParksVO", carParksVO);
        return "/manage/carparks";
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
    public int deleteCarParks(@PathVariable("id") String id){
        int row = this.carParksService.deleteCarParks(id);
        return row;
    }
}
