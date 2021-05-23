package shift_manager_pro.controllers.availability;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import shift_manager_pro.auth.AccessManager;
import shift_manager_pro.dao.AvailabilityDao;
import shift_manager_pro.dao.UserDao;
import shift_manager_pro.utils.Views;

import java.util.Map;

public class ViewAllAvailabilitiesController implements Handler {

    static final String PATH = Views.templatePath("manager/availabilities/index.html");

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Map<String, Object> model = Views.baseModel(ctx);
        model.put(
                "users",
                UserDao.INSTANCE.getAll()
        );
        ctx.render(PATH, model);
    }
}