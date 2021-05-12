package shift_manager_pro.controllers.shifts;

import shift_manager_pro.dao.UserDao;
import shift_manager_pro.models.Shift;
import shift_manager_pro.utils.Views;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Controller to render the New form. The Model creation (POST action of form) is handled by {@link ShiftCreateController}
 */
public class ShiftNewController implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Map<String, Object> model = Views.baseModel(ctx);
        model.put("shift", new Shift());
        model.put("users", UserDao.INSTANCE.getAll());
        ctx.render("/views/manager/shifts/new.html", model);
    }
}

