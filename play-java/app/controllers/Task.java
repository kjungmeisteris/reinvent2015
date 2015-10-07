package controllers;

import com.avaje.ebean.Ebean;
import models.Message;
import models.Tasks;
import play.mvc.Result;
import play.mvc.Controller;
import views.html.index;

import java.time.Instant;

public class Task extends Controller {

    public Result fromMessage(String sender, String incomingMessage, Double lat, Double lng) {

        Ebean.execute(() -> {
            Message message = new Message();
            message.arrivaldate = Instant.now();
            message.body = incomingMessage;
            message.contact = sender;
            message.lat = lat;
            message.lng = lng;
            message.save();
            Tasks pendingTask = Tasks.find.where().eq("sender", sender).eq("status", "PENDING").findUnique();
            if (pendingTask == null) {
                Tasks task = new Tasks();
                task.contact = sender;
                task.created_at = Instant.now();
                task.status = "PENDING";
                task.save();
            }
        });

        return ok(index.render("Welcome to the UNICEF vaccine delivery dashboard!", 1, 2, 3));
    }
}
