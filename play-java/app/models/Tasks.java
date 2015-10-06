package models;

import java.time.Instant;
import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Tasks extends Model {
    @Id
    public Long id;

    @OneToOne
    @Constraints.Required
    public Message message;

    @ManyToOne
    public Outreachteam outreachteam;

    public Instant created_at;

    public Instant dispatched_at;

    public Instant received_at;

    public String status;

    public static Model.Finder<Long,Tasks> find = new Model.Finder<Long,Tasks>(Long.class, Tasks.class);
}
