package shift_manager_pro.controllers.shifts;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.time.LocalDateTime;
import org.jetbrains.annotations.NotNull;
import shift_manager_pro.dao.*;
import shift_manager_pro.models.*;
import shift_manager_pro.utils.EmailSender;
import shift_manager_pro.utils.Views;
import java.time.temporal.*;

public class ShiftCreateController implements Handler {

  static final String PATH = Views.templatePath("manager/shifts/list.html");

  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    double duration = Math.round((Double.valueOf((LocalDateTime.parse(ctx.formParam("startTime")).until(LocalDateTime.parse(ctx.formParam("endTime")),ChronoUnit.MINUTES))-ctx.formParam("breakTime", Double.class).get())/60) * 100.0) / 100.0;
    Shift shift = new Shift(
      ctx.formParam("location_id", Long.class).get(),
      ctx.formParam("user_id", Long.class).get(),
      LocalDateTime.parse(ctx.formParam("startTime")),
      LocalDateTime.parse(ctx.formParam("endTime")),
      ctx.formParam("breakTime", Integer.class).get(),
      duration,
      ctx.formParam("info"),
      ctx.formParam("status")
    );
    if (shift.getUser_id() == 0) {
      shift.setStatus("UNALLOCATED");
      shift = ShiftDao.INSTANCE.createUnallocated(shift);
    } else {
      shift.setStatus("PENDING");
      shift = ShiftDao.INSTANCE.create(shift);
      User user = UserDao.INSTANCE.get(Long.valueOf(ctx.formParam("user_id")));
      EmailSender.newShiftEmailSender(user, shift);
    }

    ctx.redirect("/view_all_shifts");
  }
}
