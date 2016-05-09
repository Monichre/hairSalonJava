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
    model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/booking", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    List<Stylist> stylists = Stylist.all();
    model.put("stylists", stylists);
    model.put("template", "templates/stylists.vtl");
      return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/stylistClients/:id", (request, response) -> {
    HashMap model = new HashMap();
    Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
    model.put("stylist", stylist);
    model.put("template", "templates/addClient.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/stylistClients/addClient/:id", (request, response) -> {
    HashMap model = new HashMap();
    Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
    String newClientName = request.queryParams("addClient");
    Client newClient = new Client(newClientName, stylist.getId());
    newClient.save();
    model.put("stylist", stylist);
    model.put("template", "templates/addClient.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());



  }
}