package shift_manager_pro.controllers.user;
import org.mindrot.jbcrypt.BCrypt;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import org.jetbrains.annotations.NotNull;
import shift_manager_pro.dao.*;
import shift_manager_pro.models.*;


public class UpdatePasswordController implements Handler {

  public void handle(@NotNull Context ctx) throws Exception {
    User user = UserDao.INSTANCE.get(
      ctx.pathParam("id", Long.class).get()
    );
    user.setPassword(BCrypt.hashpw(ctx.formParam("password"), BCrypt.gensalt()));
    UserDao.INSTANCE.updateUser(user);
    ctx.redirect("/");
  }
}