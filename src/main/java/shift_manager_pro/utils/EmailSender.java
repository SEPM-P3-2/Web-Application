package shift_manager_pro.utils;

import com.sendgrid.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import shift_manager_pro.dao.LocationDao;
import shift_manager_pro.dao.UserDao;
import shift_manager_pro.models.*;

public class EmailSender {

  public static void newShiftEmailSender(User user, Shift shift)
    throws IOException, SQLException {
    Email from = new Email("SMP@smp.com");
    String subject = "New Shift Allocation";
    Email to = new Email("");
    Content content = new Content(
      "text/plain",
      "New Shift for " +
      user.getPreferred_name() +
      " :" +
      user.getName() +
      " " +
      "\nLocation: " +
      LocationDao.INSTANCE.get(shift.getLocation_id()).getName() +
      "\nStart Time: " +
      shift.getStartTime() +
      "\nEnd Time: " +
      shift.getEndTime() +
      "\nBreak: " +
      shift.getBreakTime() +
      " minutes"
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
  public static void exceedStandardWorkingHour(User user, Shift shift, List<User> managers)
    throws IOException, SQLException {
    Email from = new Email("SMP@smp.com");
    String subject = "Exceed Standard Working Hour";
    Email to = new Email("");
    Content content = new Content(
      "text/plain",
      "Current working hour of " +
      user.getPreferred_name() +
      " " +
      "exceed his/her standard working hour " +
      "Current Working Hour: " +
      user.getCurrent_working_hour() +
      "Standard Working Hour:  " +
      user.getStandard_working_hour()
    );

    SendGrid sg = new SendGrid(
      "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"
    );
    Request request = new Request();
    to.setEmail(user.getEmail());
    for (User manager : managers) {
      to.setEmail(manager.getEmail());
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
  }

  public static void cancelShiftEmailSender(List<User> managers, Shift shift)
    throws IOException, SQLException {
    Email from = new Email("SMP@smp.com");
    String subject = "New Shift Cancelation";
    Email to = new Email("");
    Content content = new Content(
      "text/plain",
      "New Shift Cancelation" +
      "\nName: " +
      UserDao.INSTANCE.get(shift.getUser_id()).getName() +
      "\nLocation: " +
      LocationDao.INSTANCE.get(shift.getLocation_id()).getName() +
      "\nStart Time: " +
      shift.getStartTime() +
      "\nEnd Time: " +
      shift.getEndTime() +
      "\nBreak: " +
      shift.getBreakTime() +
      " minutes"
    );

    SendGrid sg = new SendGrid(
      "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"
    );
    Request request = new Request();
    for (User user : managers) {
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
  }
}
