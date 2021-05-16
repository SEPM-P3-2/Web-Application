package shift_manager_pro.controllers.user;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.mindrot.jbcrypt.BCrypt;

import shift_manager_pro.dao.UserDao;
import shift_manager_pro.models.User;

public class ChangePasswordController implements Handler {

    @Override
    public void handle(Context ctx) throws Exception {
        User user = UserDao.INSTANCE.get(ctx.pathParam("id", Long.class).get());
        user.setPassword(BCrypt.hashpw(ctx.formParam("password"), BCrypt.gensalt()));
        user = UserDao.INSTANCE.create(user);
        ctx.redirect("/change_password");
    }
    
}