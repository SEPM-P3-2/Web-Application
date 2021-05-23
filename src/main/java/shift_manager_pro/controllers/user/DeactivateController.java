package shift_manager_pro.controllers.user;


import java.util.List;

import org.jetbrains.annotations.NotNull;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import shift_manager_pro.dao.ShiftDao;
import shift_manager_pro.dao.UserDao;
import shift_manager_pro.models.Shift;
import shift_manager_pro.models.User;

public class DeactivateController implements Handler{
    @Override
    public void handle(@NotNull Context ctx) throws Exception {


        User user = UserDao.INSTANCE.get(ctx.pathParam("user_id", Long.class).get());
        List<Shift> shifts  = ShiftDao.INSTANCE.getByUserId(user.getId());
        for (Shift shift : shifts) {
            ShiftDao.INSTANCE.delete(shift);
        }
        UserDao.INSTANCE.delete(user);

        ctx.redirect( "/users/list");
    }
}

