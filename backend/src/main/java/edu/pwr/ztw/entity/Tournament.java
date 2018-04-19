package edu.pwr.ztw.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.pwr.ztw.entity.Enums.PlayMode;
import edu.pwr.ztw.entity.Enums.TournamentType;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Entity
public class Tournament implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date startTime;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date endTime;
    private String description;
    @Enumerated
    private PlayMode playMode;
    @Enumerated
    private TournamentType tournamentType;
    private String location;
    private int minTeams;
    private int maxTeams;
    @ManyToOne
    @JsonIgnoreProperties("ownedTournaments")
    private User owner;
    @ManyToMany
    private Set<Team> teams;
    @OneToMany
    private Set<Match> matches;

    public Tournament(){

    }

    public Tournament(String name, Date startTime, Date endTime, int minTeams, int maxTeams) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.minTeams = minTeams;
        this.maxTeams = maxTeams;
    }

    public Tournament(String name, Date startTime, Date endTime, String description, PlayMode playMode, int minTeams, int maxTeams) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.playMode = playMode;
        this.minTeams = minTeams;
        this.maxTeams = maxTeams;
    }

    public Tournament(String name, Date startTime, Date endTime, String description, PlayMode playMode, TournamentType tournamentType, String location, int minTeams, int maxTeams) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.playMode = playMode;
        this.tournamentType = tournamentType;
        this.location = location;
        this.minTeams = minTeams;
        this.maxTeams = maxTeams;
    }

    public Tournament(String name, Date startTime, Date endTime, String description, PlayMode playMode, TournamentType tournamentType, int minTeams, int maxTeams) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.playMode = playMode;
        this.tournamentType = tournamentType;
        this.minTeams = minTeams;
        this.maxTeams = maxTeams;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getMinTeams() {
        return minTeams;
    }

    public void setMinTeams(int minTeams) {
        this.minTeams = minTeams;
    }

    public int getMaxTeams() {
        return maxTeams;
    }

    public void setMaxTeams(int maxTeams) {
        this.maxTeams = maxTeams;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<Match> getMatches() {
        return matches;
    }

    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PlayMode getPlayMode() {
        return playMode;
    }

    public void setPlayMode(PlayMode playMode) {
        this.playMode = playMode;
    }

    public TournamentType getTournamentType() {
        return tournamentType;
    }

    public void setTournamentType(TournamentType tournamentType) {
        this.tournamentType = tournamentType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void addOwner(User user){
        this.setOwner(user);
        user.getOwnedTournaments().add(this);
    }
}
