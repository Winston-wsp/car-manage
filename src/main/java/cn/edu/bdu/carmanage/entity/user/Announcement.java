package cn.edu.bdu.carmanage.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author WU
 * @Date 2020/2/12 20:07
 * @Version 1.0
 */
@Data
@TableName("user_announcement")
public class Announcement {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String title;
    private String content;
    private Timestamp time;
}
