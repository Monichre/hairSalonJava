import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import java.util.ArrayList;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import org.sql2o.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

  get("/", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    List<Stylist> stylists = Stylist.all();
    model.put("stylists", stylists);
    model.put("template", "templates/home.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/booking", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    List<Stylist> stylists = Stylist.all();
    String userInputStylistName = request.queryParams("stylistName");
    Stylist newStylist = new Stylist(userInputStylistName);
    newStylist.save();
    String clientName = request.queryParams("clientName");
    Client newClient = new Client(clientName, newStylist.getId());
    newClient.save();
    model.put("stylists", stylists);
    model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());




}
}