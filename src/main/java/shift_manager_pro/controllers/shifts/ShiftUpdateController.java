package shift_manager_pro.controllers.shifts;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.time.LocalDateTime;
import org.jetbrains.annotations.NotNull;
import shift_manager_pro.dao.ShiftDao;
import shift_manager_pro.models.Shift;

public class ShiftUpdateController implements Handler {

  public void handle(@NotNull Context ctx) throws Exception {
    Shift shift = ShiftDao.INSTANCE.getById(
      ctx.pathParam("shift_id", Long.class).get()
    );
    if (Integer.parseInt(ctx.formParam("user_id")) == 0) {
      shift.setStatus("UNALLOCATED");
      shift.setUser_id(null);
    } else {
      shift.setUser_id(Long.valueOf(ctx.formParam("user_id")));
      shift.setStatus("PENDING");
    }
    shift.setLocation_id(Long.valueOf(ctx.formParam("location_id")));
    shift.setStartTime(LocalDateTime.parse(ctx.formParam("startTime")));
    shift.setEndTime(LocalDateTime.parse(ctx.formParam("endTime")));
    shift.setBreakTime(Integer.parseInt(ctx.formParam("breakTime")));
    shift.setInfo(ctx.formParam("info"));
    ShiftDao.INSTANCE.updateShift(shift);
    ctx.redirect("/view_all_shifts");
  }
}
