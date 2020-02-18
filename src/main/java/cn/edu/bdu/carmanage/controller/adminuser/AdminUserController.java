package cn.edu.bdu.carmanage.controller.adminuser;

import cn.edu.bdu.carmanage.entity.admin.AdminUser;
import cn.edu.bdu.carmanage.service.cms.adminuser.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
        user.setPassword(adminUser.getPassword());
        adminUserService.addAdminUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("getAdminUser")
    public String getAdminUser(AdminUser adminUser, HttpServletRequest request,Model model) {
        Boolean flag = adminUserService.getAdminUser(adminUser);
        if (flag) {
            request.getSession().setAttribute("adminUserSession",adminUser);
            model.addAttribute("adminUser", adminUser);
            return "/manage/index";
        }
        model.addAttribute("message", "用户名或密码错误，请重新输入");
        return "index";
    }

    @GetMapping("getAdminUser/{id}")
    @ResponseBody
    public ResponseEntity<AdminUser> getAdminUserById(@PathVariable("id") Integer id) {
        AdminUser adminUser = adminUserService.getAdminUserById(id);

        return ResponseEntity.ok(adminUser);
    }
    @PutMapping("/updateAdminUser")
    @ResponseBody
    public int updateAdminUserByUsername(AdminUser adminUser,String newPassword){
        int row = adminUserService.updateAdminUserByUsername(adminUser, newPassword);
        return row;
    }
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/editPassword")
    public String editPassword(@RequestParam("adminUsername") String adminUsername, Model model) {
        model.addAttribute("adminUsername", adminUsername);
        return "/manage/updatePwd";
    }
/*    @PostMapping("/updateAdminUser")
    public {

    }*/

}
