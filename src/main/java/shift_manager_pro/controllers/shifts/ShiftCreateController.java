package shift_manager_pro.controllers.shifts;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import shift_manager_pro.dao.ShiftDao;
import shift_manager_pro.models.Shift;

public class ShiftCreateController implements Handler {


    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Shift shift = new Shift(
                ctx.formParam("user_id"),
                ctx.formParam("startTime"),
                ctx.formParam("endTime"),
                ctx.formParam("loc_id"),
                ctx.formParam("duration"),
                ctx.formParam("info")
        );



        shift = ShiftDao.INSTANCE.create(shift);
        ctx.redirect("/shifts/" + shift.getId());
    }
}

