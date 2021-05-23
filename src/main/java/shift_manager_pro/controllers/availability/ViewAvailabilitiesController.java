package shift_manager_pro.controllers.availability;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import shift_manager_pro.auth.AccessManager;
import shift_manager_pro.dao.AvailabilityDao;
import shift_manager_pro.dao.UserDao;
import shift_manager_pro.models.Role;
import shift_manager_pro.utils.Views;

import java.util.Map;

public class ViewAvailabilitiesController implements Handler {

    static final String PATH = Views.templatePath("employee/availabilities/list.html");

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Long id = ctx.pathParam("id", Long.class).get();

        if(AccessManager.getSessionCurrentUser(ctx).getRole() != Role.MANAGER) {
            if(id != AccessManager.getSessionCurrentUser(ctx).getId()) {
                id = AccessManager.getSessionCurrentUser(ctx).getId();
            }
        }

        boolean current = id == AccessManager.getSessionCurrentUser(ctx).getId();

        Map<String, Object> model = Views.baseModel(ctx);
        model.put(
                "availabilities",
                AvailabilityDao.INSTANCE.getByUserID(
                        id
                )
        );
        model.put(
                "user",
                UserDao.INSTANCE.get(
                        id
                )
        );
        model.put("current", current);
        ctx.render(PATH, model);
    }
}