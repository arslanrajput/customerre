package com.example.myapplication.Utility;


public class Config {


    // flag to identify whether to show single line
    // or multi line test push notification tray
    public static boolean appendNotificationMessages = true;

    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    // type of push messages
    public static final int PUSH_TYPE_CHATROOM = 1;
    public static final int PUSH_TYPE_USER = 2;

    // id to handle the notification in the notification try
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

    //

    public static final String HOST_IP = "http://192.168.100.10:8083/apis/";

   // public static final String HOST_IP = "192.168.1.23:8080";

    // public  static final String Host_url="http://www.manageilm.com/cancer/";
    //http://codingcampus.tech/Cancer/";
    // public  static final String Host_url="http://menteck.com/roady/roady/index.php"; //http://codingcampus.tech/roadyserver/index.php";
  //  private static final String BASE_URL = "http://bustracker.cresol.pk/";
//    private static final String BASE_URL = "https://bustracker.cicm.pk/";


    //http://bustracker.cresol.pk/busapp_apis/
    //
    // https://bustracker.cicm.pk/busapp_apis/api/parent_login
   // private static final String BASE_URL = "http://bustracker.cresol.pk/";


    private static final String BASE_URL = "https://cfa.swantech.ae/";

    public static final String QUES = BASE_URL + "api/questions";

    public static final String ANSWER = BASE_URL + "api/submit-answers-array";


//https://bustracker.cicm.pk/busapp_apis/api/child_bus_stops
//  https://bustracker.cicm.pk/busapp_apis/api/child_all_detail
//public static final String HISBTYID = BASE_URL + "busapp_apis/api/get_driver_history_detail";

}
//childDetail
