package shift_manager_pro.controllers.shifts;

import org.jetbrains.annotations.NotNull;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import shift_manager_pro.dao.ShiftDao;
import shift_manager_pro.dao.UserDao;
import shift_manager_pro.models.Shift;
import shift_manager_pro.models.User;

public class ShiftRejectController implements Handler {

  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    Shift shift = ShiftDao.INSTANCE.getById(
      ctx.pathParam("shift_id", Long.class).get()
    );
    shift.setStatus("REJECTED");
    User user = UserDao.INSTANCE.get(Long.valueOf(ctx.formParam("user_id")));
    user.setCurrent_working_hour(user.getCurrent_working_hour()+shift.getDuration());
    UserDao.INSTANCE.updateUser(user);
    ShiftDao.INSTANCE.updateShift(shift);
    ctx.redirect("/view_my_shifts");
  }
}
