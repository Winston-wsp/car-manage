package cn.edu.bdu.carmanage;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author i-winstonw
 * @Date 2020/3/9 22:42
 * @Version 1.0
 */
@Data
public class User implements Serializable {
    @ExcelProperty(value = "手机号")
    private String mobile;

    @ExcelProperty(value = "真实姓名")
    private String fullName;

    @ExcelProperty(value = "身份证号")
    private String idNum;

    @ExcelProperty(value = "用户分组")
    private String userGroup;

    @ExcelProperty(value = "用户权限")
    private String userAuthority;

    @ExcelProperty(value = "用户状态")
    private String userState;

    @ExcelProperty(value = "员工编号")
    private String employeeNumber;
}
