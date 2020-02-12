package cn.edu.bdu.carmanage.entity.user;

import lombok.Data;

import java.util.List;

/**
 * @Author WU
 * @Date 2020/2/10 18:58
 * @Version 1.0
 */
@Data
public class UserVO<T> {

    private Long total;
    private Long size;
    private Long pages;
    private Long current;
    private List<T> list;



}
