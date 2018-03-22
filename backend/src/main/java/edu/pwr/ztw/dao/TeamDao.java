package edu.pwr.ztw.dao;

import edu.pwr.ztw.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface TeamDao extends JpaRepository<Team,Long>{
}
