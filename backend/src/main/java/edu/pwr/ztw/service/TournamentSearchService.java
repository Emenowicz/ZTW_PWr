package edu.pwr.ztw.service;

import edu.pwr.ztw.entity.Tournament;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Tournament> findPaginated(String q, Pageable pageable) {
        List<Tournament> tournaments = fuzzySearch(q);
        int start = pageable.getOffset();
        int end = (start + pageable.getPageSize()) > tournaments.size()? tournaments.size() : (start + pageable.getPageSize());
        final Page<Tournament> page = new PageImpl<>(tournaments.subList(start,end), pageable,tournaments.size());
        return page;
    }
}
