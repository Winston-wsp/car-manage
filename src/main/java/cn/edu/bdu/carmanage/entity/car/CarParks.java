package cn.edu.bdu.carmanage.entity.car;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author WU
 * @Date 2020/2/11 18:38
 * @Version 1.0
 */
@Data
@TableName(value = "car_carparks")
public class CarParks {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String area;
    @TableField("park_number")
    private String parkNumber;
    private String remark;

}
