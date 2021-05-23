package shift_manager_pro;

import static io.javalin.core.security.SecurityUtil.roles;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import shift_manager_pro.auth.AccessManager;
import shift_manager_pro.auth.LoginController;
import shift_manager_pro.auth.RegisterController;
import shift_manager_pro.controllers.HomeController;
import shift_manager_pro.controllers.availability.AvailabilityCreateController;
import shift_manager_pro.controllers.availability.ViewAvailabilitiesController;
import shift_manager_pro.controllers.shifts.*;
import shift_manager_pro.models.Role;
import shift_manager_pro.utils.Views;

public class App {

  public static void main(String[] args) {
    //Create our HTTP server and listen in port 7000
    Javalin app = Javalin
      .create(
        config -> {
          config.enableDevLogging();
          config.registerPlugin(new RouteOverviewPlugin("/help/routes"));
          config.addStaticFiles("public/");
          config.accessManager(new AccessManager());
          
        }
      )
      .start(7000);

    //Register the engine to process html views
    JavalinRenderer.register(JavalinThymeleaf.INSTANCE, ".html");

    //Configure Web Routes
    configureRoutes(app);

    LocalDateTime d = LocalDateTime.of(2020, 7, 13, 14, 45, 7);
    System.out.print(d.toString());
  }

  public static void configureRoutes(Javalin app) {
    // Routing
    app.get(HomeController.URL, new HomeController());

    app.get("/shifts/new", new ShiftNewController(), roles(Role.MANAGER));

    app.post(
      "/shifts/create",
      new ShiftCreateController(),
      roles(Role.MANAGER)
    );

    app.get(
      "/shifts/:shift_id/edit",
      new ShiftEditController(),
      roles(Role.MANAGER)
    );

    app.post(
      "/shifts/:shift_id/update",
      new ShiftUpdateController(),
      roles(Role.MANAGER)
    );

    app.get(
      "/shifts/:shift_id/delete",
      new ShiftDeleteController(),
      roles(Role.MANAGER)
    );

    app.get(
      "/view_availabilities",
      new ViewAvailabilitiesController(),
      roles(Role.EMPLOYEE, Role.MANAGER)
    );
    app.get(
      "/availabilities/new",
      ctx -> {
        ctx.render(
          "/views/employee/availabilities/new.html",
          Views.baseModel(ctx)
        );
      },
      roles(Role.EMPLOYEE, Role.MANAGER)
    );
    app.post("availabilities/new", new AvailabilityCreateController());

    // View all shifts (only for managers)
    app.get("/view_all_shifts", new ViewAllShiftsController()); // only registered users may view shifts

    // Allocate shifts
    app.get(
      "/allocate/:user_id/:shift_id",
      new ShiftAllocateController()
    );

    // Accept shifts
    app.get(
      "/allocate/:user_id/:shift_id/accept",
      new ShiftAcceptController(),
      roles(Role.MANAGER, Role.EMPLOYEE)
    );

    // Reject shifts
    app.get(
      "/allocate/:user_id/:shift_id/reject",
      new ShiftRejectController(),
      roles(Role.MANAGER, Role.EMPLOYEE)
    );

    // only registered users may view shifts
    app.get(
      "/shift_preferences",
      ctx -> {
        ctx.render(
          "/views/employee//shifts/calendar.html",
          Views.baseModel(ctx)
        );
      }
    );
    //Auth
    app.get(
      "/login",
      ctx -> {
        ctx.render("/views/auth/login.html", Views.baseModel(ctx));
      }
    );

    app.get(
      "/register",
      ctx -> {
        Map<String, Object> model = Views.baseModel(ctx);
        model.put("user", AccessManager.getSessionCurrentUser(ctx));
        ctx.render(Views.templatePath("auth/register.html"), model);
      }
    );

    app.post("/register", new RegisterController());

    app.post("auth", new LoginController());
    app.get(
      "/logout",
      ctx -> {
        AccessManager.logout(ctx);
        ctx.redirect("/");
      }
    );
  }
}
