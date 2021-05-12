package shift_manager_pro.controllers.shifts;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.time.LocalDateTime;
import org.jetbrains.annotations.NotNull;
import shift_manager_pro.dao.ShiftDao;
import shift_manager_pro.models.Shift;

public class ShiftCreateController implements Handler {

  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    Shift shift = new Shift(
      ctx.formParam("location_id", Long.class).get(),
      ctx.formParam("user_id", Long.class).get(),
      LocalDateTime.parse(ctx.formParam("startTime")),
      LocalDateTime.parse(ctx.formParam("endTime")),
      ctx.formParam("breakTime", Integer.class).get(),
      ctx.formParam("info"),
      ctx.formParam("status")
    );
    if (shift.getUser_id() == 0) {
      shift.setStatus("UNALLOCATED");
      shift = ShiftDao.INSTANCE.createUnallocated(shift);
    } else {
      shift.setStatus("PENDING");
      shift = ShiftDao.INSTANCE.create(shift);
    }

    ctx.redirect("/view_all_shifts");
  }
}
