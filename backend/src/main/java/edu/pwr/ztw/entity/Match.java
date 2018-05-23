package edu.pwr.ztw.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.pwr.ztw.entity.Enums.MatchRank;
import edu.pwr.ztw.entity.Enums.PlaysToWin;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @ManyToOne
    @JsonIgnoreProperties({"matches"})
    private Tournament tournament;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false,updatable = false)
    private Date createdDate;
    @Min(0)
    @Max(3)
    private int scoreBlue;
    @Min(0)
    @Max(3)
    private int scoreRed;

    @ManyToOne
    @JsonIgnoreProperties({"teams","ownedTournaments","joinedTournaments"})
    private User scoreUpdatedBy;

    private boolean acceptedBlue;
    private boolean acceptedRed;



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

    public int getScoreBlue() {
        return scoreBlue;
    }

    public void setScoreBlue(int scoreBlue) {
        this.scoreBlue = scoreBlue;
    }

    public int getScoreRed() {
        return scoreRed;
    }

    public void setScoreRed(int scoreRed) {
        this.scoreRed = scoreRed;
    }

    public boolean isAcceptedBlue() {
        return acceptedBlue;
    }

    public void setAcceptedBlue(boolean acceptedBlue) {
        this.acceptedBlue = acceptedBlue;
    }

    public boolean isAcceptedRed() {
        return acceptedRed;
    }

    public void setAcceptedRed(boolean acceptedRed) {
        this.acceptedRed = acceptedRed;
    }

    public boolean hasScore(){
        return scoreBlue!=0 && scoreRed!=0;
    }

    public User getScoreUpdatedBy() {
        return scoreUpdatedBy;
    }

    public void setScoreUpdatedBy(User scoreUpdatedBy) {
        this.scoreUpdatedBy = scoreUpdatedBy;
    }

    @PrePersist
    private void onCreate(){
        this.setCreatedDate(new Timestamp((new Date()).getTime()));
    }
}
