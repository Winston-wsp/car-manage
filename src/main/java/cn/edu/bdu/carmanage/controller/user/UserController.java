package cn.edu.bdu.carmanage.controller.user;

import cn.edu.bdu.carmanage.entity.car.CarCard;
import cn.edu.bdu.carmanage.entity.car.CarParkingCost;
import cn.edu.bdu.carmanage.entity.user.*;
import cn.edu.bdu.carmanage.service.cms.car.CarParksService;
import cn.edu.bdu.carmanage.service.cms.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Winston
 * @Date 2020/1/10
 **/
@Controller
@RequestMapping("user/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CarParksService carParksService;

    /**
     * 添加新的普通用户
     *
     * @param user
     * @return
     */
    @PostMapping("addUser")
    @ResponseBody
    public String addUser(User user) {
        User userByUsername = this.userService.getUserByUsername(user.getUsername());
        if (userByUsername != null) {
            return "error";
        }
        User u = new User();
        u.setUsername(user.getUsername());
        u.setNicheng(user.getNicheng());
        u.setPassword(user.getPassword());
        u.setPhoneNumber(user.getPhoneNumber());
        userService.addUser(u);
        return "success";
    }

    @GetMapping("/getUser")
    public String getUser(@RequestParam(value = "userId") String userId, HttpServletRequest request, Model model) {
        User u = null;
        if (userId != null) {
            u = this.userService.getUserById(userId);
            Map<String, Integer> carParksMap = new HashMap<>();
            request.getSession().setAttribute("user", u);

            Integer allCarParks = this.carParksService.getAllCarParks();
            Integer emptyCarParks = this.carParksService.getEmptyCarParks();
            carParksMap.put("allCarParks", allCarParks);
            carParksMap.put("emptyCarParks", emptyCarParks);
            model.addAttribute("carParksMap", carParksMap);

            // 获取费用说明信息
            List<UserType> userTypeInfoList = this.userService.getUserTypeInfo();
            model.addAttribute("userTypeInfoList", userTypeInfoList);
            // 获取公告栏
            List<Announcement> announcementList = this.userService.getAnnouncementList();
            model.addAttribute("announcementList", announcementList);
            return "/user/index";
        }
        return "/login";
    }

    @PostMapping("getUser")
    public String getUser(@RequestParam(value = "userId", required = false) String userId, User user, Model model, HttpServletRequest request) {
        // 查询用户是否存在
        User u = userService.getUser(user);
        //如果存在则保存用户session
        if (u != null) {
            request.getSession().setAttribute("user", u);
            Map<String, Integer> carParksMap = new HashMap<>();
            Integer allCarParks = this.carParksService.getAllCarParks();
            Integer emptyCarParks = this.carParksService.getEmptyCarParks();
            carParksMap.put("allCarParks", allCarParks);
            carParksMap.put("emptyCarParks", emptyCarParks);
            model.addAttribute("carParksMap", carParksMap);

            // 获取公告栏
            List<Announcement> announcementList = this.userService.getAnnouncementList();
            model.addAttribute("announcementList", announcementList);

            // 获取费用说明信息
            List<UserType> userTypeInfoList = this.userService.getUserTypeInfo();
            model.addAttribute("userTypeInfoList", userTypeInfoList);
            return "/user/index";
        }
        model.addAttribute("message", "用户名或密码错误，请重新输入");
        return "login";
    }

    /**
     * 根据id查找普通用户
     *
     * @param id
     * @return
     */
    @GetMapping("getUserById/{id}")
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @PutMapping("updateUser")
    @ResponseBody
    public int updateUser(String newPassword, User user) {
        int row = userService.updateUser(user);
        return row;
    }


    @DeleteMapping("deleteUser/{id}")
    @ResponseBody
    public int deleteUser(@PathVariable("id") String id) {
        int row = userService.deleteUser(id);
        return row;
    }

    /**
     * 查找所有用户
     */
    @GetMapping("getUsers")
    public String getUsers(@RequestParam(value = "username", required = false, defaultValue = "") String username,
                           @RequestParam(value = "nicheng", required = false, defaultValue = "") String nicheng,
                           @RequestParam(value = "phone", required = false, defaultValue = "") String phone,
                           @RequestParam(value = "currentPage", defaultValue = "1") Long currentPage,
                           @RequestParam(value = "size", defaultValue = "8") Long size, Model model) {
        UserVO<User> userVO = userService.getUsers(username, nicheng, phone, currentPage, size);
        model.addAttribute("userVO", userVO);
        return "/manage/user";
    }

    /**
     * 登录页面
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";

    }

    @GetMapping("/userLogout")
    public String userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "/login";
    }

    /**
     * 注册页面
     *
     * @return
     */
    @GetMapping("/register")
    public String register() {
        return "register";
    }


    /**
     * 发布公告
     *
     * @param announcement
     * @return
     */
    @PostMapping("/addAnnouncement")
    @ResponseBody
    public int addAnnouncement(Announcement announcement) {
        int row = userService.addAnnouncement(announcement);
        return row;
    }

    @GetMapping("/getAnnouncement")
    public String getAnnouncement(@RequestParam(value = "currentPage", defaultValue = "1") Long currentPage, @RequestParam(value = "size", defaultValue = "8") Long size, Model model) {
        UserVO<Announcement> announcementVO = userService.getAnnouncement(currentPage, size);
        if (!CollectionUtils.isEmpty(announcementVO.getList())) {
            model.addAttribute("announcementVO", announcementVO);
        }
        return "/manage/announcement";
    }

    /*  @GetMapping("/getAnnouncementToUser")
      public String getAnnouncementToUser(@RequestParam(value = "currentPage", defaultValue = "1") Long currentPage, @RequestParam(value = "size", defaultValue = "8") Long size, Model model) {
          UserVO<Announcement> announcementVO = userService.getAnnouncement(currentPage, size);
          if (!CollectionUtils.isEmpty(announcementVO.getList())) {
              model.addAttribute("announcementVO", announcementVO);
          }
          return "/user/announcement";
      }
  */
    @GetMapping("/getAnnouncementById/{id}")
    public ResponseEntity<Announcement> getAnnouncementById(@PathVariable("id") String id) {
        Announcement announcement = this.userService.getAnnouncementById(id);
        if (announcement == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(announcement);
    }

    @DeleteMapping("/deleteAnnouncementById/{id}")
    @ResponseBody
    public int deleteAnnouncementById(@PathVariable("id") String id) {
        int row = this.userService.deleteAnnouncementById(id);
        return row;
    }

    @PutMapping("/editAnnouncement")
    @ResponseBody
    public int editAnnouncement(Announcement announcement) {
        int row = this.userService.editAnnouncement(announcement);
        return row;
    }

    /**
     * 用户个人信息
     *
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("/toUserInfo")
    public String toUserInfo(@RequestParam String userId, Model model) {
        User user = this.userService.getUserById(userId);
        CarCard carCard = this.userService.getCarCardInfo(userId);
        model.addAttribute("carCard", carCard);
        model.addAttribute("user", user);
        return "/user/userInfo";
    }

    @GetMapping("/toUpdatePassword")
    public String toUpdatePassword(@RequestParam String userId, Model model) {
        model.addAttribute("userId", userId);
        return "/user/updatePwd";
    }

    /**
     * 更新用户密码
     *
     * @return
     */
    @PutMapping("/updateUserById")
    @ResponseBody
    public int updateUserById(@RequestParam("id") String id, @RequestParam("password") String password, @RequestParam("newPassword") String newPassword) {
        int row = userService.updateUserById(id, password, newPassword);
        return row;
    }

    @GetMapping("/toUserCard")
    public String toUserCard(@RequestParam String userId, Model model) {
        model.addAttribute("userId", userId);
        // 获取停车需要的价格
        CarParkingCost carParkingCost = this.carParksService.getCarParkingCost();
        model.addAttribute("carParkingCost", carParkingCost);
        return "/user/userCard";
    }

    @GetMapping("/toUserChongzhi")
    public String toUserChongzhi(@RequestParam String userId, Model model) {
        User user = this.userService.getUserById(userId);
        model.addAttribute("user", user);
        return "/user/chongzhi";
    }

    @PostMapping("/addUserMoney/{userId}")
    @ResponseBody
    public int addUserMoney(@PathVariable("userId") String userId, @RequestParam("context") String context, @RequestParam("money") Double money) {
        int row = this.userService.addUserMoney(userId, money);
        if (row > 0) {
            this.userService.addPayOrder(userId, context, money);
        }
        return row;
    }

    /**
     * 用户的缴费历史
     *
     * @return
     */
    @GetMapping("/toUserCost")
    public String toUserCost(@RequestParam("userId") String userId,
                             @RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
                             @RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
                             @RequestParam(value = "currentPage", defaultValue = "1") Long currentPage,
                             @RequestParam(value = "size", defaultValue = "8") Long size,
                             Model model) {

        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
       /* Map<String,String> map = new HashMap<>();
        if(!StringUtils.isEmpty(startDate) ){
            map.put("startDate",startDate);
        }
        if (!StringUtils.isEmpty(endDate)) {
            map.put("endDate", endDate);
            model.addAttribute("searchDate", map);
        }*/

        UserVO<UserPay> userPayVO = this.userService.getUserCost(userId, startDate, endDate, currentPage, size);
        model.addAttribute("userPayVO", userPayVO);
        return "/user/userCost";
    }
}
