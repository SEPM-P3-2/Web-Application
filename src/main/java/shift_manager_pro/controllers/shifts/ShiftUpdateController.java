package shift_manager_pro.controllers.shifts;

import com.sendgrid.*;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.io.IOException;
import java.time.LocalDateTime;
import org.jetbrains.annotations.NotNull;
import shift_manager_pro.dao.*;
import shift_manager_pro.models.*;

public class ShiftUpdateController implements Handler {

  public void handle(@NotNull Context ctx) throws Exception {
    Shift shift = ShiftDao.INSTANCE.getById(
      ctx.pathParam("shift_id", Long.class).get()
    );
    if (Integer.parseInt(ctx.formParam("user_id")) == 0) {
      shift.setStatus("UNALLOCATED");
      shift.setUser_id(null);
    } else {
      shift.setUser_id(Long.valueOf(ctx.formParam("user_id")));
      shift.setStatus("PENDING");
      User user = UserDao.INSTANCE.get(Long.valueOf(ctx.formParam("user_id")));
      Email from = new Email("SMP@smp.com");
      String subject = "New Shift Allocation";
      Email to = new Email("");
      Content content = new Content(
        "text/plain",
        "New Shift" + shift.toString()
      );

      SendGrid sg = new SendGrid(
        "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"
      );
      Request request = new Request();
      to.setEmail(user.getEmail());
      Mail mail = new Mail(from, subject, to, content);

      try {
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        Response response = sg.api(request);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        System.out.println(response.getHeaders());
      } catch (IOException ex) {
        throw ex;
      }
    }
    shift.setLocation_id(Long.valueOf(ctx.formParam("location_id")));
    shift.setStartTime(LocalDateTime.parse(ctx.formParam("startTime")));
    shift.setEndTime(LocalDateTime.parse(ctx.formParam("endTime")));
    shift.setBreakTime(Integer.parseInt(ctx.formParam("breakTime")));
    shift.setInfo(ctx.formParam("info"));
    ShiftDao.INSTANCE.updateShift(shift);
    ctx.redirect("/view_all_shifts");
  }
}
