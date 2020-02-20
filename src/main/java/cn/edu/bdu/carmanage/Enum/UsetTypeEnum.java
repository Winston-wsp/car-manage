package cn.edu.bdu.carmanage.Enum;

/**
 * @Author WU
 * @Date 2020/2/18 20:19
 * @Version 1.0
 */
public enum UsetTypeEnum {
    COMMON(0,"普通用户"),
    MONTH(1, "包月用户"),
    YEAR(2,"包年用户");

    private int value;
    private String descript;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    UsetTypeEnum(int value, String descript){
        this.value = value;
        this.descript = descript;
    }
}
