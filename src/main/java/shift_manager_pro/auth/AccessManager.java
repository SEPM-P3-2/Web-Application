package shift_manager_pro.auth;

import shift_manager_pro.models.User;
import shift_manager_pro.utils.Views;
import io.javalin.core.security.Role;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class AccessManager implements io.javalin.core.security.AccessManager {
    private static final String USER_KEY = "currentUser";

    private static final User ANON = new User("none@smp.com","Anonymous", shift_manager_pro.models.Role.ANONYMOUS);

    public static User getSessionCurrentUser(Context ctx) {
        User user = ctx.sessionAttribute(USER_KEY);
        if(user == null){
            user = ANON;
            loginUser(ctx,user);
        }
        return user;
    }

    @Override
    public void manage(@NotNull Handler handler, @NotNull Context ctx, @NotNull Set<Role> permittedRoles) throws Exception {
        Role userRole = getUserRole(ctx);
        if (permittedRoles.isEmpty() || permittedRoles.contains(userRole)) {
            handler.handle(ctx);
        } else {
            ctx.render("/views/auth/unauthorised.html", Views.baseModel(ctx));
        }
    }

    private Role getUserRole(Context ctx) {
        return getSessionCurrentUser(ctx).getRole();
    }

    public static void loginUser(Context ctx, User user){
        ctx.sessionAttribute(USER_KEY,user);
    }

    public static void logout(Context ctx) {
        ctx.sessionAttribute(USER_KEY,null);
    }


}
