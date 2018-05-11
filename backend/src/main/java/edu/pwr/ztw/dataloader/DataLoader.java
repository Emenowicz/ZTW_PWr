package edu.pwr.ztw.dataloader;

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
import java.util.*;

@Component
public class DataLoader implements ApplicationRunner {

    @Resource
    UserDao userDao;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        String[] tournamentNames = {"Wyborna rozgrywka Kielce", "Nowa nazwa turnieju", "Robimy turniej", "Soccer 11", "MiniSoccer18", "Fajna gra", "Turniej Polski", "Turniej Czeski", "Weekendowe granie", "Na śmierć i życie", "Teksańska masakra grillem piłkarzykowym", "Piłkarzyki 2011", "Firmowe granie", "PWr piłkarzyki", "Zagrajmy w grę"};

        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
        Date[] startDates = {sf.parse("19-04-2018"), sf.parse("19-05-2018"), sf.parse("10-05-2018"), sf.parse("21-05-2018"), sf.parse("19-06-2018"), sf.parse("30-06-2018"), sf.parse("27-06-2018")};
        String[] usersNames = {"Marek Konieczny", "Jan Kowalski", "Marek Kark", "Paweł Zając"};
        String[] usersIds = {"213123", "2342342", "21553345", "5556456"};
        LinkedList<User> users = new LinkedList<>();

        for (int i = 0; i < usersNames.length; i++) {
            users.add(new User(usersIds[i], usersNames[i]));
        }

        int i = 0;
        User konrad = new User("116248526006631545152", "Konrad Drozd");
        for (String tournamentName : tournamentNames) {
            Date startDate = startDates[randBetween(0, startDates.length - 1)];
            Date endDate = addDays(startDate, randBetween(10, 30));

            Tournament tournament = new Tournament(tournamentName, startDate, endDate,
                    "Przykładowy opis " + randBetween(10, 100), PlayMode.ONEVSONE, TournamentType.LOCAL, "Gdzieś tam",
                    2, 6);

            if (i++ % 3 == 0) {
                konrad.addOwnedTournament(tournament);
            } else {
                users.get(randBetween(0, users.size() - 1)).addOwnedTournament(tournament);
            }
        }
        users.add(konrad);
        users.forEach(u -> userDao.save(u));
    }

    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}