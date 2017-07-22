package zolo.in.zolostays.zolostaysdemo.Activity.Presenter;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import zolo.in.zolostays.zolostaysdemo.Activity.Model.User;
import zolo.in.zolostays.zolostaysdemo.Activity.Model.ZoloDbHelper;

public class Utility {
    private Context context;
    private ArrayList<User> userDetailsList = new ArrayList<User>();

    public Utility(Context ctx) {
        this.context = ctx;
    }

    public boolean checkUserPhoneNumber(String phoneNumber){
        ZoloDbHelper dbHelper = new ZoloDbHelper(context);
        boolean value = false;
        userDetailsList = (ArrayList<User>) dbHelper.getUserDetailsFromUserDetailsTable();
        for (User user: userDetailsList) {
            Log.e("number..",user.getUserPhoneNumber());
            Log.e("phoneNumber..",phoneNumber);
            if(phoneNumber.equals(user.getUserPhoneNumber())){
                value = true;
                break;
            }
            else {
                value = false;
            }
        }
        Log.e("value....",""+value);
        return value;
    }
    public int getValidityOfUser(String userPhoneNumber,String password){
        ZoloDbHelper dbHelper = new ZoloDbHelper(context);
        int returnValue;
        userDetailsList = (ArrayList<User>) dbHelper.getUserDetailsFromUserDetailsTable(userPhoneNumber);
        if(userDetailsList.size() == 0){
            returnValue = 0;
        }
        else {
            if(password.equals(userDetailsList.get(0).getPassword())){
                returnValue = 1;
            }
            else {
                returnValue = 2;
            }
        }
        return returnValue;
    }


}
