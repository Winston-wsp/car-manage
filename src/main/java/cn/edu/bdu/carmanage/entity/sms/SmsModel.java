package cn.edu.bdu.carmanage.entity.sms;

import lombok.Data;

/**
 * @author: Winston
 * @createTime: 2020/5/11
 */
@Data
public class SmsModel {

    /**
     * mobile
     * */
    private String mobile;


    /**
     * templateCode
     * */
    private String templateCode;

    /**
     * templateParam
     * */
    private String templateParam;
}
