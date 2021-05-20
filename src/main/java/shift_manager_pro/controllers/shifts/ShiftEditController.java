package shift_manager_pro.controllers.shifts;
import shift_manager_pro.dao.*;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import shift_manager_pro.utils.Views;

import java.util.Map;

public class ShiftEditController implements Handler{
    static final String PATH = Views.templatePath("manager/shifts/edit.html");

    @Override
    
    public void handle(@NotNull Context ctx) throws Exception {
        Map<String, Object> model = Views.baseModel(ctx);
        model.put("shift", ShiftDao.INSTANCE.getById(ctx.pathParam("shift_id", Long.class).get()));
        model.put("users", UserDao.INSTANCE.getAll());
        ctx.render(PATH, model);
    }
}
