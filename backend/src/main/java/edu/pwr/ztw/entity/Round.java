package edu.pwr.ztw.entity;

import edu.pwr.ztw.entity.Enums.TeamColors;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Round implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Min(0)
    @Max(10)
    private int blueScore;
    @Min(0)
    @Max(10)
    private int redScore;
    private boolean approved;
    @Enumerated
    private TeamColors winnerColor;
    @ManyToOne
    private Match match;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false,updatable = false)
    private Date createdDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBlueScore() {
        return blueScore;
    }

    public void setBlueScore(int blueScore) {
        this.blueScore = blueScore;
    }

    public int getRedScore() {
        return redScore;
    }

    public void setRedScore(int redScore) {
        this.redScore = redScore;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public TeamColors getWinnerColor() {
        return winnerColor;
    }

    public void setWinnerColor(TeamColors winnerColor) {
        this.winnerColor = winnerColor;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
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
