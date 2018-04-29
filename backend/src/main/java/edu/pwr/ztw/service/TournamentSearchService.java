package edu.pwr.ztw.service;

import edu.pwr.ztw.entity.Tournament;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class TournamentSearchService extends HibernateSearchServiceImpl {

    public TournamentSearchService(EntityManager entityManager) {
        super(entityManager);
    }

    @Transactional
    public List<Tournament> fuzzySearch(String searchTerm){
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(getEntityManager());
        String[] fields = new String[] {"name","description"};
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Tournament.class).get();
        Query luceneQuery = qb.keyword().fuzzy().onFields(fields).matching(searchTerm).createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery,Tournament.class);

        List<Tournament> tournaments = null;

        tournaments = jpaQuery.getResultList();

        return tournaments;
    }

}
