package com.ebaotech.study.domain;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author: BlueMelancholy
 * 2019/7/30 16:15
 * @desc:
 */
public class Minister {
    private Integer mid;
    private String  mname;
//    @ManyToOne(targetEntity = Country.class,cascade = CascadeType.ALL)
//    @JoinColumn(name = "country_id")
    private Country country;
    public Minister() {
    }

    public Minister(String mname) {
        this.mname = mname;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Minister{" +
                "mid=" + mid +
                ", mname='" + mname + '\'' +
                '}';
    }
}
