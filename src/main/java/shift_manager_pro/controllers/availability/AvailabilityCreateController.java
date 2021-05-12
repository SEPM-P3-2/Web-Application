package shift_manager_pro.controllers.availability;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import org.mindrot.jbcrypt.BCrypt;
import shift_manager_pro.auth.AccessManager;
import shift_manager_pro.dao.AvailabilityDao;
import shift_manager_pro.dao.UserDao;
import shift_manager_pro.models.Availability;
import shift_manager_pro.models.Role;
import shift_manager_pro.models.User;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class AvailabilityCreateController implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        System.out.println(ctx.formParam("startTime" + ":00"));
        System.out.println(ctx.formParam("endTime" + ":00"));
        DayOfWeek day = DayOfWeek.valueOf(ctx.formParam("day"));
        LocalTime startTime = LocalTime.parse(ctx.formParam("startTime").concat(":00"), AvailabilityDao.formatter);
        LocalTime endTime = LocalTime.parse(ctx.formParam("endTime").concat(":00"), AvailabilityDao.formatter);

        if(AccessManager.getSessionCurrentUser(ctx).getRole() == Role.ANONYMOUS)
            return;

        Availability a = new Availability(null, AccessManager.getSessionCurrentUser(ctx).getId(), day, startTime, endTime);
        AvailabilityDao.INSTANCE.create(a);

        ctx.redirect("/view_availabilities");
    }
}
