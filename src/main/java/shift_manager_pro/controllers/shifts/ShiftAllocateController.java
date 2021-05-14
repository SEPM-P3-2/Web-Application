package shift_manager_pro.controllers.shifts;

import com.sendgrid.*;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.io.IOException;
import org.jetbrains.annotations.NotNull;
import shift_manager_pro.dao.*;
import shift_manager_pro.models.*;
import shift_manager_pro.utils.DynamicTemplatePersonalization;

public class ShiftAllocateController implements Handler {

  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    User user = UserDao.INSTANCE.get(
      ctx.pathParam("user_id", Long.class).get()
    );
    Shift shift = ShiftDao.INSTANCE.getById(
      ctx.pathParam("shift_id", Long.class).get()
    );
    shift.setUser_id(user.getId());
    shift.setStatus("PENDING");

    ShiftDao.INSTANCE.updateShift(shift);

    Email from = new Email("SMP@smp.com");
    String subject = "New Shift Allocation";
    Email to = new Email("");
    Content content = new Content("text/plain", "New Shift" + shift.toString());

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

    ctx.redirect("/view_all_shifts");
  }
}
