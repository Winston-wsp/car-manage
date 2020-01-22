package cn.edu.bdu.carmanage.controller.adminuser;

import cn.edu.bdu.carmanage.entity.admin.AdminUser;
import cn.edu.bdu.carmanage.service.cms.adminuser.AdminUserService;
import cn.edu.bdu.carmanage.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Winston
 * @Date 2020/1/7
 **/
@Controller
@RequestMapping("admin/user")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    /**
     * 添加新的管理员用户
     */
    @PostMapping("addAdminUser")
    @ResponseBody
    public ResponseEntity<Void> addAdminUser(@RequestBody AdminUser adminUser) {
        AdminUser user = new AdminUser();
        user.setUsername(adminUser.getUsername());
        user.setPassword(Md5.encodeByMD5(adminUser.getPassword()));
        adminUserService.addAdminUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("getAdminUser")
    public String getAdminUser( AdminUser adminUser, Model model) {
        Boolean flag = adminUserService.getAdminUser(adminUser);
        if (flag) {
            model.addAttribute("adminUser", adminUser);
            return "success";
        }
        model.addAttribute("message","用户名或密码错误，请重新输入");
        return "index";
    }

    @GetMapping("getAdminUser/{id}")
    @ResponseBody
    public ResponseEntity<AdminUser> getAdminUserById(@PathVariable("id") Integer id) {
        AdminUser adminUser = adminUserService.getAdminUserById(id);

        return ResponseEntity.ok(adminUser);
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }


}
