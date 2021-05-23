package shift_manager_pro.auth;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import org.mindrot.jbcrypt.BCrypt;
import shift_manager_pro.dao.UserDao;
import shift_manager_pro.models.Role;
import shift_manager_pro.models.User;

public class RegisterController implements Handler {

  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    User user = new User(
      ctx.formParam("email"),
      ctx.formParam("name"),
      Role.valueOf(ctx.formParam("role"))
    );
    user.setJob_id(Long.valueOf(ctx.formParam("job_id")));
    user.setPreferred_name(String.valueOf(ctx.formParam("preferred_name")));
    user.setHome_address(String.valueOf(ctx.formParam("home_address")));
    user.setPassword(BCrypt.hashpw(ctx.formParam("password"), BCrypt.gensalt()));
    user.setStandard_working_hour(Integer.valueOf(ctx.formParam("standard_working_hour")));
    user.setPhone_number(String.valueOf(ctx.formParam("phone_number")));
    user = UserDao.INSTANCE.create(user);
    ctx.redirect("/");
  }
}
