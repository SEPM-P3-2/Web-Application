package shift_manager_pro.controllers.users;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import org.mindrot.jbcrypt.BCrypt;
import shift_manager_pro.dao.UserDao;
import shift_manager_pro.models.Role;
import shift_manager_pro.models.User;

public class CreateAccountController implements Handler {

  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    User user = new User(
      ctx.formParam("email"),
      ctx.formParam("name"),
      Role.valueOf(ctx.formParam("role"))
    );
    user.setPassword(
      BCrypt.hashpw(ctx.formParam("password"), BCrypt.gensalt())
    );
    user = UserDao.INSTANCE.create(user);
    ctx.redirect("/users/" + user.getId());
  }
}
