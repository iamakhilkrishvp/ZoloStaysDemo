package zolo.in.zolostays.zolostaysdemo.Activity.Presenter;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import zolo.in.zolostays.zolostaysdemo.Activity.Model.User;
import zolo.in.zolostays.zolostaysdemo.Activity.Model.ZoloDbHelper;

public class Utility {
    ZoloDbHelper dbHelper;
    private Context context;
    private ArrayList<User> userDetailsList = new ArrayList<User>();
    public Utility(Context ctx) {
        this.context = ctx;
        dbHelper = new ZoloDbHelper(context);
    }

    public boolean checkUserPhoneNumber(String phoneNumber){

        boolean value = false;
        userDetailsList = (ArrayList<User>) dbHelper.getUserDetailsFromUserDetailsTable();
        for (int i = 0; i < userDetailsList.size(); i++) {
            if (phoneNumber.equals(userDetailsList.get(i).getUserPhoneNumber())) {
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
        int returnValue;
        userDetailsList = (ArrayList<User>) dbHelper.getUserDetailsFromUserDetailsTable(userPhoneNumber);
        Log.e("user size....", "" + userDetailsList.size());
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
