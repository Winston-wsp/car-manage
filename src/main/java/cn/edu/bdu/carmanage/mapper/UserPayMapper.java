package cn.edu.bdu.carmanage.mapper;

import cn.edu.bdu.carmanage.entity.user.UserPay;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @Author WU
 * @Date 2020/2/20 15:20
 * @Version 1.0
 */
@Component
public interface UserPayMapper extends BaseMapper<UserPay> {
    IPage<UserPay> getUserPay(IPage<UserPay> page, @Param(Constants.WRAPPER) Wrapper<UserPay> queryWrapper);
}
