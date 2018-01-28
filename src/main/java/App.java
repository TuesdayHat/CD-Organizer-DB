
import models.CD;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
//            Map<String, ArrayList<CD>> model = new HashMap<>();
            ArrayList<CD> allCD = CD.getInstances();
            model.put("AllCD", allCD);
//            System.out.println(model.get("AllCD"));

            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        post("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String album = request.queryParams("album");
            String artist = request.queryParams("artist");

            ArrayList<CD> allCDs= CD.getInstances();

            CD newCD = new CD(album, artist);
            model.put("allCDs", allCDs);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }

}
