package com.yl.util.demo;

/**
 * @author Alex
 * @since 2019/2/12 17:03
 */
public class RedPacketItem {

    public RedPacketItem(){

    }

    public RedPacketItem(int id,int moeny,int parentId){
        this.setId(id);
        this.setMoeny(moeny);
        this.setParentId(parentId);
    }

    private int id;

    private int moeny;

    private int parentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoeny() {
        return moeny;
    }

    public void setMoeny(int moeny) {
        this.moeny = moeny;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "RedPacketItem{" +
            "id=" + id +
            ", moeny=" + moeny +
            ", parentId=" + parentId +
            '}';
    }
}
