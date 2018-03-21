package edu.pwr.ztw.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
public class Tournament implements Serializable {
    @Id
    private long id;
    private String name;
    private Date startTime;
    private Date endTime;
    private int minTeams;
    private int maxTeams;
    @ManyToOne
    private User owner;
    @ManyToMany
    private Set<Team> teams;
    @OneToMany
    private Set<Match> matches;


}
