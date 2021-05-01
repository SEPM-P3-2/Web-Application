package shift_manager_pro.controllers.shifts;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import shift_manager_pro.auth.AccessManager;
import shift_manager_pro.dao.ShiftDao;
import shift_manager_pro.dao.UserDao;
import shift_manager_pro.models.Role;
import shift_manager_pro.utils.Views;

import java.util.Map;

public class ViewShiftsController implements Handler {

    static final String PATH = Views.templatePath("shifts/list.html");

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        if(AccessManager.getSessionCurrentUser(ctx).getRole() != Role.REGISTERED)
            return;

        Map<String, Object> model = Views.baseModel(ctx);
        model.put("user", UserDao.INSTANCE.get(AccessManager.getSessionCurrentUser(ctx).getId()));
        model.put("shifts", ShiftDao.INSTANCE.getByUserId(AccessManager.getSessionCurrentUser(ctx).getId()));
        ctx.render(PATH, model);
    }
}
