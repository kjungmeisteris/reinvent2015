package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public Result index() {
        return ok(index.render("Welcome to the UNICEF vaccine delivery dashboard!", 1, 2, 3));
    }

}
