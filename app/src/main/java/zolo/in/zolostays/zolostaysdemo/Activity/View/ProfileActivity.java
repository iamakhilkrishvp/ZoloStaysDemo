package zolo.in.zolostays.zolostaysdemo.Activity.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.util.ArrayList;

import zolo.in.zolostays.zolostaysdemo.Activity.Model.User;
import zolo.in.zolostays.zolostaysdemo.Activity.Model.ZoloDbHelper;
import zolo.in.zolostays.zolostaysdemo.R;

public class ProfileActivity extends AppCompatActivity {
    EditText etUserName, etEmailId, etPhoneNumber;

    ZoloDbHelper dbHelper;
    String phoneNumber;
    private ArrayList<User> userDetailsList = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etUserName = (EditText) findViewById(R.id.editText);
        etEmailId = (EditText) findViewById(R.id.editText2);
        etPhoneNumber = (EditText) findViewById(R.id.editText3);
        dbHelper = new ZoloDbHelper(this);
        phoneNumber = getPhoneNumber();
        setUserDetails(phoneNumber);

    }

    public String getPhoneNumber() {
        String phoneNumber;
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            phoneNumber = null;
        } else {
            phoneNumber = extras.getString("USER_NUMBER");
        }
        return phoneNumber;
    }

    public void setUserDetails(String phoneNumber) {
        userDetailsList = (ArrayList<User>)
                dbHelper.getUserDetailsFromUserDetailsTable(phoneNumber);
        etUserName.setText(userDetailsList.get(0).getUsername());
        etEmailId.setText(userDetailsList.get(0).getUserEmailId());
        etPhoneNumber.setText(userDetailsList.get(0).getUserPhoneNumber());
    }

}
