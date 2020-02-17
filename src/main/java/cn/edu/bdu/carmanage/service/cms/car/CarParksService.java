package cn.edu.bdu.carmanage.service.cms.car;

import cn.edu.bdu.carmanage.entity.car.CarParking;
import cn.edu.bdu.carmanage.entity.car.CarParks;
import cn.edu.bdu.carmanage.entity.user.User;
import cn.edu.bdu.carmanage.entity.user.UserVO;
import cn.edu.bdu.carmanage.mapper.CarParkingMapper;
import cn.edu.bdu.carmanage.mapper.CarParksMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author WU
 * @Date 2020/2/11 18:51
 * @Version 1.0
 */
@Service
@Transactional
public class CarParksService {
    @Autowired
    private CarParksMapper carParksMapper;
    @Autowired
    private CarParkingMapper CarParkingMapper;

    public int addCarParks(CarParks carParks) {
        int row = this.carParksMapper.insert(carParks);
        return row;
    }

    public UserVO<CarParks> getCarParksListToUser(String area, String parkNumber, Long currentPage, Long size) {
        QueryWrapper<CarParks> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", '1');
        if (!"".equals(parkNumber) && parkNumber != null) {
            queryWrapper.eq("park_number", parkNumber);
        }
        if (!"".equals(area) && area != null) {
            queryWrapper.eq("area", area);
        }
        queryWrapper.orderByAsc("park_number");
        IPage<CarParks> iPage = new Page<>(currentPage, size);
        IPage<CarParks> page = carParksMapper.selectPage(iPage, queryWrapper);
        UserVO<CarParks> carParksVO = new UserVO<>();
        carParksVO.setPages(page.getPages());
        carParksVO.setCurrent(page.getCurrent());
        carParksVO.setSize(page.getSize());
        carParksVO.setTotal(page.getTotal());
        carParksVO.setList(page.getRecords());
        return carParksVO;

    }

    public UserVO<CarParks> getCarParksList(String area, String parkNumber, Long currentPage, Long size) {
        QueryWrapper<CarParks> queryWrapper = new QueryWrapper<>();
        if (!"".equals(parkNumber) && parkNumber != null) {
            queryWrapper.eq("park_number", parkNumber);
        }
        if (!"".equals(area) && area != null) {
            queryWrapper.eq("area", area);
        }
        queryWrapper.orderByAsc("park_number");
        IPage<CarParks> iPage = new Page<>(currentPage, size);
        IPage<CarParks> page = carParksMapper.selectPage(iPage, queryWrapper);
        UserVO<CarParks> carParksVO = new UserVO<>();
        carParksVO.setPages(page.getPages());
        carParksVO.setCurrent(page.getCurrent());
        carParksVO.setSize(page.getSize());
        carParksVO.setTotal(page.getTotal());
        carParksVO.setList(page.getRecords());
        return carParksVO;

    }

    public CarParks getCarParksById(String id) {

        CarParks carParks = this.carParksMapper.selectById(id);
        return carParks;
    }

    public int updateCarParks(CarParks carParks) {
        int row = this.carParksMapper.updateById(carParks);
        return row;
    }

    public int deleteCarParks(String id) {
        int row = this.carParksMapper.deleteById(id);
        return row;
    }

    public int addCarParing(CarParking carParking) {
        int row = this.CarParkingMapper.insert(carParking);
        CarParks carParks = this.carParksMapper.selectById(carParking.getCarparksId());
        if (carParks != null) {
            carParks.setStatus('0');
            this.carParksMapper.updateById(carParks);
        }
        return row;
    }

    public List<CarParking> getMyCarParking(String userId) {
        List<CarParking> carParkingList = new ArrayList<>();
        List<CarParking> myCarParing = this.CarParkingMapper.getMyCarParing(userId);
        myCarParing.forEach(carParking -> {
            if (carParking.getStartTime() != null && carParking.getEndTime() == null) {
                carParkingList.add(carParking);
            }
        });

        return carParkingList;
    }

    public  UserVO<CarParking> getCarParkings(String area, String parkNumber, String username, String nicheng, Long currentPage, Long size) {
        QueryWrapper<CarParking> queryWrapper = new QueryWrapper<>();
//        Map<String, String> map = new HashMap<>();
        if (!"".equals(area) && area != null) {
//            map.put("area", area);
            queryWrapper.eq("car_carparks.area",area);

        }
        if (!"".equals(parkNumber) && parkNumber != null) {
//            map.put("parkNumber", parkNumber);
            queryWrapper.eq("car_carparks.park_number",parkNumber);
        }
        if (!"".equals(username) && username != null) {
//            map.put("username", username);
            queryWrapper.eq("user_user.username",username);
        }
        if (!"".equals(nicheng) && nicheng != null) {
//            map.put("nicheng", nicheng);
            queryWrapper.eq("user_user.nicheng",nicheng);
        }
        Page<CarParking> page = new Page<>(currentPage, size);
        IPage<CarParking> parkingIPage = this.CarParkingMapper.getCarParkings(page, queryWrapper);
        UserVO<CarParking>carParkingVO = new UserVO<>();
        carParkingVO.setCurrent(parkingIPage.getCurrent());
        carParkingVO.setTotal(parkingIPage.getTotal());
        carParkingVO.setSize(parkingIPage.getSize());
        carParkingVO.setPages(parkingIPage.getPages());
        carParkingVO.setList(parkingIPage.getRecords());
        return carParkingVO;
    }

    public CarParking getCarParkingById(String id) {
        CarParking carParking = this.CarParkingMapper.selectById(id);
        return carParking;
    }
}
