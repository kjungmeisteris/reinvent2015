package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

 
@Entity 
public class Message extends Model {
	private static final long serialVersionUID = 1L;

	@Id
    public Long id;
    
    @Constraints.Required
    public String contact;

    @Constraints.Required
    public String arrivaldate;

    public String body;

    public Double lat;

    public Double lng;
    
    /**
     * Generic query helper for entity Company with id Long
     */
    public static Model.Finder<Long,Message> find = new Model.Finder<Long,Message>(Long.class, Message.class);
}