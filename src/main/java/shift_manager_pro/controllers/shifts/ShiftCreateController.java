package shift_manager_pro.controllers.shifts;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.io.IOException;
import java.time.LocalDateTime;

import com.sendgrid.*;

import org.jetbrains.annotations.NotNull;
import shift_manager_pro.dao.*;
import shift_manager_pro.models.*;
import shift_manager_pro.utils.Views;

public class ShiftCreateController implements Handler {
  static final String PATH = Views.templatePath("manager/shifts/list.html");

  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    Shift shift = new Shift(
      ctx.formParam("location_id", Long.class).get(),
      ctx.formParam("user_id", Long.class).get(),
      LocalDateTime.parse(ctx.formParam("startTime")),
      LocalDateTime.parse(ctx.formParam("endTime")),
      ctx.formParam("breakTime", Integer.class).get(),
      ctx.formParam("info"),
      ctx.formParam("status")
    );
    if (shift.getUser_id() == 0) {
      shift.setStatus("UNALLOCATED");
      shift = ShiftDao.INSTANCE.createUnallocated(shift);
    } else {
      shift.setStatus("PENDING");
      shift = ShiftDao.INSTANCE.create(shift);
      User user = UserDao.INSTANCE.get(Long.valueOf(ctx.formParam("user_id")));

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
    }

    ctx.redirect("/view_all_shifts");
  }
}
