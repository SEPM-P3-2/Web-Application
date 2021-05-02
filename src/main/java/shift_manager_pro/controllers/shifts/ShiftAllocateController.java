package shift_manager_pro.controllers.shifts;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.time.LocalDateTime;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

import shift_manager_pro.dao.ShiftDao;
import shift_manager_pro.utils.Views;

public class ShiftAllocateController implements Handler {

  static final String PATH = Views.templatePath("shift/allocate.html");

  @Override
  public void handle(@NotNull Context context) throws Exception {
    Map<String, Object> model = Views.baseModel(context);
    model.put("shifts", ShiftDao.INSTANCE.getFromNow());
    context.render(PATH, model);
  }
}
