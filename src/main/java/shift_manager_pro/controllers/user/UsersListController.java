package shift_manager_pro.controllers.user;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import shift_manager_pro.dao.UserDao;
import shift_manager_pro.models.User;
import shift_manager_pro.utils.Views;
import java.util.Map;

public class UsersListController implements Handler{
    static final String PATH = Views.templatePath("users/list.html");
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Map<String, Object> model = Views.baseModel(ctx);
        User user = (User) model.get("user");
        model.put("users", UserDao.INSTANCE.getAll());
        ctx.render(PATH, model);
}

}
