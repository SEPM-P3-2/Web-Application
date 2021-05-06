package shift_manager_pro.controllers.shifts;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import shift_manager_pro.dao.*;
import shift_manager_pro.utils.Views;

import java.util.Map;

public class ViewAllShiftsController implements Handler {

    static final String PATH = Views.templatePath("manager/shifts/list.html");

    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        Map<String, Object> model = Views.baseModel(ctx);
        model.put("shifts", ShiftDao.INSTANCE.getFromNow());
        model.put("users", UserDao.INSTANCE.getAll());
        ctx.render(PATH, model);
    }
}
