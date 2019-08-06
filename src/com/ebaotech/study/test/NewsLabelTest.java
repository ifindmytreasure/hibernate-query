package com.ebaotech.study.test;


import com.ebaotech.study.domain.NewsLabel;
import com.ebaotech.study.utils.HibernateUtils;
import org.hibernate.Session;
import org.junit.Test;

/**
 * @author: BlueMelancholy
 * 2019/8/1 10:06
 * @desc:
 */
public class NewsLabelTest {
    @Test
    public void testA(){
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            NewsLabel football = new NewsLabel("足球", "踢足球");
            NewsLabel basketball = new NewsLabel("篮球", "打篮球");
            NewsLabel sports = new NewsLabel("体育", "体育竞技");
            //单方关联
//            sports.getChildrenNewsLabels().add(football);
//            sports.getChildrenNewsLabels().add(basketball);
//            session.save(sports);
            football.setParentNewsLabel(sports);
            basketball.setParentNewsLabel(sports);
            session.save(football);
            session.save(basketball);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}
