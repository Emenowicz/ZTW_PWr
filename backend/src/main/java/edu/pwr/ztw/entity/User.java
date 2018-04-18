package edu.pwr.ztw.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class User implements Serializable {
    @Id
    private String id;
    private String name;
    private boolean admin = false;
    @Lob
    private byte[] avatar;

    @OneToMany
    private Set<Tournament> ownedTournaments;
    @OneToMany
    private Set<Team> teams;

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

}
