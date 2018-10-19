package com.yl.common.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alex
 * @since 2018/10/19 17:33
 */
public class UserBasicBean implements Serializable {

    private long id;

    private String name;

    private int sex;

    private long cellphone;

    private String password;

    private int status;

    private Date ct;

    private Date upt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public long getCellphone() {
        return cellphone;
    }

    public void setCellphone(long cellphone) {
        this.cellphone = cellphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCt() {
        return ct;
    }

    public void setCt(Date ct) {
        this.ct = ct;
    }

    public Date getUpt() {
        return upt;
    }

    public void setUpt(Date upt) {
        this.upt = upt;
    }

    @Override
    public String toString() {
        return "UserBasicBean{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", sex=" + sex +
            ", cellphone=" + cellphone +
            ", password='" + password + '\'' +
            ", status=" + status +
            ", ct=" + ct +
            ", upt=" + upt +
            '}';
    }
}
