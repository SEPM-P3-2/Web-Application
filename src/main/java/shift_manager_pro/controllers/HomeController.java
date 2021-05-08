package shift_manager_pro.controllers;

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
    public void handle(@NotNull Context ctx) throws Exception {
        Map<String, Object> model = Views.baseModel(ctx);
        model.put("date", new Date());
        ctx.render(TEMPLATE, model);
    }
}
