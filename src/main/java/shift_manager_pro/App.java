package shift_manager_pro;

import shift_manager_pro.auth.AccessManager;
import shift_manager_pro.auth.LoginController;
import shift_manager_pro.controllers.HomeController;
import shift_manager_pro.controllers.shifts.ViewShiftsController;
import shift_manager_pro.controllers.users.CreateAccountController;
import shift_manager_pro.models.Role;
import shift_manager_pro.utils.Views;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;

import java.time.LocalDateTime;
import java.util.Date;

import static io.javalin.core.security.SecurityUtil.roles;

public class App {


    public static void main(String[] args) {
        //Create our HTTP server and listen in port 7000
        Javalin app = Javalin.create(config -> {
            config.enableDevLogging();
            config.registerPlugin(new RouteOverviewPlugin("/help/routes"));
            config.addStaticFiles("public/");
            config.accessManager(new AccessManager());
        }).start(7000);

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

        app.get("/view_shifts", new ViewShiftsController(), roles(Role.REGISTERED)); // only registered users may view shifts

        //Auth
        app.get("/login", ctx -> {
            ctx.render("/views/auth/login.html", Views.baseModel(ctx));
        });

        app.post("auth", new LoginController());
        app.get("/logout", ctx -> {
            AccessManager.logout(ctx);
            ctx.redirect("/");
        });

        app.post("/users", new CreateAccountController(), roles(Role.ADMIN));//Secured for ADMINs only

    }


}
