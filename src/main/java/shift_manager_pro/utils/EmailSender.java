package shift_manager_pro.utils;

import com.sendgrid.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import shift_manager_pro.dao.LocationDao;
import shift_manager_pro.models.Location;
import shift_manager_pro.models.Shift;
import shift_manager_pro.models.User;

public class EmailSender {

  public static void sendEmail(User user, Shift shift)
    throws IOException, SQLException {
    Email from = new Email("SMP@smp.com");
    String subject = "New Shift Allocation";
    Email to = new Email("");
    Content content = new Content(
      "text/plain",
      "New Shift for " +
      user.getName() +
      " :" +
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
}
