package com.ebaotech.study.utils;

import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * @author: BlueMelancholy
 * 2019/7/9 20:17
 * @desc:
 */
public class HibernateUtils {
    private static SessionFactory sf;
    static{
        Configuration conf = new Configuration().configure();
        sf = conf.buildSessionFactory();
    }

    public static Session openSession(){
        Session session = sf.openSession();
        return session;
    }
    public static Session getCurrentSession(){
        Session session = sf.getCurrentSession();
        return session;
    }
    public static Cache getCache(){
        return sf.getCache();
    }
    public static void main(String[] args) {
        System.out.println(HibernateUtils.getCurrentSession());
        System.out.println(HibernateUtils.getCurrentSession());
    }
}

