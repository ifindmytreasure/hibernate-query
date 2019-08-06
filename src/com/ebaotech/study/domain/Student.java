package com.ebaotech.study.domain;

/**
 * @author: BlueMelancholy
 * 2019/7/9 12:42
 * @desc:
 */
public class Student {
    private Integer id;
    /**
     * 乐观锁用到的版本号
     */
    private Integer stuVersion;
    private String name;
    private Integer age;
    private Double score;

    public Student() {
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, Integer age, Double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getStuVersion() {
        return stuVersion;
    }

    public void setStuVersion(Integer stuVersion) {
        this.stuVersion = stuVersion;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}
