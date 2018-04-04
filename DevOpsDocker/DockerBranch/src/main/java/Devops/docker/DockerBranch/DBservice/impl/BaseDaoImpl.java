package Devops.docker.DockerBranch.DBservice.impl;

import Devops.docker.DockerBranch.DBservice.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class BaseDaoImpl implements BaseDao{
    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession;
    public Session getSession() {
        if(currentSession==null)
            return sessionFactory.openSession();
        return currentSession;
    }

    public Session getNewSession() {

        return sessionFactory.openSession();
    }

    public void flush() {
        getSession().flush();
    }

    public void clear() {
        getSession().clear();
    }

    @SuppressWarnings("rawtypes")
    public Object load(Class c, Serializable id) {
        Session session = getSession();
        return session.get(c, id);
    }

    public int loadNew(Class c) {
        Session session = getSession();
        String hql = String.format("select max(id) from %s", c.getName());
        Integer max = (Integer)session.createQuery(hql).uniqueResult();
        session.close();
        return max;
    }

    public List getAllList(Class c) {
        String hql = "from " + c.getName();
        Session session = getSession();
        return session.createQuery(hql).list();
    }

    public Long getTotalCount(Class c) {
        Session session = getNewSession();
        String hql = "select count(*) from " + c.getName();
        Long count = (Long) session.createQuery(hql).uniqueResult();
        session.close();
        return count != null ? count.longValue() : 0;
    }

    public void save(Object bean) {
        try {
            Session session = getSession();
            session.save(bean);
            session.flush();
            session.clear();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Object bean) {
        Session session = getNewSession();
        session.update(bean);
        session.flush();
        session.clear();
        session.close();
    }

    public void delete(Object bean) {
        Session session = getNewSession();
        session.delete(bean);
        session.flush();
        session.clear();
        session.close();
    }

    @SuppressWarnings({ "rawtypes" })
    public void delete(Class c, Serializable id) {
        Session session = getNewSession();
        Object obj = session.get(c, id);
        session.delete(obj);
        flush();
        clear();
    }

    @SuppressWarnings({ "rawtypes" })
    public void delete(Class c, String[] ids) {
        for (String id : ids) {
            Object obj = getSession().get(c, id);
            if (obj != null) {
                getSession().delete(obj);
            }
        }
    }
}
