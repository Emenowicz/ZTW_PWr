package edu.pwr.ztw.dao;

import edu.pwr.ztw.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface MatchDao extends JpaRepository<Match,Long> {
}
