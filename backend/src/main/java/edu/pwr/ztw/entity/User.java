package edu.pwr.ztw.entity;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
public class User implements Serializable {
    @Id
    private long id;
    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    @Email
    private String email;
    private boolean admin = false;
    @Lob
    private byte[] avatar;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    @OneToMany
    private Set<Tournament> ownedTournaments;
    @OneToMany
    private Set<Team> teams;


}
