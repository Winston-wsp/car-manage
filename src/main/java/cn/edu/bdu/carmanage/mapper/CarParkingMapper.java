package cn.edu.bdu.carmanage.mapper;

import cn.edu.bdu.carmanage.entity.car.CarParking;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author WU
 * @Date 2020/2/15 15:43
 * @Version 1.0
 */
@Component
public interface CarParkingMapper extends BaseMapper<CarParking> {

    List<CarParking> getMyCarParing(String userId);

    IPage<CarParking> getCarParkings(IPage<CarParking> page, @Param(Constants.WRAPPER) Wrapper<CarParking> queryWrapper);
}
