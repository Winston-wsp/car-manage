package cn.edu.bdu.carmanage.controller.user;

import cn.edu.bdu.carmanage.entity.user.Announcement;
import cn.edu.bdu.carmanage.entity.user.User;
import cn.edu.bdu.carmanage.entity.user.UserVO;
import cn.edu.bdu.carmanage.service.cms.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Winston
 * @Date 2020/1/10
 **/
@Controller
@RequestMapping("user/user")
public class UserController {

    @Autowired
    private UserService userService;

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
        if(userByUsername != null){
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


    @PostMapping("getUser")
    public String getUser(User user, Model model, HttpServletRequest request) {
        User u = userService.getUser(user);
        if (u != null) {
            request.getSession().setAttribute("userSession",u);
            model.addAttribute("user",u);
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
    public int updateUser(User user) {
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

    @GetMapping("/getAnnouncementToUser")
    public String getAnnouncementToUser(@RequestParam(value = "currentPage", defaultValue = "1") Long currentPage, @RequestParam(value = "size", defaultValue = "8") Long size, Model model) {
        UserVO<Announcement> announcementVO = userService.getAnnouncement(currentPage, size);
        if (!CollectionUtils.isEmpty(announcementVO.getList())) {
            model.addAttribute("announcementVO", announcementVO);
        }
        return "/user/announcement";
    }

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

}
