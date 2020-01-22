package cn.edu.bdu.carmanage.controller.user;

import cn.edu.bdu.carmanage.entity.admin.AdminUser;
import cn.edu.bdu.carmanage.entity.user.User;
import cn.edu.bdu.carmanage.service.cms.user.UserService;
import cn.edu.bdu.carmanage.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
     * @param user
     * @return
     */
    @PostMapping("addUser")
    @ResponseBody
    public String addUser(@RequestBody User user) {
        User u = new User();
        u.setUsername(user.getUsername());
        u.setPassword(Md5.encodeByMD5(user.getPassword()));
        userService.addUser(u);
        return "success";
    }

    @PostMapping("getUser")
    public String getUser(User user, Model model) {
        Boolean flag = userService.getUser(user);
        if (flag) {
            model.addAttribute("user", user);
            return "ok";
        }
        model.addAttribute("message","用户名或密码错误，请重新输入");
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
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);

        return ResponseEntity.ok(user);
    }

    /**
     * 登录页面
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "login";

    }

    /**
     * 注册页面
     * @return
     */
    @GetMapping("/register")
    public String register() {
        return "register";
    }


}