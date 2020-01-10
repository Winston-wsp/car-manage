package cn.edu.bdu.carmanage.service.cms.user;

import cn.edu.bdu.carmanage.entity.user.User;
import cn.edu.bdu.carmanage.mapper.UserMapper;
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
}
