package shift_manager_pro.utils;

import io.javalin.http.Context;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import shift_manager_pro.auth.AccessManager;
import shift_manager_pro.dao.*;
import shift_manager_pro.models.*;

/**
 * A Utility Class with useful methods for Views of our application
 */
public class Views {

  /**
   * Root location of html views
   */
  private static final String TEMPLATE_BASE = "/views/";

  /**
   * Use this method to obtain the final path for your templates.
   * @param template
   * @return
   */
  public static String templatePath(String template) {
    return TEMPLATE_BASE.concat(template);
  }

  /**
   * Use this method to populate the model with common information.
   * Eg: model.put("user", getSessionCurrentUser(ctx));
   * @param ctx
   * @return a pre-populated model
   * @throws SQLException
   */
  public static Map<String, Object> baseModel(Context ctx) throws SQLException {
    User user = AccessManager.getSessionCurrentUser(ctx);
    int new_shift_count = 0;
    List<Shift> new_shifts = new ArrayList<Shift>();
    List<Shift> shifts = new ArrayList<Shift>();
    if (user.getRole() != Role.ANONYMOUS) {
      shifts = ShiftDao.INSTANCE.getByUserId(user.getId());
      for (Shift shift : shifts) {
        if (shift.getStatus().equals("PENDING")) {
          new_shift_count++;
          new_shifts.add(shift);
        }
      }
    }

    Map<String, Object> model = new HashMap<>();
    //add currentUser information
    model.put("user", user);
    model.put("user_id", user.getId());
    model.put(
      "user_logged_in",
      (user.getRole() == Role.ANONYMOUS ? false : true)
    );
    model.put("role", user.getRole().toString());
    model.put("locations", LocationDao.INSTANCE.getAll());
    model.put("jobs", JobDao.INSTANCE.getAll());
    model.put("new_shift_count", new_shift_count);
    model.put("new_shifts", new_shifts);

    return model;
  }
}
