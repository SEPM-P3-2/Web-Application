package shift_manager_pro.controllers.shifts;
import shift_manager_pro.dao.ShiftDao;
import shift_manager_pro.models.Shift;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class ShiftDeleteController implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Shift shift = ShiftDao.INSTANCE.getById(ctx.pathParam("shift_id", Long.class).get());
        ShiftDao.INSTANCE.delete(shift);
        ctx.redirect("/view_all_shifts");
    }
}
