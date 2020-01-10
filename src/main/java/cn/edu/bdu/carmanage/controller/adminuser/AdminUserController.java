package cn.edu.bdu.carmanage.controller.adminuser;

import cn.edu.bdu.carmanage.entity.admin.AdminUser;
import cn.edu.bdu.carmanage.service.cms.adminuser.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    public ResponseEntity<Void> addAdminUser(@RequestBody AdminUser adminUser)
    {
        adminUserService.addAdminUser(adminUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("getAdminUser/{id}")
    @ResponseBody
    public ResponseEntity<AdminUser> getAdminUserById(@PathVariable("id") Integer id){
        AdminUser adminUser = adminUserService.getAdminUserById(id);

        return ResponseEntity.ok(adminUser);
    }

}
