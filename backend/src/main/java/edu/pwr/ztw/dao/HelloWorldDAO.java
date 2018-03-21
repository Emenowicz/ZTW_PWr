package edu.pwr.ztw.dao;

import edu.pwr.ztw.entity.HelloWorld;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class HelloWorldDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<HelloWorld> getAll() {
        String hql = "FROM HelloWorld AS hw ORDER BY hw.name ASC";
        return (List<HelloWorld>) entityManager.createQuery(hql).getResultList();
    }
}
