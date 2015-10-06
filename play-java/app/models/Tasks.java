package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Tasks extends Model {
    @Id
    public Long id;

    @Constraints.Required
    public String contact;

    public Long outreach_id;

    public String created_at;

    public String dispatched_at;

    public String received_at;

    public String status;

    public static Model.Finder<Long,Tasks> find = new Model.Finder<Long,Tasks>(Long.class, Tasks.class);
}