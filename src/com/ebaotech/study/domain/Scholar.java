package com.ebaotech.study.domain;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: BlueMelancholy
 * 2019/8/1 10:46
 * @desc:
 */
public class Scholar {
    private String stuId;
    private String stuName;
//    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "scholars")
    private Set<Course> courses;

    public Scholar() {
        courses = new HashSet<Course>();
    }

    public Scholar(String stuName) {
        this();
        this.stuName = stuName;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Scholar{" +
                "stuId='" + stuId + '\'' +
                ", stuName='" + stuName + '\'' +
                ", courses=" + courses +
                '}';
    }
}