package org.example.common.enums;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/26 11:07
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.common.enums
 */
public enum OrderLogType {
    FROZEN("frozen",1,"冻结"),
    WRITE_OFF("writeOff",2,"核销"),
    ROLL_BACK("rollBack",3,"回滚");

    private OrderLogType(String code,Integer value,String desc) {
        this.code = code;
        this.value = value;
        this.desc = desc;
    }
    private String code;
    private Integer value;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
