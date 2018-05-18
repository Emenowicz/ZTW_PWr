package edu.pwr.ztw.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements Serializable {
    @Id
    private String id;
    @NotBlank
    private String name;
    private boolean admin = false;
    @Lob
    private byte[] avatar;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"owner","teams","matches","players"})
    private Set<Tournament> ownedTournaments = new HashSet<>();
    @OneToMany
    @JsonIgnoreProperties({"matches"})
    private Set<Team> teams = new HashSet<>();

    @ManyToMany
    @JsonIgnoreProperties("players")
    private Set<Tournament> joinedTournaments = new HashSet<>();

    public User(){

    }

    public User(String name) {
        this.name = name;
    }

    public User(String id, String name) {
        this.id=id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public Set<Tournament> getOwnedTournaments() {
        return ownedTournaments;
    }

    public void setOwnedTournaments(Set<Tournament> ownedTournaments) {
        this.ownedTournaments = ownedTournaments;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public void addOwnedTournament(Tournament tournament){
        ownedTournaments.add(tournament);
        tournament.setOwner(this);
    }

    public Set<Tournament> getJoinedTournaments() {
        return joinedTournaments;
    }

    public void setJoinedTournaments(Set<Tournament> joinedTournaments) {
        this.joinedTournaments = joinedTournaments;
    }

    public void joinToTournament(Tournament tournament){
        joinedTournaments.add(tournament);
        tournament.getPlayers().add(this);
    }
}
