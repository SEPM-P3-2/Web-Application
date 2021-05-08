package shift_manager_pro.controllers.shifts;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import shift_manager_pro.auth.AccessManager;
import shift_manager_pro.dao.*;
import shift_manager_pro.models.*;
import shift_manager_pro.utils.Views;

public class ShiftRejectController implements Handler {

  static final String PATH = Views.templatePath("employee/shifts/reject.html");

  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    Shift shift = ShiftDao.INSTANCE.getById(
      ctx.pathParam("shift_id", Long.class).get()
    );
    shift.setStatus("REJECTED");
    ShiftDao.INSTANCE.updateShift(shift);

    Map<String, Object> model = Views.baseModel(ctx);
    ctx.render(PATH, model);
  }
}
