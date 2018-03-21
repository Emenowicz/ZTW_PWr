package edu.pwr.ztw.entity;

import edu.pwr.ztw.entity.Enums.MatchRank;
import edu.pwr.ztw.entity.Enums.PlaysToWin;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Match implements Serializable {
    @Id
    private long id;
    @OneToOne
    private Team teamBlue;
    @OneToOne
    private Team teamRed;
    private PlaysToWin playsToWin;
    private MatchRank matchRank;
    @OneToMany
    private Set<Round> rounds;
    @ManyToOne
    private Tournament tournament;
}
