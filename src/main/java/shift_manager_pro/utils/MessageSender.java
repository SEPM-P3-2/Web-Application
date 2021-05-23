package shift_manager_pro.utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Configuration;
import ClickSend.Api.SmsApi;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;
import ClickSend.auth.HttpBasicAuth;
import shift_manager_pro.dao.LocationDao;
import shift_manager_pro.dao.UserDao;
import shift_manager_pro.models.Shift;
import shift_manager_pro.models.User;

public class MessageSender {

  public static void cancelShiftMessageSender(List<User> managers, Shift shift)
    throws IOException, SQLException {
    ApiClient defaultClient = Configuration.getDefaultApiClient();

    // Configure HTTP basic authorization: BasicAuth
    HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication(
      "BasicAuth"
    );
    BasicAuth.setUsername("quydat1711@gmail.com");
    BasicAuth.setPassword("Vucu,4567");

    SmsApi apiInstance = new SmsApi();
    SmsMessageCollection smsMessages = new SmsMessageCollection();
    for (User manager : managers) {
      SmsMessage smsMessage = new SmsMessage();
      smsMessage.from("SMP");
      smsMessage.body(
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
      smsMessage.to(manager.getPhone_number());
      smsMessages.addMessagesItem(smsMessage);
    }

    try {
      String result = apiInstance.smsSendPost(smsMessages);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SmsApi#smsSendPost");
      e.printStackTrace();
    }
  }
}
