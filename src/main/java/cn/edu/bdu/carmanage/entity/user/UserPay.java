package cn.edu.bdu.carmanage.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author WU
 * @Date 2020/2/20 13:02
 * @Version 1.0
 */
@Data
@TableName(value = "user_pay")
public class UserPay {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    @TableField("user_id")
    private String userId;
    // 付款金额
    private Double pay;
    // 订单编号
    @TableField("order_number")
    private String orderNumber;
    private Date time;
    private String context;
    @TableField(exist = false)
    private User user;
}
