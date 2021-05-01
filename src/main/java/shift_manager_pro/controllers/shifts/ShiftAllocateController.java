package shift_manager_pro.controllers.shifts;

import shift_manager_pro.utils.Views;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Map;

public class ShiftAllocateController implements Handler {

    static final String PATH = Views.templatePath("shift/allocate.html");

    @Override
    public void handle(@NotNull Context context) throws Exception {





        

        Map<String, Object> model = Views.baseModel(context);
        context.render(PATH, model);
    }





}
