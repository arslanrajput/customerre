package com.example.myapplication.Utility;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;



public class SessionManager {
    // Shared Preferences
    private SharedPreferences pref;
    // Editor for Shared preferences
    private Editor editor;
    // Context
    private Context _context;
    // Shared pref mode
    private int PRIVATE_MODE = 0;
    // Sharedpref file name
    private static final String PREF_NAME = "UserPref";
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String IS_ADIMLOGIN = "IsLogIn";
    // email (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    public static final String KEY_ADMINEMAIL = "email";
    // Name (make variable public to access from outside)
    public static final String KEY_NAME = "name";

    //token
    public static final String KEY_TOKEN = "token_key";

    public static final String KEY_TOKENCOUNT = "markercount";
    public static final String KEY_FNAME = "firstname";
    public static final String KEY_LNAME = "lastname";

    public static final String KEY_STRATID = "stid";

    public static final String KEY_STRATFRTID = "id";

    public static final String KEY_FULLNAME = "fullname";
    public static final String KEY_USERNAME = "username";

    public static final String KEY_PROFIMG = "prof_img";


    public static final String KEY_ADMINNAME = "username";

    public static final String KEY_USERID = "userid";

    public static final String KEY_ADMINID = "userid";

    public static final String KEY_USERDATEJOINED = "userdatejoined";
    public static final String KEY_USERIMAGE = "userimage";
    public static final String KEY_TOKEN_ID = "token_id";
    public static final String KEY_BOOK_ID = "book_id";
    // User password (make variable public to access from outside)
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_CONTACT = "contact";
    public static final String KEY_CNIC = "nicNumber";
    public static final String KEY_Qualification = "qualification";
    public static final String KEY_Occupation = "qualification";
    public static final String KEY_HomeAddress = "homeAddress";
    public static final String KEY_OfficeAddress = "officeAddress";
    public static final String KEY_Registerbranch = "officeAddress";

    public static final String KEY_ADDRESS = "address";
    public static final String KEY_PHONENUMBER = "phone_num";
    public static final String KEY_CITY = "city";
    //user_type 0 means user, 1 means tenant
    public static final String KEY_TYPE = "type";
    //FIrebase registration id for messaging
    public static final String KEY_FIREBASE_ID = "regid";

    public static final String KEY_HotelId= "hotel_id";
    public static final String KEY_CityId= "city_id";
    public static final String KEY_ChildId= "child_id";
    public static final String KEY_ADDRESSS = "addresses";
    public static final String KEY_CURTADDRESSS = "curt_address";

    public static final String KEY_CHATNAME= "name";
    public static final String KEY_CHATEmail= "email";
    public static final String KEY_CHATId= "user_id";



    public void saveChatName(String id) {
        editor.putString(KEY_CHATNAME, id);
        editor.commit();
    }
    public void saveChatEmail(String id) {
        editor.putString(KEY_CHATEmail, id);
        editor.commit();
    }
    public void saveChatId(String id) {
        editor.putString(KEY_CHATId, id);
        editor.commit();
    }

    public void saveCounter(String id) {
        editor.putString(KEY_TOKENCOUNT, id);
        editor.commit();
    }
    public String getCount() {
        return pref.getString(KEY_TOKENCOUNT, null);
    }

    public String getChatname() {
        return pref.getString(KEY_CHATNAME, null);
    }
    public String getChatId()    { return pref.getString(KEY_CHATId, null); }
    public String getChatEmail() {
        return pref.getString(KEY_CHATEmail, null);
    }

    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }



    /**
     * Create login session
     */

    public void createLoginSession(String username) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        // Storing password in pref
        // Storing name in pref
        editor.putString(KEY_USERNAME, username);
//        editor.putString(KEY_TYPE, role);
        // commit changes
        editor.commit();
    }

    public void createAdminLoginSession(String adminname) {
        // Storing login value as TRUE
        editor.putBoolean(IS_ADIMLOGIN, true);
        // Storing password in pref
        // Storing name in pref
        editor.putString(KEY_ADMINNAME, adminname);
//        editor.putString(KEY_TYPE, role);
        // commit changes
        editor.commit();
    }

    public boolean checkLoginName()
    {
        // Check login status
        if (this.isLoggedInName()) {
            return true;
        }
        return false;
    }

    private boolean isLoggedInName()
    {
        return pref.getBoolean(KEY_ADMINNAME, false);
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    public boolean checkLogin()
    {
        // Check login status
        if (this.isLoggedIn()) {
            return true;
        }
        return false;
    }
    public boolean checkLoginAdmin()
    {
        // Check login status
        if (this.isLogIn()) {
            return true;
        }
        return false;
    }



    /**
     * Clear session details
     */
    public boolean logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
       // Intent i = new Intent(_context, com.example.Parent.SignIn.class);
        // Closing all the Requests
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Staring Login Activity
   //     _context.startActivity(i);
        // Add new Flag to start new Activity
        return true;
    }
    public boolean logoutadmin() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

//        // After logout redirect user to Loing Activity
//        Intent i = new Intent(_context, SignIn.class);
//        // Closing all the Requests
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//        // Staring Login Activity
//        _context.startActivity(i);
        // Add new Flag to start new Activity
        return true;
    }

    /**
     * Quick check for login
     **/
    // Get Login State
    private boolean isLoggedIn()
    {
        return pref.getBoolean(IS_LOGIN, false);
    }
    private boolean isLogIn()
    {
        return pref.getBoolean(IS_ADIMLOGIN, false);
    }

    public void saveUserID(String id) {
        editor.putString(KEY_USERID, id);
        editor.commit();
    }
    public void saveAdminID(String id) {
        editor.putString(KEY_ADMINID, id);
        editor.commit();
    }

    public void saveHotelId(String id) {
        editor.putString(KEY_HotelId, id);
        editor.commit();
    }
    public void saveCityId(String id) {
        editor.putString(KEY_CityId, id);
        editor.commit();
    }

    public void saveChildId(String id) {
        editor.putString(KEY_ChildId, id);
        editor.commit();
    }
    public void saveAddress(String id) {
        editor.putString(KEY_ADDRESSS, id);
        editor.commit();
    }

    public void saveUserDateJoined(String date) {
        editor.putString(KEY_USERDATEJOINED, date);
        editor.commit();
    }


    public void saveName(String name) {
        editor.putString(KEY_NAME, name);
        editor.commit();
    }
    public void saveUserName(String name) {
        editor.putString(KEY_USERNAME, name);
        editor.commit();
    }




    public void saveAdminName(String name) {
        editor.putString(KEY_ADMINNAME, name);
        editor.commit();
    }

    public void saveFirstName(String fname) {
        editor.putString(KEY_FNAME, fname);
        editor.commit();

    }
    public void saveLastName(String lname) {
        editor.putString(KEY_LNAME, lname);
        editor.commit();
    }

    //save startTRipID

    public void saveStartLocId(String id) {
        editor.putString(KEY_STRATID, id);
        editor.commit();
    }

    //save startTRipID

    public void saveStartFrtLocId(String id) {
        editor.putString(KEY_STRATFRTID, id);
        editor.commit();
    }
////////////////////////////////KEY_STRATFRTID
    // token key
    public void saveTokenKey(String token_key) {
        editor.putString(KEY_TOKEN_ID, token_key);
        editor.commit();
    }




    public void savefullname(String fullname) {
        editor.putString(KEY_FULLNAME, fullname);
        editor.commit();
    }
    public void savecontact(String contact) {
        editor.putString(KEY_CONTACT, contact);
        editor.commit();
    }

    public void saveCnicNumber(String cnicNumber) {
        editor.putString(KEY_CNIC, cnicNumber);
        editor.commit();
    }
    public void saveQualification(String qualification) {
        editor.putString(KEY_Qualification, qualification);
        editor.commit();
    }
    public void saveOccupation(String occupation) {
        editor.putString(KEY_Occupation, occupation);
        editor.commit();
    }
    public void saveHomeAddress(String homeAddress) {
        editor.putString(KEY_HomeAddress, homeAddress);
        editor.commit();
    }
    public void saveProfImage(String homeAddress) {
        editor.putString(KEY_PROFIMG, homeAddress);
        editor.commit();
    }


    public void saveOfficeAddress(String officeAddress) {
        editor.putString(KEY_OfficeAddress, officeAddress);
        editor.commit();
    }
    public void saveRegisterbranch(String officeAddress) {
        editor.putString(KEY_Registerbranch, officeAddress);
        editor.commit();
    }

    public void saveUserImage(String image) {
        editor.putString(KEY_USERIMAGE, image);
        editor.commit();
    }

    public void saveUserAddress(String address) {
        editor.putString(KEY_ADDRESS, address);
        editor.commit();
    }

    public void saveTokenid(String res_id) {
        editor.putString(KEY_BOOK_ID, res_id);
        editor.commit();
    }



    public void savePassword(String password) {
        editor.putString(KEY_PASSWORD, password);
        editor.commit();
    }



    public void saveUserEmail(String email) {
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }
    public void saveAdminEmail(String email) {
        editor.putString(KEY_ADMINEMAIL, email);
        editor.commit();
    }


    public void saveBookId(String id) {
        editor.putString(KEY_BOOK_ID, id);
        editor.commit();
    }

    public void saveUserPhoneNumber(String phonenumber) {
        editor.putString(KEY_PHONENUMBER, phonenumber);
        editor.commit();
    }

    public String getUserEmail() {
        return pref.getString(KEY_EMAIL, null);
    }
    public String getAdminEmail() {
        return pref.getString(KEY_ADMINEMAIL, null);
    }

    public String getPassword() {
        return pref.getString(KEY_PASSWORD, null);
    }

    public String getUserID() {
        return pref.getString(KEY_USERID, null);
    }
    public String getAdminID() {
        return pref.getString(KEY_ADMINID, null);
    }
    public String getProfimg() {
        return pref.getString(KEY_PROFIMG, null);
    }

    public String getHotelId() {
        return pref.getString(KEY_HotelId, null);
    }
    public String getCityId() {
        return pref.getString(KEY_CityId, null);
    }

    public String getChildId() {
        return pref.getString(KEY_ChildId, null);
    }
    public String getAddresss() {
        return pref.getString(KEY_ADDRESSS, null);
    }



    public String getRegistrationId() {
        return pref.getString(KEY_FIREBASE_ID, null);
    }

    public String getUserType() {
        return pref.getString(KEY_TYPE, null);
    }
    public String getName() {
        return pref.getString(KEY_NAME, null);
    }
    public String getFullName() {
        return pref.getString(KEY_FULLNAME, null);
    }
    public String getUserName() {
        return pref.getString(KEY_USERNAME, null);
    }
    public String getAdminName() {
        return pref.getString(KEY_ADMINNAME, null);
    }

    public String getUserAddress() {
        return pref.getString(KEY_ADDRESS, null);
    }
    public String getUserimage() {return pref.getString(KEY_USERIMAGE, null);}
    public String getTokenId() {
        return pref.getString(KEY_TOKEN_ID, null);}
    public String getBookId() {return pref.getString(KEY_BOOK_ID, null);}
    public String getUserPhonenumber() {return pref.getString(KEY_PHONENUMBER, null);}
    public String getUserDateJoined() {return pref.getString(KEY_USERDATEJOINED, null);}


    public String getFirstName() {return pref.getString(KEY_FNAME, null);}

    public String getLastName() {return pref.getString(KEY_LNAME, null);}

    public String getStartId() {return pref.getString(KEY_STRATID, null);}
    public String getStartFrtId() {return pref.getString(KEY_STRATFRTID, null);}

}