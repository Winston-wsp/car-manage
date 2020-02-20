package cn.edu.bdu.carmanage.entity.car;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author WU
 * @Date 2020/2/18 20:30
 * @Version 1.0
 */
@Data
@TableName(value = "car_card")
public class CarCard {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("user_id")
    private String userId;
    @TableField("card_type")
    private int cardType;
    @TableField("card_number")
    private String cardNumber;
    @TableField("start_time")
    private Date startTime;
    @TableField("end_time")
    private Date endTime;
}
