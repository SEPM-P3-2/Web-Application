package shift_manager_pro.controllers.home;

import shift_manager_pro.utils.Views;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Map;

public class HomeController implements Handler {

    public static final String URL = "/";

    static final String TEMPLATE = Views.templatePath("home/index.html");

    @Override
    public void handle(@NotNull Context context) throws Exception {
        Map<String, Object> model = Views.baseModel(context);
        model.put("date", new Date());
        context.render(TEMPLATE, model);
    }
}
