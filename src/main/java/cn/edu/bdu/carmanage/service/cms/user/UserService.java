package cn.edu.bdu.carmanage.service.cms.user;

import cn.edu.bdu.carmanage.entity.admin.AdminUser;
import cn.edu.bdu.carmanage.entity.user.User;
import cn.edu.bdu.carmanage.mapper.UserMapper;
import cn.edu.bdu.carmanage.utils.Md5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Winston
 * @Date 2020/1/10
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加新的普通用户
     * @param user
     */
    public void addUser(User user)
    {
        userMapper.insert(user);
    }

    /**
     * 根据id获取普通用户
     * @param id
     * @return
     */
    public User getUserById(Integer id){
        User user = userMapper.selectById(id);
        return user;
    }

    /**
     * 根据用户名密码查找用户
     * @param user
     * @return
     */
    public Boolean getUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        queryWrapper.eq("password", Md5.encodeByMD5(user.getPassword()));
        User u = userMapper.selectOne(queryWrapper);
        if(u != null){
            return true;
        }
        return false;

    }
}