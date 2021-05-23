package shift_manager_pro.controllers.user;
import org.jetbrains.annotations.NotNull;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import shift_manager_pro.dao.ShiftDao;
import shift_manager_pro.dao.UserDao;
import shift_manager_pro.models.Shift;
import shift_manager_pro.models.User;

import java.time.LocalDateTime;

public class UserUpdate implements Handler{
    public void handle(@NotNull Context ctx) throws Exception {
        User user = UserDao.INSTANCE.get(
                ctx.pathParam("user_id", Long.class).get()
        );

        user.setName(ctx.formParam("name"));
        user.setEmail(ctx.formParam("email"));

        UserDao.INSTANCE.updateUser(user);
        ctx.redirect("/users/list");
    }
}
