package cn.edu.bdu.carmanage.service.cms.adminuser;

import cn.edu.bdu.carmanage.entity.admin.AdminUser;
import cn.edu.bdu.carmanage.entity.user.UserPay;
import cn.edu.bdu.carmanage.entity.user.UserVO;
import cn.edu.bdu.carmanage.mapper.AdminUserMapper;
import cn.edu.bdu.carmanage.mapper.UserPayMapper;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @Author Winston
 * @Date 2020/1/7
 **/
@Service
public class AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private UserPayMapper userPayMapper;

    /**
     * 添加新的管理员用户
     *
     * @param adminUser
     */
    public void addAdminUser(AdminUser adminUser) {
        adminUserMapper.insert(adminUser);
    }

    /**
     * 根据id获取管理员用户
     *
     * @param id
     * @return
     */
    public AdminUser getAdminUserById(Integer id) {
        AdminUser adminUser = adminUserMapper.selectById(id);
        return adminUser;
    }

    /**
     * 根据用户名和密码查找管理员用户是否存在
     *
     * @param adminUser
     * @return
     */
    public Boolean getAdminUser(AdminUser adminUser) {
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", adminUser.getUsername());
        queryWrapper.eq("password", adminUser.getPassword());
        AdminUser user = adminUserMapper.selectOne(queryWrapper);
        if (user != null) {
            return true;
        }
        return false;
    }

    public int updateAdminUserByUsername(AdminUser adminUser, String newPassword) {
        int row = -1;
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", adminUser.getUsername());
        queryWrapper.eq("password", adminUser.getPassword());
        AdminUser user = this.adminUserMapper.selectOne(queryWrapper);
        if (user == null) {
            return row;
        }
        user.setPassword(newPassword);
        row = adminUserMapper.updateById(user);
        return row;
    }

    public UserVO<UserPay> getHistoryOrder(String username, String startTime, String endTime, Long currentPage, Long size) {
        Date startDate = null;
        Date endDate = null;
        if (!StringUtils.isEmpty(startTime)) {
            startDate = DateUtil.parse(startTime);
        }
        if (!StringUtils.isEmpty(endTime)) {
            endDate = DateUtil.parse(endTime);
        }

        IPage<UserPay> page = new Page<>(currentPage, size);
        QueryWrapper<UserPay> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(username)){
            queryWrapper.eq("user_user.username",username);
        }
        if (startDate != null && endDate != null) {
            // 起始日期和结束日期都不为空
            queryWrapper.ge("pay.time", startDate);
            queryWrapper.le("pay.time", DateUtil.offsetDay(endDate, 1));
        }
        if (startDate != null && endDate == null) {
            // 起始日期不为空，结束日期为空
            queryWrapper.ge("pay.time", startDate);
        }
        if (startDate == null && endDate != null) {
            // 起始日期为空，结束日期不为空
            queryWrapper.le("pay.time", DateUtil.offsetDay(endDate, 1));
        }
        queryWrapper.orderByDesc("pay.time");

        IPage<UserPay> userPayIPage = this.userPayMapper.getUserPay(page, queryWrapper);
        UserVO<UserPay> userPayVO = new UserVO<>();
        userPayVO.setPages(page.getPages());
        userPayVO.setCurrent(page.getCurrent());
        userPayVO.setSize(page.getSize());
        userPayVO.setTotal(page.getTotal());
        userPayVO.setList(page.getRecords());
        return userPayVO;
    }
}
