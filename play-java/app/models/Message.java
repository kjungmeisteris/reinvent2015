package models;

import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.validation.*;

import java.sql.Timestamp;

@Entity
@Table(name="messages")
public class Message extends Model {
	private static final long serialVersionUID = 1L;

	@Id
    public Long id;
    
    @Constraints.Required
    public String contact;

    @Constraints.Required
    public Timestamp arrived_at;

    @Constraints.Required
    public String body;

    public Double lat;

    public Double lng;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Message> find = new Model.Finder<Long,Message>(Long.class, Message.class);
}
