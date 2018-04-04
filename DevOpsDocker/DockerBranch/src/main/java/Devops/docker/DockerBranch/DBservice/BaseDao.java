package Devops.docker.DockerBranch.DBservice;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface BaseDao {
    public Session getSession();

    public Session getNewSession();

    public void flush();

    public void clear() ;

    public Object load(Class c, Serializable id) ;

    public int loadNew(Class c);

    public List getAllList(Class c) ;

    public Long getTotalCount(Class c) ;

    public void save(Object bean) ;

    public void update(Object bean) ;

    public void delete(Object bean) ;

    public void delete(Class c, Serializable id) ;

    public void delete(Class c, String[] ids) ;
}
