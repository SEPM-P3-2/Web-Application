package shift_manager_pro.controllers.home;

import shift_manager_pro.utils.Views;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.LocalDateTime;
import java.util.Map;

public class ShiftAllocateController implements Handler {

    public static final String URL = "/";

    static final String TEMPLATE = Views.templatePath("home/index.html");

    @Override
    public void handle(@NotNull Context context) throws Exception {





        

        Map<String, Object> model = Views.baseModel(context);
        context.render(TEMPLATE, model);
    }





}
