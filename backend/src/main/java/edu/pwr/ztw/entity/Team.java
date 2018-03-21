package edu.pwr.ztw.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Team {
    @Id
    private long id;
    private byte[] avatar;
    private String name;
    @ManyToOne
    private User playerOne;
    @ManyToOne
    private User playerTwo;
    @OneToMany
    private Set<Match> matches;
    @ManyToMany
    private Set<Tournament> tournaments;
}
