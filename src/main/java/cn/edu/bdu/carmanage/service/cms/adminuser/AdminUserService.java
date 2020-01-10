package cn.edu.bdu.carmanage.service.cms.adminuser;

import cn.edu.bdu.carmanage.entity.admin.AdminUser;
import cn.edu.bdu.carmanage.mapper.AdminUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @Author Winston
 * @Date 2020/1/7
 **/
@Service
public class AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    /**
     * 添加新的管理员用户
     * @param adminUser
     */
    public void addAdminUser(AdminUser adminUser)
    {
        adminUserMapper.insert(adminUser);
    }

    /**
     * 根据id获取管理员用户
     * @param id
     * @return
     */
    public AdminUser getAdminUserById(Integer id){
        AdminUser adminUser = adminUserMapper.selectById(id);
        return adminUser;
    }
}
