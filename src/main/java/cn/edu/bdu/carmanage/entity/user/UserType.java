package cn.edu.bdu.carmanage.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @description:
 * @author: Winston
 * @createTime: 2020/3/17 21:49
 */
@Data
@TableName(value = "user_type")
public class UserType {
    @TableField("user_type")
    private String userType;
    @TableField("type_describe")
    private String typeDescribe;
}
