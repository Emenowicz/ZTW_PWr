package edu.pwr.ztw.entity;

import edu.pwr.ztw.entity.Enums.MatchRank;
import edu.pwr.ztw.entity.Enums.PlaysToWin;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Entity
public class Match implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Team teamBlue;
    @OneToOne
    private Team teamRed;
    @Enumerated
    private PlaysToWin playsToWin;
    @Enumerated
    private MatchRank matchRank;
    @OneToMany
    private Set<Round> rounds;
    @ManyToOne
    private Tournament tournament;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false,updatable = false)
    private Date createdDate;

    public Match(long id, Team teamBlue, Team teamRed, PlaysToWin playsToWin, MatchRank matchRank, Set<Round> rounds, Tournament tournament, Date createdDate) {
        this.id = id;
        this.teamBlue = teamBlue;
        this.teamRed = teamRed;
        this.playsToWin = playsToWin;
        this.matchRank = matchRank;
        this.rounds = rounds;
        this.tournament = tournament;
        this.createdDate = createdDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Team getTeamBlue() {
        return teamBlue;
    }

    public void setTeamBlue(Team teamBlue) {
        this.teamBlue = teamBlue;
    }

    public Team getTeamRed() {
        return teamRed;
    }

    public void setTeamRed(Team teamRed) {
        this.teamRed = teamRed;
    }

    public PlaysToWin getPlaysToWin() {
        return playsToWin;
    }

    public void setPlaysToWin(PlaysToWin playsToWin) {
        this.playsToWin = playsToWin;
    }

    public MatchRank getMatchRank() {
        return matchRank;
    }

    public void setMatchRank(MatchRank matchRank) {
        this.matchRank = matchRank;
    }

    public Set<Round> getRounds() {
        return rounds;
    }

    public void setRounds(Set<Round> rounds) {
        this.rounds = rounds;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @PrePersist
    private void onCreate(){
        this.setCreatedDate(new Timestamp((new Date()).getTime()));
    }
}
