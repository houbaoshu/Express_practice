package com.kaikeba.bean.BootstarpTable;


import com.kaikeba.bean.Express;
import com.kaikeba.util.DateFormatUtil;
/**
 * @description: 用于Bootstarp框架中table的显示快递数据
 * @author: southwindow
 * @create: 2020-11-30 13:16
 **/
public class ExpressBootstrapTable {
    //快递id
    private int id;
    //快递单号
    private String number;
    //收件人
    private String username;
    //收件人电话
    private String userPhone;
    private String company;
    //取件码
    private String code;
    //Timestamp时间戳类型
    //入库时间
    private String inTime;
    //出库时间
    private String outTime;
    //快递状态：0未取件，1已取件
    private String status;
    //录入该快递的快递员电话
    private String sysPhone;

    public ExpressBootstrapTable() {
    }

    /**
     * 传入父类对象，得到可用于前端显示格式的子类Bean对象
     * @param express
     */
    public ExpressBootstrapTable(Express express) {
        if(express == null){
            return;
        }
        this.id = express.getId();
        this.number = express.getNumber();
        this.username = express.getUsername();
        this.userPhone = express.getUserPhone();
        this.company = express.getCompany();
        //如果该快递已取件，则显示已取件，如果未取件，显示取件码
        this.code = express.getStatus() == 1?"已取件": express.getCode();
        //将TimeStamp转为格式化字符串显示
        this.inTime = express.getInTime() == null ? "未入库" :DateFormatUtil.format(express.getInTime());
        //如果出库时间为null，即未取件，则显示未取件，已取件则显示格式化后的时间
        this.outTime = express.getOutTime() == null?"未取件":DateFormatUtil.format(express.getOutTime());
        //如果快递未取走状态码为0，显示未取件，如果快递已取走状态码为1，显示已取件，
        this.status = express.getStatus() == 0?"未取件":"已取件";
        this.sysPhone = express.getSysPhone();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSysPhone() {
        return sysPhone;
    }

    public void setSysPhone(String sysPhone) {
        this.sysPhone = sysPhone;
    }
}
