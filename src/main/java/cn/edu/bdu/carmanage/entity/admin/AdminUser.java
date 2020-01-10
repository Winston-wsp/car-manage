package cn.edu.bdu.carmanage.entity.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "user_admin")
public class AdminUser {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String username;
    private String password;


}
