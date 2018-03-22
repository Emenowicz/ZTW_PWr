package edu.pwr.ztw.dao;

import edu.pwr.ztw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface UserDao extends JpaRepository<User,Long> {
    User getUserByLogin(String login);
}
