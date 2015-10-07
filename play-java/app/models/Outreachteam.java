package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.validation.*;

@Entity
public class Outreachteam extends Model {
    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String contact;

    public static Model.Finder<Long,Outreachteam> find = new Model.Finder<>(Long.class, Outreachteam.class);

    public static List<Outreachteam> all() {
        return find.all();
    }
}