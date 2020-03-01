package cn.edu.bdu.carmanage.service.cms.car;

import cn.edu.bdu.carmanage.entity.car.CarCard;
import cn.edu.bdu.carmanage.entity.car.CarParking;
import cn.edu.bdu.carmanage.entity.car.CarParkingCost;
import cn.edu.bdu.carmanage.entity.car.CarParks;
import cn.edu.bdu.carmanage.entity.user.User;
import cn.edu.bdu.carmanage.entity.user.UserVO;
import cn.edu.bdu.carmanage.mapper.*;
import cn.edu.bdu.carmanage.utils.CardNumberUtils;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

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
    private CarParkingMapper carParkingMapper;
    @Autowired
    private CarCardMapper carCardMapper;
    @Autowired
    private CarParkingCostMapper carParkingCostMapper;
    @Autowired
    private UserMapper userMapper;


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
        CarParks carParks = this.carParksMapper.selectById(id);
        if (carParks.getStatus() == '0') {
            return -2;
        }
        int row = this.carParksMapper.deleteById(id);
        return row;
    }

    public int addCarParing(CarParking carParking) {
        int row = this.carParkingMapper.insert(carParking);
        CarParks carParks = this.carParksMapper.selectById(carParking.getCarparksId());
        if (carParks != null) {
            carParks.setStatus('0');
            this.carParksMapper.updateById(carParks);
        }
        return row;
    }

    public List<CarParking> getMyCarParking(String userId) {
        List<CarParking> carParkingList = new ArrayList<>();
        List<CarParking> myCarParing = this.carParkingMapper.getMyCarParing(userId);
        myCarParing.forEach(carParking -> {
            if (carParking.getStartTime() != null && carParking.getEndTime() == null) {
                carParkingList.add(carParking);
            }
        });

        return carParkingList;
    }

    public UserVO<CarParking> getNotCarParkings(String area, String parkNumber, String username, Long currentPage, Long size) {

        UserVO<CarParking> carParkingVO = this.getCarParkingVO(area, parkNumber, username, currentPage, size, false);

        return carParkingVO;
    }

    public UserVO<CarParking> getCarParkings(String area, String parkNumber, String username, Long currentPage, Long size) {

        UserVO<CarParking> carParkingVO = this.getCarParkingVO(area, parkNumber, username, currentPage, size, true);

        return carParkingVO;
    }

    /*
    查找在场车辆和历史车辆中的共同方法
     */
    public UserVO<CarParking> getCarParkingVO(String area, String parkNumber, String username, Long currentPage, Long size, boolean flag) {
        QueryWrapper<CarParking> queryWrapper =
                new QueryWrapper<>();
        if (!StringUtils.isEmpty(area)) {

            queryWrapper.eq("car_carparks.area", area);

        }
        if (!StringUtils.isEmpty(parkNumber)) {

            queryWrapper.eq("car_carparks.park_number", parkNumber);
        }
        if (!StringUtils.isEmpty(username)) {

            queryWrapper.eq("user_user.username", username);
        }

        if(flag == true){
            queryWrapper.isNull("end_time");
        }else {
            queryWrapper.isNotNull("end_time");
        }
        queryWrapper.orderByAsc("car_parking.start_time");
        Page<CarParking> page = new Page<>(currentPage, size);
        IPage<CarParking> parkingIPage = this.carParkingMapper.getCarParkings(page, queryWrapper);
        UserVO<CarParking> carParkingVO = new UserVO<>();
        carParkingVO.setCurrent(parkingIPage.getCurrent());
        carParkingVO.setTotal(parkingIPage.getTotal());
        carParkingVO.setSize(parkingIPage.getSize());
        carParkingVO.setPages(parkingIPage.getPages());
        carParkingVO.setList(parkingIPage.getRecords());

        return carParkingVO;
    }


    public CarParking getCarParkingById(String id) {
        CarParking carParking = this.carParkingMapper.selectById(id);
        return carParking;
    }

    public int addCarCard(String userId, Integer type) {
        int row = -1;
        Date date = DateUtil.date();

        // 如果用户在办卡的时候有车未出库，则办卡失败
        List<CarParking> carParkings = this.carParkingMapper.getMyCarParing(userId);
        for (CarParking carParking : carParkings) {
            if (carParking.getStartTime() != null && carParking.getEndTime() == null) {
                return row;
            }
        }

        QueryWrapper<CarCard> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        CarCard carCard = this.carCardMapper.selectOne(queryWrapper);
        // 如果用户之前没有办理过卡
        if (carCard == null) {
            CarCard card = new CarCard();
            card.setUserId(userId);
            card.setStartTime(date);
            card.setCardNumber("ZZ" + CardNumberUtils.getCardNumber());
            if (type == 1) {
                // 办理的是月卡
                card.setCardType(1);
                card.setEndTime(DateUtil.nextMonth());
            } else if (type == 2) {
                // 办理的是年卡
                card.setCardType(2);
                card.setEndTime(DateUtil.offsetMonth(new Date(), 12));
            }
            row = this.carCardMapper.insert(card);
            return row;

        }

        // 如果曾经办理过
        // 1.看看之前办理的卡是否已经过期
        Date startTime = carCard.getStartTime();
        Date endTime = carCard.getEndTime();
        Date now = new Date();
        if (DateUtil.isExpired(startTime, endTime, now)) {
            // 如果已经过期，则重置开始和结束时间
            carCard.setStartTime(now);
            if (type == 1) {
                // 办理的是月卡
                carCard.setCardType(1);
                carCard.setEndTime(DateUtil.nextMonth());
            } else if (type == 2) {
                // 办理的是年卡
                carCard.setCardType(2);
                carCard.setEndTime(DateUtil.offsetMonth(new Date(), 12));
            }
            row = this.carCardMapper.updateById(carCard);
        } else {
            // 如果没有过期则往后继续添加时间
            if (type == 1) {
                // 办理的是月卡
                carCard.setCardType(1);
                carCard.setEndTime(DateUtil.offsetMonth(carCard.getEndTime(), 1));
            } else if (type == 2) {
                // 办理的是年卡
                carCard.setCardType(2);
                carCard.setEndTime(DateUtil.offsetMonth(carCard.getEndTime(), 12));
            }
            row = this.carCardMapper.updateById(carCard);
        }

        return row;
    }

    /**
     * 根据用户id查找卡号类型
     *
     * @param userId
     * @return
     */
    public Long getCarCardByUserId(String userId, Date endTime) {
        QueryWrapper<CarCard> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        CarCard carCard = this.carCardMapper.selectOne(queryWrapper);

        // 用户车辆所在的车位，用户可以停多辆
        List<CarParking> carParkings = new ArrayList<>();
        List<CarParking> carParkingList = this.carParkingMapper.getMyCarParing(userId);
        carParkingList.forEach(carParking -> {
            if (carParking.getStartTime() != null && carParking.getEndTime() == null) {
                carParkings.add(carParking);
            }
        });
        if (carCard == null) {
            // 不存在卡号，是普通用户
            return -1l;
        }
        CarParking carParking = carParkings.get(0);
        // 获取用户入库的时间
        Date startTime = carParking.getStartTime();
        // 卡的有效期
        Date cardStartTime = carCard.getStartTime();
        Date cardEndTime = carCard.getEndTime();
        if (DateUtil.isExpired(cardStartTime, cardEndTime, startTime)) {
            // 会员卡已经过期
            return -1l;
        }
        // 如果会员卡没过期
        // 1.入库时间在有效期，出库时间不在有效期
        if (DateUtil.isExpired(cardStartTime, cardEndTime, endTime)) {
            // 计算过期日期到出库时间的毫秒数
            long between = DateUtil.between(cardEndTime, endTime, DateUnit.MS);
            System.out.println(between);
            return between;
        }


        return 1l;
    }

    public int updateCarParkingById(String id, Date outTime, Double money, String context) {
        CarParking carParking = this.carParkingMapper.selectById(id);
        carParking.setEndTime(outTime);
        int row = this.carParkingMapper.updateById(carParking);

        if (row > 0) {
            // 出库后车位状态要置为未使用状态
            // 查询车位号
            String carparksId = carParking.getCarparksId();
            CarParks carParks = this.carParksMapper.selectById(carparksId);
            carParks.setStatus('1');
            row = this.carParksMapper.updateById(carParks);

        }
        return row;
    }

    // 获取所有车位
    public Integer getAllCarParks() {
        return this.carParksMapper.selectCount(null);
    }

    // 获取空闲车位
    public Integer getEmptyCarParks() {
        QueryWrapper<CarParks> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        Integer count = this.carParksMapper.selectCount(queryWrapper);
        return count;
    }

    // 获取价格区间
    public CarParkingCost getCarParkingCost() {
        List<CarParkingCost> carParkingCosts = this.carParkingCostMapper.selectList(null);
        CarParkingCost carParkingCost = carParkingCosts.get(0);

        return carParkingCost;
    }

    /**
     * 管理员添加入库车辆信息
     * @param carParksId
     * @param username
     * @param plateNumber
     * @return
     */
    public int addCarParking(String carParksId, String username, String plateNumber) {

        // 首先确定用户是否存在
        User user = this.userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if(user == null){
            return -2;
        }
        CarParking carParking = new CarParking();
        carParking.setUserId(user.getId());
        carParking.setCarparksId(carParksId);
        carParking.setPlateNumber(plateNumber);
        carParking.setStartTime(new Date());

        int row = this.carParkingMapper.insert(carParking);
        CarParks carParks = this.carParksMapper.selectById(carParksId);
        if (carParks != null) {
            carParks.setStatus('0');
            row = this.carParksMapper.updateById(carParks);
        }
        return row;
    }

    /**
     * 设定停车费用
     * @param carParkingCost
     * @return
     */
    public int setCarParkingCost(CarParkingCost carParkingCost) {
        int row = this.carParkingCostMapper.updateById(carParkingCost);
        return row;
    }
}
