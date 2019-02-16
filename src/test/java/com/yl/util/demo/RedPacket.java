package com.yl.util.demo;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alex
 * @since 2019/2/13 10:43
 */
public class RedPacket {

    public RedPacket(){

    }

    public RedPacket(int id,int leftMoney){
        this.setId(id);
        this.setLeftMoney(leftMoney);
    }

    public void addUser(long uid){
        this.getRebutUsers().add(uid);
    }

    private int id;

    private int leftMoney;

    private Set<Long> rebutUsers = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLeftMoney() {
        return leftMoney;
    }

    public void setLeftMoney(int leftMoney) {
        this.leftMoney = leftMoney;
    }

    public Set<Long> getRebutUsers() {
        return rebutUsers;
    }

    public void setRebutUsers(Set<Long> rebutUsers) {
        this.rebutUsers = rebutUsers;
    }

    @Override
    public String toString() {
        return "RedPacket{" +
            "id=" + id +
            ", leftMoney=" + leftMoney +
            ", rebutUsers=" + rebutUsers +
            '}';
    }
}
