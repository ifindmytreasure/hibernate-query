package com.ebaotech.study.test;

import com.ebaotech.study.domain.Order;
import com.ebaotech.study.utils.HibernateUtils;
import org.hibernate.Session;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: BlueMelancholy
 * 2019/8/6 10:45
 * @desc:
 */
public class OrderTest {
    @Test
    public void test01(){
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            Order order = new Order();
            order.setSumPrice(new BigDecimal(3243.34));
            order.setCreateTime(new Date());
            order.setLastUpdateTime(new Date());
            session.save(order);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
