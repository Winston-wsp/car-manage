package cn.edu.bdu.carmanage.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author Winston
 * @Date 2020/1/10
 **/
@Data
@TableName(value = "user_user")
public class User {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String username;
    private String password;
}
