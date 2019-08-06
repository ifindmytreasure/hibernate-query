package com.ebaotech.study.test;

import com.ebaotech.study.domain.Course;
import com.ebaotech.study.domain.Scholar;
import com.ebaotech.study.domain.Student;
import com.ebaotech.study.utils.HibernateUtils;
import org.hibernate.Session;
import org.junit.Test;

/**
 * @author: BlueMelancholy
 * 2019/8/1 11:03
 * @desc:
 */
public class CourseTest {
    /**
     * 学生维护
     */
    @Test
    public void testA(){
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            Course javaSE = new Course("javaSE");
            Course javaEE = new Course("javaEE");
            Course android = new Course("Android");
            Scholar stu1 = new Scholar("张三");
            Scholar stu2 = new Scholar("李四");
            stu1.getCourses().add(javaSE);
            stu1.getCourses().add(javaEE);
            stu2.getCourses().add(javaSE);
            stu2.getCourses().add(android);


            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    /**
     * 课程维护
     */
    @Test
    public void testB(){
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            Course javaSE = new Course("javaSE");
            Course javaEE = new Course("javaEE");
            Course android = new Course("Android");
            Scholar stu1 = new Scholar("张三");
            Scholar stu2 = new Scholar("李四");
           javaEE.getScholars().add(stu1);
           javaEE.getScholars().add(stu2);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}
