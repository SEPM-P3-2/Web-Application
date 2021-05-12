package shift_manager_pro.controllers.shifts;
import shift_manager_pro.dao.ShiftDao;
import shift_manager_pro.models.Shift;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public class ShiftUpdateController implements Handler{
    public void handle(@NotNull Context ctx) throws Exception {
        Shift shift = ShiftDao.INSTANCE.getById(ctx.pathParam("shift_id", Long.class).get());
        shift.setLocation_id(Long.valueOf(ctx.formParam("location_id")));
        shift.setUser_id(Long.valueOf(ctx.formParam("user_id")));
        shift.setStartTime(LocalDateTime.parse(ctx.formParam("startTime")));
        shift.setEndTime(LocalDateTime.parse(ctx.formParam("endTime")));
        shift.setBreakTime(Integer.parseInt(ctx.formParam("breakTime")));
        shift.setInfo(ctx.formParam("info"));
        ShiftDao.INSTANCE.updateShift(shift);
        ctx.redirect("/view_all_shifts");

    }
}
