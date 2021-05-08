package shift_manager_pro.controllers.shifts;
import shift_manager_pro.dao.ShiftDao;
import shift_manager_pro.models.Shift;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import shift_manager_pro.utils.Views;

import java.util.Map;

public class ShiftEditController implements Handler{
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Map<String, Object> model = Views.baseModel(ctx);
        model.put("program", ShiftDao.INSTANCE.getById(ctx.pathParam("id", Long.class).get()));
        ctx.render("/views/manager/shifts/edit.html", model);
    }
}
