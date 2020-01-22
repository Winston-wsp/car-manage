package cn.edu.bdu.carmanage.service.cms.adminuser;

import cn.edu.bdu.carmanage.entity.admin.AdminUser;
import cn.edu.bdu.carmanage.mapper.AdminUserMapper;
import cn.edu.bdu.carmanage.utils.Md5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    /**
     * 根据用户名和密码查找管理员用户是否存在
     * @param adminUser
     * @return
     */
    public Boolean getAdminUser(AdminUser adminUser) {
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", adminUser.getUsername());
        queryWrapper.eq("password", Md5.encodeByMD5(adminUser.getPassword()));
        AdminUser user = adminUserMapper.selectOne(queryWrapper);
        if(user != null){
            return true;
        }
        return false;
    }
}