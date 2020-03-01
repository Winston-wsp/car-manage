package cn.edu.bdu.carmanage.entity.car;

import cn.edu.bdu.carmanage.entity.user.User;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author WU
 * @Date 2020/2/15 15:20
 * @Version 1.0
 */
@Data
@TableName(value = "car_parking")
public class CarParking {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    @TableField("user_id")
    private String userId;
    @TableField("carparks_id")
    private String carparksId;
    @TableField("plate_number")
    private String plateNumber;
    @TableField("start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @TableField("end_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  endTime;
    @TableField(exist = false)
    private CarParks carParks;
    @TableField(exist = false)
    private User User;
    @TableField("sort")
    private Integer sort;
}
