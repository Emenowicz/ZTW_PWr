package edu.pwr.ztw.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.pwr.ztw.entity.Enums.MatchRank;
import edu.pwr.ztw.entity.Enums.PlaysToWin;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity
public class Match implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"matches"})
    private Team teamBlue;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"matches"})
    private Team teamRed;
    @Enumerated
    private MatchRank matchRank;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"match"})
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
//        teamBlue.getMatches().add(this);
    }

    public Team getTeamRed() {
        return teamRed;
    }

    public void setTeamRed(Team teamRed) {
//        teamRed.getMatches().add(this);
        this.teamRed = teamRed;
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
        rounds.forEach(round -> round.setMatch(this));
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
