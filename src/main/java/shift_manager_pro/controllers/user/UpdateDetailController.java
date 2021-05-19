package shift_manager_pro.controllers.user;
import org.mindrot.jbcrypt.BCrypt;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import org.jetbrains.annotations.NotNull;
import shift_manager_pro.dao.*;
import shift_manager_pro.models.*;


public class UpdateDetailController implements Handler {

  public void handle(@NotNull Context ctx) throws Exception {
    User user = UserDao.INSTANCE.get(
      ctx.pathParam("id", Long.class).get()
    );
    user.setEmail(String.valueOf(ctx.formParam("email")));
    user.setName(String.valueOf(ctx.formParam("name")));
    user.setRole(Role.valueOf(ctx.formParam("role")));
    user.setJob_id(Long.valueOf(ctx.formParam("job_id")));
    UserDao.INSTANCE.updateUser(user);
    ctx.redirect("/");
  }
}
