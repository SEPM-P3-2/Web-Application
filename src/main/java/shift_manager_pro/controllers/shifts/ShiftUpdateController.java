package shift_manager_pro.controllers.shifts;
import shift_manager_pro.dao.ShiftDao;
import shift_manager_pro.models.Shift;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class ShiftUpdateController implements Handler{
    public void handle(@NotNull Context ctx) throws Exception {
        //Shift shift = ShiftDao.INSTANCE.getById(ctx.pathParam("id", Long.class).getById());
       // shift.setUser_id(ctx.formParam("user_id", Long.class));
        //shift.setLocation_id(ctx.formParam("location_id", Long.class));
        //shift.setStartTime(ctx.formParam("startTime"));
        //shift.setEndTime(ctx.formParam("endTime"));
        //shift.setDuration(ctx.formParam("Duration");
        //shift.setInfo(ctx.formParam("info"));
        //ShiftDao.INSTANCE.update(shift);
        //ctx.redirect("/shifts/" + shift.getId());
    }
}
