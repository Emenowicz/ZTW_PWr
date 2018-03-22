package edu.pwr.ztw.dao;

import edu.pwr.ztw.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface RoundDao extends JpaRepository<Round,Long> {
}
