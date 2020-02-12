package cn.edu.bdu.carmanage.service.cms.car;

import cn.edu.bdu.carmanage.entity.car.CarParks;
import cn.edu.bdu.carmanage.entity.user.User;
import cn.edu.bdu.carmanage.entity.user.UserVO;
import cn.edu.bdu.carmanage.mapper.CarParksMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author WU
 * @Date 2020/2/11 18:51
 * @Version 1.0
 */
@Service
public class CarParksService {
    @Autowired
    private CarParksMapper carParksMapper;


    public int addCarParks(CarParks carParks) {
        int row = this.carParksMapper.insert(carParks);
        return row;
    }

    public UserVO<CarParks> getCarParksList(Long currentPage, Long size) {
        IPage<CarParks> iPage = new Page<>(currentPage,size);
        IPage<CarParks> page = carParksMapper.selectPage(iPage, null);
        UserVO<CarParks> carParksVO = new UserVO<>();
        carParksVO.setPages(page.getPages());
        carParksVO.setCurrent(page.getCurrent());
        carParksVO.setSize(page.getSize());
        carParksVO.setTotal(page.getTotal());
        carParksVO.setList(page.getRecords());
        return carParksVO;

    }

    public CarParks getCarParksById(String id) {

        CarParks carParks = this.carParksMapper.selectById(id);
        return carParks;
    }

    public int updateCarParks(CarParks carParks) {
        int row = this.carParksMapper.updateById(carParks);
        return row;
    }

    public int deleteCarParks(String id) {
        int row = this.carParksMapper.deleteById(id);
        return row;
    }
}
