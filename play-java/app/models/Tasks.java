package models;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.validation.*;

@Entity
public class Tasks extends Model {
    @Id
    public Long id;

    @Constraints.Required
    public String contact;

    @ManyToOne
    public Outreachteam outreachteam;

    public Instant created_at;

    public Instant dispatched_at;

    public Instant received_at;

    public String status;

    public static Model.Finder<Long,Tasks> find = new Model.Finder<Long,Tasks>(Long.class, Tasks.class);

    public static Optional<Tasks> findByJobId(Long id) {
        return Optional.ofNullable(find.where().eq("id", id).findUnique());
    }

    public static List<Tasks> findByContact(String contact) {
        return find.where().eq("contact", contact).findList();
    }

    public static List<Tasks> findByOutreachTeamId(Long outreachId) {
        return find.where().eq("outreach_id", outreachId).findList();
    }
}
