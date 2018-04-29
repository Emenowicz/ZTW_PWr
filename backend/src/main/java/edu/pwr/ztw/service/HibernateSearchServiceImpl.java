package edu.pwr.ztw.service;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public abstract class HibernateSearchServiceImpl {

    private final EntityManager entityManager;

    @Autowired
    public HibernateSearchServiceImpl(EntityManager entityManager){
        super();
        this.entityManager = entityManager;
    }

    @Transactional
    public void initializeHibernateSearch(){
        try{
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public EntityManager getEntityManager() {
        return entityManager;
    }
}
