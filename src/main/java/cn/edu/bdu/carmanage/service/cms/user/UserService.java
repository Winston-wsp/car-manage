package cn.edu.bdu.carmanage.service.cms.user;

import cn.edu.bdu.carmanage.entity.car.CarCard;
import cn.edu.bdu.carmanage.entity.car.CarParking;
import cn.edu.bdu.carmanage.entity.user.*;
import cn.edu.bdu.carmanage.mapper.*;
import cn.edu.bdu.carmanage.utils.CardNumberUtils;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @Author Winston
 * @Date 2020/1/10
 **/
@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AnnouncementMapper announcementMapper;
    @Autowired
    private UserPayMapper userPayMapper;
    @Autowired
    private CarCardMapper carCardMapper;
    @Autowired
    private CarParkingMapper carParkingMapper;
    @Autowired
    private UserTypeMapper userTypeMapper;

    /**
     * 添加新的普通用户
     *
     * @param user
     */
    public void addUser(User user) {
        userMapper.insert(user);
    }

    /**
     * 根据id获取普通用户
     *
     * @param id
     * @return
     */
    public User getUserById(String id) {
        User user = userMapper.selectById(id);
        return user;
    }

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    public User getUserByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = this.userMapper.selectOne(queryWrapper);
        return user;
    }

    public int updateUser(User user) {
        if (user.getPassword() == null || "".equals(user.getPassword())) {
            {
                User user1 = this.userMapper.selectById(user.getId());
                user.setPassword(user1.getPassword());
            }
        }
        int row = userMapper.updateById(user);
        return row;
    }

    /**
     * 根据用户名密码查找用户
     *
     * @param user
     * @return
     */
    public User getUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        queryWrapper.eq("password", user.getPassword());
        User u = userMapper.selectOne(queryWrapper);
        return u;

    }

    public UserVO<User> getUsers(String username, String nicheng, String phone, Long currentPagge, Long size) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!"".equals(username) && username != null) {
            queryWrapper.eq("username", username);
        }
        if (!"".equals(nicheng) && nicheng != null) {
            queryWrapper.eq("nicheng", nicheng);
        }
        if (!"".equals(phone) && phone != null) {
            queryWrapper.eq("phone_number", phone);
        }
        IPage<User> iPage = new Page<>(currentPagge, size);
        IPage<User> page = userMapper.selectPage(iPage, queryWrapper);
        UserVO<User> userVO = new UserVO<>();
        userVO.setPages(page.getPages());
        userVO.setCurrent(page.getCurrent());
        userVO.setSize(page.getSize());
        userVO.setTotal(page.getTotal());
        userVO.setList(page.getRecords());
        return userVO;
    }

    public int deleteUser(String id) {

        int row = userMapper.deleteById(id);
        return row;
    }

    /**
     * 发布公告
     *
     * @param announcement
     * @return
     */
    public int addAnnouncement(Announcement announcement) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        announcement.setTime(timestamp);
        int row = announcementMapper.insert(announcement);
        return row;
    }

    /**
     * 查找历史公告
     *
     * @param currentPage
     * @param size
     * @return
     */
    public UserVO<Announcement> getAnnouncement(Long currentPage, Long size) {
        IPage<Announcement> iPage = new Page<>(currentPage, size);
        IPage<Announcement> page = announcementMapper.selectPage(iPage, null);
        UserVO<Announcement> announcementVO = new UserVO<>();
        announcementVO.setPages(page.getPages());
        announcementVO.setCurrent(page.getCurrent());
        announcementVO.setSize(page.getSize());
        announcementVO.setTotal(page.getTotal());
        announcementVO.setList(page.getRecords());
        return announcementVO;
    }

    public Announcement getAnnouncementById(String id) {
        Announcement announcement = this.announcementMapper.selectById(id);
        return announcement;
    }

    public int deleteAnnouncementById(String id) {
        int row = this.announcementMapper.deleteById(id);
        return row;
    }

    public int editAnnouncement(Announcement announcement) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        announcement.setTime(timestamp);
        int row = this.announcementMapper.updateById(announcement);
        return row;
    }

    public List<Announcement> getAnnouncementList() {
        List<Announcement> announcements = this.announcementMapper.selectList(null);
        return announcements;
    }


    public int updateUserById(String id, String password, String newPassword) {
        int row = -1;
        User user = this.userMapper.selectById(id);
        if (user.getPassword().equals(password)) {
            user.setPassword(newPassword);
            row = this.userMapper.updateById(user);
        }
        return row;
    }

    // 充值
    public int addUserMoney(String userId, Double money) {
        User user = this.userMapper.selectById(userId);
        user.setMoney(user.getMoney() + money);
        int row = this.userMapper.updateById(user);
        return row;
    }

    // 缴费的时候生成的订单
    public void addPayOrder(String userId, String context, Double money) {
        UserPay userPay = new UserPay();
        userPay.setUserId(userId);
        userPay.setPay(money);
        userPay.setOrderNumber("JFDD" + CardNumberUtils.getCardNumber());
        userPay.setTime(DateUtil.date());
        userPay.setContext(context);

        this.userPayMapper.insert(userPay);
    }

    // 用户账户余额消费
    public int userConsume(String userId, Double money) {
        User user = this.userMapper.selectById(userId);
        // 如果账户余额小于需要消费的金额则返回 -2
        if (user.getMoney() - money < 0) {
            return -2;
        }
        user.setMoney(user.getMoney() - money);
        int row = this.userMapper.updateById(user);
        return row;
    }

    public CarCard getCarCardInfo(String userId) {
        QueryWrapper<CarCard> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        CarCard carCard = this.carCardMapper.selectOne(queryWrapper);
        if (carCard != null) {
            if (DateUtil.isExpired(carCard.getStartTime(), carCard.getEndTime(), DateUtil.date())) {
                // 如果办理的会员卡过期的话
                return null;
            }
            return carCard;
        }
        return carCard;
    }

    // 历史缴费查询
    public UserVO<UserPay> getUserCost(String userId, String start, String end, Long currentPage, Long size) {
        Date startDate = null;
        Date endDate = null;
        if (!StringUtils.isEmpty(start)) {
            startDate = DateUtil.parse(start);
        }
        if (!StringUtils.isEmpty(end)) {
            endDate = DateUtil.parse(end);
        }
        IPage<UserPay> iPage = new Page<>(currentPage, size);
        QueryWrapper<UserPay> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        if (startDate != null && endDate != null) {
            // 起始日期和结束日期都不为空
            queryWrapper.ge("time", startDate);
            queryWrapper.le("time", DateUtil.offsetDay(endDate, 1));
        }
        if (startDate != null && endDate == null) {
            // 起始日期不为空，结束日期为空
            queryWrapper.ge("time", startDate);
        }
        if (startDate == null && endDate != null) {
            // 起始日期为空，结束日期不为空
            queryWrapper.le("time", DateUtil.offsetDay(endDate, 1));
        }
        queryWrapper.orderByDesc("time");
        IPage<UserPay> page = userPayMapper.selectPage(iPage, queryWrapper);
        UserVO<UserPay> userPayVO = new UserVO<>();
        userPayVO.setPages(page.getPages());
        userPayVO.setCurrent(page.getCurrent());
        userPayVO.setSize(page.getSize());
        userPayVO.setTotal(page.getTotal());
        userPayVO.setList(page.getRecords());

        return userPayVO;
    }

    /**
     * 用户历史停车记录
     *
     * @param userId
     * @param start
     * @param end
     * @param currentPage
     * @param size
     * @return
     */
    public UserVO<CarParking> getUserHistoryParking(String userId, String start, String end, Long currentPage, Long size) {
        Date startDate = null;
        Date endDate = null;
        if (!StringUtils.isEmpty(start)) {
            startDate = DateUtil.parse(start);
        }
        if (!StringUtils.isEmpty(end)) {
            endDate = DateUtil.parse(end);
        }
        IPage<CarParking> iPage = new Page<>(currentPage, size);
        QueryWrapper<CarParking> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("car_parking.user_id", userId);
        queryWrapper.isNotNull("car_parking.end_time");
        if (startDate != null && endDate != null) {
            // 起始日期和结束日期都不为空
            queryWrapper.ge("car_parking.start_time", startDate);
            queryWrapper.le("car_parking.start_time", DateUtil.offsetDay(endDate, 1));
        }
        if (startDate != null && endDate == null) {
            // 起始日期不为空，结束日期为空
            queryWrapper.ge("car_parking.start_time", startDate);
        }
        if (startDate == null && endDate != null) {
            // 起始日期为空，结束日期不为空
            queryWrapper.le("car_parking.start_time", DateUtil.offsetDay(endDate, 1));
        }
        queryWrapper.orderByDesc("car_parking.start_time");
        IPage<CarParking> page = carParkingMapper.getCarParkings(iPage, queryWrapper);
        UserVO<CarParking> carParkingVO = new UserVO<>();
        carParkingVO.setPages(page.getPages());
        carParkingVO.setCurrent(page.getCurrent());
        carParkingVO.setSize(page.getSize());
        carParkingVO.setTotal(page.getTotal());
        carParkingVO.setList(page.getRecords());

        return carParkingVO;
    }

    public List<UserType> getUserTypeInfo() {
        List<UserType> userTypes = this.userTypeMapper.selectList(null);
        return userTypes;
    }
}
