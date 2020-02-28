package cn.edu.bdu.carmanage.entity.pay;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author WU
 * @Date 2020/2/26 20:59
 * @Version 1.0
 */
@Data
@TableName("user_order_alipay")
public class UserOrderAlipay {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    @TableField("user_id")
    private String userId;
    @TableField("out_trade_no")
    private String ouTradeNo;
    @TableField("total_amount")
    private String totalAmount;
    @TableField("subject")
    private String subject;
    @TableField("body")
    private String body;
    @TableField("creatTime")
    private Date date;
    @TableField("status")
    private Integer status;

}
