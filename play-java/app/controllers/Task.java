package controllers;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Message;
import models.Tasks;
import org.joda.time.DateTime;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Controller;
import views.html.index;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

public class Task extends Controller {

    public Result fromMessage(String sender, String incomingMessage, Double lat, Double lng) {

        //Tasks pendingTask = null;
        return Ebean.execute(() -> {
            Message message = new Message();
            message.body = incomingMessage;
            message.contact = sender;
            message.lat = lat;
            message.lng = lng;
            message.arrived_at = new Timestamp(DateTime.now().getMillis());
            message.save();
            Tasks pendingTask = Tasks.find.where().eq("contact", sender).eq("status", "PENDING").findUnique();
            if (pendingTask == null) {
                Tasks task = new Tasks();
                task.contact = sender;
                task.created_at = Instant.now();
                task.status = "PENDING";
                task.save();
            }
            final ObjectNode response = Json.newObject();
            Long taskId = pendingTask.id;
            response.put("dispatchURL", "tasks/completed/" + taskId);
            response.put("deliveredURL", "tasks/delivered/" + taskId);
            response.put("completedURL", "tasks/completed/" + taskId);

            return ok(response);
        });

        // return ok(index.render("Welcome to the UNICEF vaccine delivery dashboard!", 1, 2, 3));
    }

    public Result dispatch(Long taskId) {

        try {
            Ebean.execute(() -> {
                Optional<Tasks> taskOpt = Tasks.findByJobId(taskId);
                if (taskOpt.isPresent()) {
                    Tasks task = taskOpt.get();
                    task.dispatched_at = Instant.now();
                    task.status = "DISPATCHED";
                    task.update();
                } else {
                    throw new IllegalArgumentException("Unknown task id: " + taskId);
                }
            });
        } catch (IllegalArgumentException e) {
            return notFound();
        }

        return ok();
    }

    public Result delivered(Long taskId) {

        try {
            Ebean.execute(() -> {
                Optional<Tasks> taskOpt = Tasks.findByJobId(taskId);
                if (taskOpt.isPresent()) {
                    Tasks task = taskOpt.get();
                    task.received_at = Instant.now();
                    task.status = "DELIVERED";
                    task.update();
                } else {
                    throw new IllegalArgumentException("Unknown task id: " + taskId);
                }
            });
        } catch (IllegalArgumentException e) {
            return notFound();
        }

        return ok();
    }

    public Result completed(Long taskId) {

        try {
            Ebean.execute(() -> {
                Optional<Tasks> taskOpt = Tasks.findByJobId(taskId);
                if (taskOpt.isPresent()) {
                    Tasks task = taskOpt.get();
                    task.status = "COMPLETED";
                    task.update();
                } else {
                    throw new IllegalArgumentException("Unknown task id: " + taskId);
                }
            });
        } catch (IllegalArgumentException e) {
            return notFound();
        }

        return ok();
    }


}
