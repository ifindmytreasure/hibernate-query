package com.ebaotech.study.test;

import com.ebaotech.study.domain.Country;
import com.ebaotech.study.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

/**
 * @author: BlueMelancholy
 * 2019/8/5 11:08
 * @desc:
 */
public class SecondLevelCacheTest {
    /**
     * 证明二级缓存存在
     *
     */
    @Test
    public void testA(){
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
//            第一次查询
            Country country1 = session.get(Country.class, 4);
//            System.out.println("Country1:" + country1);
            System.out.println("ministers.size=" + country1.getMinisters().size());

//            第二次查询
            Country country2 = session.get(Country.class, 4);
//            System.out.println("Country2:" + country2);
            System.out.println("ministers.size=" + country2.getMinisters().size());

            session.clear();
//            第三次查询
            Country country3 = session.get(Country.class, 4);
//            类缓存中存放在专门的一个称为实体区域的一个缓存中，存放的是详情
//            集合缓存存放在专门的恶一个称为集合区域的一个缓存中，存放的是集合中所包含对象的id
            System.out.println("ministers.size=" + country3.getMinisters().size());

//            System.out.println("Country3:" + country3);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * query缓存测试
     * Query缓存内容，其从Query缓存中查找的数据不再是查询结果对象的id，而是Query查询语句
     * 也就是说，Query查询结果,存放到Query缓存时，其key为Query的查询语句，value为查询结果
     */
    @Test
    public void testQueryCache(){
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            String hql = "from Country where cid = 2";
//            第一次查询
            Country country1 = (Country) session.createQuery(hql).setCacheable(true).uniqueResult();
            System.out.println("Country1:" + country1);
//            第二次查询 从一级缓存中读取
            Country country2 = (Country) session.createQuery(hql).setCacheable(true).uniqueResult();
            System.out.println("Country2:" + country2);
            session.clear();
//            第三次查询 从二级缓存中读取
            Country country3 = (Country) session.createQuery(hql).setCacheable(true).uniqueResult();
            System.out.println("Country3:" + country3);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改时间戳
     */
    @Test
    public void testUpdateTime(){
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            System.out.println(HibernateUtils.getCache());
            Country country = session.load(Country.class, 2);
            System.out.println("更新前" + country.getCname());
//            事务未提交就已经更新了数据，这是因为事务有缓存，读和更新都是从事务缓存中读的
            String hql = "update Country set cname=:cname where cid=:cid";
            session.createQuery(hql).setParameter("cname","UK").setParameter("cid",2).executeUpdate();
            System.out.println("更新后" + country.getCname());
            //对比发现 更新绕过了一级缓存session，直接改到数据库了
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
