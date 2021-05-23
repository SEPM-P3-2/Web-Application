package shift_manager_pro.controllers.shifts;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.time.LocalDateTime;
import java.time.temporal.*;
import org.jetbrains.annotations.NotNull;
import shift_manager_pro.dao.*;
import shift_manager_pro.models.*;
import shift_manager_pro.utils.EmailSender;
import shift_manager_pro.utils.MessageSender;

public class ShiftCancelController implements Handler {

  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    Shift shift = ShiftDao.INSTANCE.getById(
      ctx.pathParam("shift_id", Long.class).get()
    );
    shift.setStatus("CANCELED");
    User user = UserDao.INSTANCE.get(ctx.pathParam("user_id", Long.class).get());
    user.setCurrent_working_hour(user.getCurrent_working_hour()-shift.getDuration());
    UserDao.INSTANCE.updateWorkingHour(user);
    ShiftDao.INSTANCE.updateShift(shift);
    EmailSender.cancelShiftEmailSender(UserDao.INSTANCE.getManagers(), shift);
    if (
      shift.getStartTime().until(LocalDateTime.now(), ChronoUnit.HOURS) <= 2
    ) {
      MessageSender.cancelShiftMessageSender(
        UserDao.INSTANCE.getManagers(),
        shift
      );
    }
    ctx.redirect("/view_my_shifts");
  }
}
