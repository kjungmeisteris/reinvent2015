package controllers;

import models.Tasks;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public Result index() {
        int pending = Tasks.find.where().eq("status", "PENDING").findRowCount();
        int completed = Tasks.find.where().eq("status", "COMPLETED").findRowCount();
        int failed = Tasks.find.where().eq("status", "FAILED").findRowCount();
        return ok(index.render(pending, completed, failed));
    }

}
