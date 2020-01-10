package cn.edu.bdu.carmanage.controller.user;

import cn.edu.bdu.carmanage.entity.user.User;
import cn.edu.bdu.carmanage.service.cms.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    public ResponseEntity<Void> addUser(@RequestBody User user)
    {
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 根据id查找普通用户
     * @param id
     * @return
     */
    @GetMapping("getUserById/{id}")
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id){
        User user = userService.getUserById(id);

        return ResponseEntity.ok(user);
    }



}
