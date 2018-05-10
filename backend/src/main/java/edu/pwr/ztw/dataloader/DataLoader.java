package edu.pwr.ztw.dataloader;

import edu.pwr.ztw.dao.MatchDao;
import edu.pwr.ztw.dao.TournamentDao;
import edu.pwr.ztw.dao.UserDao;
import edu.pwr.ztw.entity.Enums.PlayMode;
import edu.pwr.ztw.entity.Enums.TournamentType;
import edu.pwr.ztw.entity.Tournament;
import edu.pwr.ztw.entity.User;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

@Component
public class DataLoader implements ApplicationRunner {

    @Resource
    UserDao userDao;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = sf.parse("19-04-2018");
        Date endDate = sf.parse("20-04-2018");
        User user = new User("Tester Testowy");
        Tournament tournament = new Tournament("Testowy turniej", startDate, endDate,
                "Przykładowy opis", PlayMode.ONEVSONE,TournamentType.LOCAL,"Gdzieś tam",
                2, 6);
        user.addOwnedTournament(tournament);
        userDao.save(user);

        Date startDate2 = sf.parse("8-04-2018");
        Date endDate2 = sf.parse("30-04-2018");
        User user2 = new User("Tester Testowy2");
        Tournament tournament2 = new Tournament("Testowy turniej2", startDate2, endDate2,
                "Przykładowy opis",PlayMode.TWOVSTWO, TournamentType.VIRTUAL,
                4, 16);
        user2.addOwnedTournament(tournament2);
        userDao.save(user2);

        Date startDate3 = sf.parse("10-04-2018");
        Date endDate3 = sf.parse("27-04-2018");
        User user3 = new User( "Tester Testowy3");
        Tournament tournament3 = new Tournament("Testestest3", startDate3, endDate3,
                "",PlayMode.TWOVSTWO, TournamentType.VIRTUAL,
                4, 16);
        user3.addOwnedTournament(tournament3);
        userDao.save(user3);

    }
}
