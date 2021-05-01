package shift_manager_pro.controllers.shifts;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import shift_manager_pro.auth.AccessManager;
import shift_manager_pro.dao.ShiftDao;
import shift_manager_pro.dao.UserDao;
import shift_manager_pro.models.Role;
import shift_manager_pro.utils.Views;

public class ViewShiftsController implements Handler {

  static final String PATH = Views.templatePath("employee/shifts/list.html");

  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    Map<String, Object> model = Views.baseModel(ctx);
    model.put(
      "shifts",
      ShiftDao.INSTANCE.getByUserId(
        AccessManager.getSessionCurrentUser(ctx).getId()
      )
    );
    ctx.render(PATH, model);
  }
}
