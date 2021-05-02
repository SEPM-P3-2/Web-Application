package shift_manager_pro.auth;

import shift_manager_pro.dao.UserDao;
import shift_manager_pro.models.User;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import org.mindrot.jbcrypt.BCrypt;

public class LoginController implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String email = ctx.formParam("email");
        String pw = ctx.formParam("password");

        if (BCrypt.checkpw(pw, UserDao.INSTANCE.getUserPasswordHash(email))) {
            User user = UserDao.INSTANCE.getByEmail(email);
            AccessManager.loginUser(ctx,user);
            ctx.redirect("/");
        } else {
            ctx.result("Login Failed");
        }

    }
}
