package shift_manager_pro.utils;

import ClickSend.*;
import ClickSend.Api.AccountApi;
import ClickSend.Api.SmsApi;
import ClickSend.Model.*;
import ClickSend.auth.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import shift_manager_pro.models.Shift;
import shift_manager_pro.models.User;

public class MessageSender {

  public static void cancelShiftMessageSender(List<User> managers, Shift shift)
    throws IOException {
    ApiClient defaultClient = Configuration.getDefaultApiClient();

    // Configure HTTP basic authorization: BasicAuth
    HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication(
      "BasicAuth"
    );
    BasicAuth.setUsername("quydat1711@gmail.com");
    BasicAuth.setPassword("Vucu,4567");

    SmsApi apiInstance = new SmsApi();
    SmsMessageCollection smsMessages = new SmsMessageCollection();
    SmsMessage smsMessage = new SmsMessage(); // SmsMessageCollection | SmsMessageCollection model
    SmsMessage smsMessage2 = new SmsMessage();
    smsMessage.from("SMP");
    smsMessage.body("Shift Cancelation for ...");
    smsMessage.to("+61422969888");
    smsMessages.addMessagesItem(smsMessage);

    smsMessage2.from("SMP");
    smsMessage2.body("Shift Cancelation for ...");
    smsMessage2.to("+61455316277");
    smsMessages.addMessagesItem(smsMessage);

    try {
      String result = apiInstance.smsSendPost(smsMessages);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SmsApi#smsSendPost");
      e.printStackTrace();
    }
  }
}
