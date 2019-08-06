package com.ebaotech.study.test;

import com.ebaotech.study.domain.Country;
import com.ebaotech.study.domain.Minister;
import com.ebaotech.study.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: BlueMelancholy
 * 2019/7/30 17:42
 * @desc:
 */
public class CountryTest {
    /**
     * 单向关联
     */
    @Test
    public void testA(){
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        try {
            Minister minister1 = new Minister("ddd");
            Minister minister2 = new Minister("eee");
            Minister minister3 = new Minister("fff");
            Country china = new Country("Japan");
            china.getMinisters().add(minister1);
            china.getMinisters().add(minister2);
            china.getMinisters().add(minister3);
            session.save(china);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

    }

    /**
     * 双向关联，多对一，一对多，toString方法只能打印一方，底层执行顺序，先插对方，在插自己，然后对方插入外键
     */
    @Test
    public void testB(){
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        try {
            Minister minister1 = new Minister("111");
            Minister minister2 = new Minister("222");
            Minister minister3 = new Minister("333");
            Country china = new Country("England");
//                单方在维护关系
//            china.getMinisters().add(minister1);
//            china.getMinisters().add(minister2);
//            china.getMinisters().add(minister3);
            //多方在维护关联
            minister1.setCountry(china);
            minister2.setCountry(china);
            minister3.setCountry(china);
            session.save(minister1);
            session.save(minister2);
            session.save(minister3);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

    }
    @Test
    public void testDelete(){
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            Country country = session.get(Country.class, 2);
            Set<Minister> ministers = country.getMinisters();
            Minister minister = session.get(Minister.class, 4);
            System.out.println(country);
            ministers.remove(minister);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

    }

    /**
     * 过滤重复数据
     */
    @Test
    public void testQuery(){
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            String hql = "select distinct c from Country c left join fetch c.ministers";
            List<Country> countries = session.createQuery(hql).list();
            countries.forEach(System.out::println);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

    }
    @Test
    public void testMutilEndedLoad(){
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            Country country = session.get(Country.class, 3);
            System.out.println(country.getCname());
            Set<Minister> ministers = country.getMinisters();
            System.out.println("minister.size: " + ministers.size());
            ministers.forEach(System.out::println);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

    }

    /**
     * 测试fetch=subselect 懒加载情况
     */
    @Test
    public void testSubSelect(){
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            String hql = "from Country";
            List<Country> countries = session.createQuery(hql).list();
            countries.forEach(country -> {
                System.out.println(country.getCname());
                Set<Minister> ministers = country.getMinisters();
                System.out.println("minister.size: " + ministers.size());
                ministers.forEach(System.out::println);
            });
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    /**
     * 测试单端懒加载
     */
    @Test
    public void testSingleEndedLoad(){
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            Minister minister = session.get(Minister.class, 7);
            Country country = minister.getCountry();
            System.out.println(country.getCid());
            System.out.println(country.getCname());
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}
