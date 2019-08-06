package com.ebaotech.study.test;

import com.ebaotech.study.domain.Student;
import com.ebaotech.study.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.junit.Test;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Iterator;
import java.util.List;

/**
 * @author: BlueMelancholy
 * 2019/7/9 14:23
 * @desc:
 */
public class StudentTest {
    @Test
    public void testSave() {
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        try {
            for (int i = 1; i <= 10; i++) {
                Student student = new Student();
                student.setName("王五" + i);
                student.setAge(18+i);
                student.setScore(73.0+i);
                //save之前数据库先生成一个id返回给session，因为session是通过id来管理对象的
                //session实际上是一个map，key是被管理对象的id,value为被管理对象的引用
                session.save(student);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
    @Test
    public void testSQLAndHQL() {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            String hql = "from Student where name like :name";
            String sql = "select t_id,t_name,t_age,t_score from t_student";
//            List<Student> students = session.createSQLQuery(sql).addEntity(Student.class).list();
            List<Student> students = session.createQuery(hql).setParameter("name","%五%").setFirstResult(1).setMaxResults(3).list();
//          List<Student> students = session.createCriteria(Student.class).addOrder(Order.desc("age")).list();
            students.forEach(student -> System.out.println(student));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
    @Test
    public void testHQL() {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
//            String hql = "select count(*) from Student";
//            Long l = (Long)session.createQuery(hql).uniqueResult();
            String hql = "select new Student(name,age) from Student";
            List<Student> studentList = session.createQuery(hql).list();
            studentList.forEach(student -> {
                System.out.println(student);
            });
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
    @Test
    @Deprecated
    public void testQBC() {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            ProjectionList projectionList = Projections.projectionList().add(Projections.alias(Projections.property("name"), "name"))
                    .add(Projections.alias(Projections.property("age"), "age"));
            List<Student> studentList = session.createCriteria(Student.class)
                    .setProjection(projectionList)
                    .setResultTransformer(Transformers.aliasToBean(Student.class)).list();
            studentList.forEach((s)-> System.out.println(s));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
    @Test
    @Deprecated
    public void testQBC2() {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            List<Object> objectList = session.createCriteria(Student.class).setProjection(Projections.groupProperty("age")).list();
            objectList.forEach(o -> System.out.println(o));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
    @Test
    public void testIterator() {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            String hql = "from Student";
            List<Student> studentList = session.createQuery(hql).list();
            studentList.forEach(System.out::println);
            System.out.println("------------第二次查询------------");
            //第二次查询使用iterator直接从缓存中取数据，session缓存中map的key为id，value为Student数据
            Iterator iterator2 = session.createQuery(hql).iterate();
            while(iterator2.hasNext()){
                System.out.println(iterator2.next());
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
    @Test
    public void testNameQuery() {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.beginTransaction();
            Student student = (Student) session.getNamedQuery("queryById").setParameter("id", 5).uniqueResult();
            System.out.println(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
    @Test
    public void testLazyLoad() {
        Session session = HibernateUtils.openSession();
        try {
            session.beginTransaction();
            Student student = session.load(Student.class, 20);
            System.out.println(student.getId());
            System.out.println(student.getName());
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    /**
     * 测试快照
     */
    @Test
    public void testSnapShot() {
        Session session = HibernateUtils.openSession();
        try {
            session.beginTransaction();
//            将数据DB中读出来，将数据转成对象，放在堆内存中，将对象的id放入session缓存map的key中
//            对象的引用放入session缓存map的value中，将对象的详情放入到“快照”中
            Student student = session.load(Student.class, 2);
            //修改堆内存的对象数据
//            事务提交时，将堆内存中的数据与“快照”中数据进行对比，若不同则执行update，否则不执行
            student.setName("二妞");
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
//    删除到达刷新点的情况
    @Test
    public void testFlushPoint() {
        Session session = HibernateUtils.openSession();
        try {
            session.beginTransaction();
//            session.setFlushMode(FlushModeType.COMMIT);
            Student student = session.get(Student.class, 13);
            System.out.println("刷新前:" + student);
//            将session缓存中的key对应的value置为空，不过这个key的Map.Entry对象并没有删除，即该对象的key还存在与map中
            session.delete(student);
            Student student1 = session.get(Student.class, 13);
            System.out.println("刷新前:" + student1);
//            刷新点，不是同步点，对session缓存的数据进行刷新，实际上对堆内存数据的刷新
            session.createQuery("from Student").list();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
//    乐观锁实现
    @Test
    public void testOptimisticLock() {
        Session session = HibernateUtils.openSession();
        try {
            session.beginTransaction();
            Student student = session.get(Student.class, 13);
            student.setName("张小三");
            session.save(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

}
