package shift_manager_pro.controllers.user;


import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import org.mindrot.jbcrypt.BCrypt;
import shift_manager_pro.dao.ShiftDao;
import shift_manager_pro.dao.UserDao;
import shift_manager_pro.models.Role;
import shift_manager_pro.models.Shift;
import shift_manager_pro.models.User;
import shift_manager_pro.utils.Views;

public class DeactivateController implements Handler{
    @Override
    public void handle(@NotNull Context ctx) throws Exception {


        User user = UserDao.INSTANCE.get(ctx.pathParam("user_id", Long.class).get());
        UserDao.INSTANCE.delete(user);

        ctx.redirect( "/list");
    }
}

