package com.ebaotech.study.domain;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: BlueMelancholy
 * 2019/7/30 16:16
 * @desc:
 */
public class Country {
    private Integer cid;
    private String cname;
//    mappedBy的设置表示当前一方放弃了关联关系的维护权，将维护权交给了对方的关联属性
//    一旦一方放弃了维护权，就不用加@joinColumn注解
//    @OneToMany(targetEntity = Minister.class,cascade = CascadeType.ALL,mappedBy="country)
//    @JoinColumn(name = "country_id")
    private Set<Minister> ministers;

    public Country() {
        ministers = new HashSet<Minister>();
    }

    public Country(String cname) {
        this();
        this.cname = cname;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Set<Minister> getMinisters() {
        return ministers;
    }

    public void setMinisters(Set<Minister> ministers) {
        this.ministers = ministers;
    }

    @Override
    public String toString() {
        return "Country{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", ministers=" + ministers +
                '}';
    }
}
