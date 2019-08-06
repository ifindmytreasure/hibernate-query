package com.ebaotech.study.domain;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: BlueMelancholy
 * 2019/8/1 10:44
 * @desc:
 */
public class Course {
    private Integer courseId;
    private String courseName;
//    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Scholar> scholars;
    public Course() {
        scholars = new HashSet<>();
    }

    public Course(String courseName) {
        this();
        this.courseName = courseName;
    }

    public Set<Scholar> getScholars() {
        return scholars;
    }

    public void setScholars(Set<Scholar> scholars) {
        this.scholars = scholars;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
