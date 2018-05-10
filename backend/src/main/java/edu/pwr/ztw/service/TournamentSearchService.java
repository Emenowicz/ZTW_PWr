package edu.pwr.ztw.service;

import edu.pwr.ztw.entity.Tournament;
import javassist.NotFoundException;
import org.apache.lucene.search.Query;
import org.hibernate.QueryParameterException;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TournamentSearchService extends HibernateSearchServiceImpl {

    @Resource
    private TournamentService tournamentService;

    public TournamentSearchService(EntityManager entityManager) {
        super(entityManager);
    }

    @Transactional
    public List<Tournament> fuzzySearch(String q, Date sd, Date ed) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(getEntityManager());
        String[] fields = new String[]{"name", "description"};
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Tournament.class).get();
        Query luceneQuery = null;
        if (!q.isEmpty()) {
            if (sd != null && ed != null) {
                luceneQuery = qb
                        .bool()
                        .must(qb
                                .range()
                                .onField("startTime")
                                .from(sd)
                                .to(ed)
                                .createQuery())
                        .should(qb
                                .keyword()
                                .fuzzy()
                                .onFields(fields)
                                .matching(q)
                                .createQuery()
                        )
                        .createQuery();
            } else {
                luceneQuery = qb
                        .keyword()
                        .fuzzy()
                        .onFields(fields)
                        .matching(q)
                        .createQuery();
            }
        } else {
            if (sd != null && ed != null) {
                luceneQuery = qb
                        .range()
                        .onField("startTime")
                        .from(sd)
                        .to(ed)
                        .createQuery();
            }

        }
        if(luceneQuery==null){
            throw new QueryParameterException("Wrong parameters in query");
        }
        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Tournament.class);
        return jpaQuery.getResultList();

    }

    public Page<Tournament> findPaginated(String q, String sd, String ed, Pageable pageable) throws ParseException,QueryParameterException {
        List<Tournament> tournaments;
        if(q.isEmpty()&&sd.isEmpty()&&ed.isEmpty()){
            tournaments = tournamentService.getAllTournaments();
        }else {
            SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
            Date startDate = null;
            Date endDate = null;

            if (!sd.isEmpty()) {
                if (ed.isEmpty()) {
                    ed = sd;
                }
                startDate = sf.parse(sd);
                endDate = sf.parse(ed);
            }

            tournaments = fuzzySearch(q, startDate, endDate);
        }

        int start = pageable.getOffset();
        int end = (start + pageable.getPageSize()) > tournaments.size() ? tournaments.size() : (start + pageable.getPageSize());
        final Page<Tournament> page = new PageImpl<>(tournaments.subList(start, end), pageable, tournaments.size());
        return page;
    }
}
