package shift_manager_pro.controllers.user;

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
    user.setJob_id(Long.valueOf(ctx.formParam("job_id")));
    user.setPreferred_name(String.valueOf(ctx.formParam("preferred_name")));
    user.setHome_address(String.valueOf(ctx.formParam("home_address")));
    UserDao.INSTANCE.updateUser(user);
    ctx.redirect("/");
  }
}
