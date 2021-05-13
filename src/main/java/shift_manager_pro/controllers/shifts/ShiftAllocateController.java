package shift_manager_pro.controllers.shifts;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import shift_manager_pro.dao.*;
import shift_manager_pro.models.*;

public class ShiftAllocateController implements Handler {
  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    User user = UserDao.INSTANCE.get(
      ctx.pathParam("user_id", Long.class).get()
    );
    Shift shift = ShiftDao.INSTANCE.getById(
      ctx.pathParam("shift_id", Long.class).get()
    );
    shift.setUser_id(user.getId());
    shift.setStatus("PENDING");

    ShiftDao.INSTANCE.updateShift(shift);

    ctx.redirect("/view_all_shifts");
  }
}
