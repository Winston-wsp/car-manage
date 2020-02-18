package cn.edu.bdu.carmanage.service.cms.user;

import cn.edu.bdu.carmanage.entity.user.Announcement;
import cn.edu.bdu.carmanage.entity.user.User;
import cn.edu.bdu.carmanage.entity.user.UserVO;
import cn.edu.bdu.carmanage.mapper.AnnouncementMapper;
import cn.edu.bdu.carmanage.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @Author Winston
 * @Date 2020/1/10
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AnnouncementMapper announcementMapper;

    /**
     * 添加新的普通用户
     *
     * @param user
     */
    public void addUser(User user) {
        userMapper.insert(user);
    }

    /**
     * 根据id获取普通用户
     *
     * @param id
     * @return
     */
    public User getUserById(String id) {
        User user = userMapper.selectById(id);
        return user;
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User getUserByUsername(String username){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = this.userMapper.selectOne(queryWrapper);
        return user;
    }

    public int updateUser(User user) {
        int row = userMapper.updateById(user);
        return row;
    }

    /**
     * 根据用户名密码查找用户
     *
     * @param user
     * @return
     */
    public User getUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        queryWrapper.eq("password", user.getPassword());
        User u = userMapper.selectOne(queryWrapper);
        return u;

    }

    public UserVO<User> getUsers(String username, String nicheng, String phone, Long currentPagge, Long size) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!"".equals(username) && username != null) {
            queryWrapper.eq("username", username);
        }
        if (!"".equals(nicheng) && nicheng != null) {
            queryWrapper.eq("nicheng", nicheng);
        }
        if (!"".equals(phone) && phone != null) {
            queryWrapper.eq("phone_number", phone);
        }
        IPage<User> iPage = new Page<>(currentPagge, size);
        IPage<User> page = userMapper.selectPage(iPage, queryWrapper);
        UserVO<User> userVO = new UserVO<>();
        userVO.setPages(page.getPages());
        userVO.setCurrent(page.getCurrent());
        userVO.setSize(page.getSize());
        userVO.setTotal(page.getTotal());
        userVO.setList(page.getRecords());
        return userVO;
    }

    public int deleteUser(String id) {

        int row = userMapper.deleteById(id);
        return row;
    }

    /**
     * 发布公告
     *
     * @param announcement
     * @return
     */
    public int addAnnouncement(Announcement announcement) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        announcement.setTime(timestamp);
        int row = announcementMapper.insert(announcement);
        return row;
    }

    /**
     * 查找历史公告
     *
     * @param currentPage
     * @param size
     * @return
     */
    public UserVO<Announcement> getAnnouncement(Long currentPage, Long size) {
        IPage<Announcement> iPage = new Page<>(currentPage, size);
        IPage<Announcement> page = announcementMapper.selectPage(iPage, null);
        UserVO<Announcement> announcementVO = new UserVO<>();
        announcementVO.setPages(page.getPages());
        announcementVO.setCurrent(page.getCurrent());
        announcementVO.setSize(page.getSize());
        announcementVO.setTotal(page.getTotal());
        announcementVO.setList(page.getRecords());
        return announcementVO;
    }

    public Announcement getAnnouncementById(String id) {
        Announcement announcement = this.announcementMapper.selectById(id);
        return announcement;
    }

    public int deleteAnnouncementById(String id) {
        int row = this.announcementMapper.deleteById(id);
        return row;
    }

    public int editAnnouncement(Announcement announcement) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        announcement.setTime(timestamp);
        int row = this.announcementMapper.updateById(announcement);
        return row;
    }

    public List<Announcement> getAnnouncementList() {
        List<Announcement> announcements = this.announcementMapper.selectList(null);
        return announcements;
    }
}
