package edu.pwr.ztw.entity;

import edu.pwr.ztw.entity.Enums.TeamColors;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
@Entity
public class Round implements Serializable{
    @Id
    private long id;
    @Min(0)
    @Max(10)
    private int blueScore;
    @Min(0)
    @Max(10)
    private int redScore;
    private boolean approved;
    private TeamColors winnerColor;
    @ManyToOne
    private Match match;

}
