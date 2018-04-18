package edu.pwr.ztw.dao;

import edu.pwr.ztw.entity.Tournament;
import edu.pwr.ztw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface TournamentDao extends JpaRepository<Tournament,Long> {
    List<Tournament> getTournamentsByOwner(User user);
}
