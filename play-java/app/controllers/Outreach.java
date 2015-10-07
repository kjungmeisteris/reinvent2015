package controllers;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Outreachteam;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import views.html.index;

import java.util.List;

public class Outreach extends Controller {
    public Result getAll() {
        List<Outreachteam> outreachteams = Outreachteam.all();

        final ObjectNode outreachteamsRes = Json.newObject();

        final ArrayNode orarray = JsonNodeFactory.instance.arrayNode();

        for (final Outreachteam or : outreachteams) {
            final ObjectNode country = Json.newObject();
            country.put("id", or.id);
            country.put("name", or.name);
            country.put("contact", or.contact);
            orarray.add(country);
        }

        outreachteamsRes.put("outreachteams", orarray);
        return ok(outreachteamsRes);

    }
}