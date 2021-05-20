package shift_manager_pro.controllers.shifts;

import java.util.Map;

import org.jetbrains.annotations.NotNull;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import shift_manager_pro.auth.AccessManager;
import shift_manager_pro.dao.ShiftDao;
import shift_manager_pro.utils.Views;

public class ViewShiftsHistoryController implements Handler {

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
