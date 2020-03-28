package cn.edu.bdu.carmanage;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author i-winstonw
 * @Date 2020/3/9 22:42
 * @Version 1.0
 */
@Data
public class FieldEntity implements Serializable {
    @Excel(name = "手机号")
    private String mobile;

    @Excel(name = "真实姓名")
    private String fullName;

    @Excel(name = "身份证号")
    private String idNum;

    @Excel(name = "用户分组")
    private String userGroup;

    @Excel(name = "用户权限")
    private String userAuthority;

    @Excel(name = "用户状态")
    private String userState;

    @Excel(name = "员工编号")
    private String employeeNumber;
}
