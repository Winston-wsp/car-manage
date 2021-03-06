package cn.edu.bdu.carmanage.entity.car;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author WU
 * @Date 2020/2/20 16:50
 * @Version 1.0
 */
@Data
@TableName(value = "car_parking_cost")
public class CarParkingCost {
    private  int id;
    @TableField("month_cost")
    private  int monthCost;
    @TableField("year_cost")
    private  int yearCost;
    @TableField("level_one")
    private  int levelOne;
    @TableField("level_one_time")
    private int levelOneTime;
    @TableField("level_two")
    private  int levelTwo;
    @TableField("level_two_time")
    private int levelTwoTime;
    @TableField("level_three")
    private int levelThree;
    @TableField("level_three_time")
    private int levelThreeTime;
    @TableField("day_cost")
    private  int dayCost;
}
