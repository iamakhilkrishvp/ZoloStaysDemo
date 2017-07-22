package zolo.in.zolostays.zolostaysdemo.Activity.View;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.xwray.passwordview.PasswordView;

import zolo.in.zolostays.zolostaysdemo.Activity.Model.User;
import zolo.in.zolostays.zolostaysdemo.Activity.Model.ZoloDbHelper;
import zolo.in.zolostays.zolostaysdemo.Activity.Presenter.Utility;
import zolo.in.zolostays.zolostaysdemo.R;

public class UserRegistrationActivity extends AppCompatActivity {
    EditText etPhoneNumber, etUserEmailId, etUserName;
    PasswordView etPassword;
    TextView registerButton, goToLoginButton;
    ScrollView parentLayout;
    Utility utility;
    ZoloDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        etPhoneNumber = (EditText) findViewById(R.id.phoneNumertextView);
        etUserEmailId = (EditText) findViewById(R.id.emailIdText);
        etUserName = (EditText) findViewById(R.id.nameText);
        etPassword = (PasswordView) findViewById(R.id.passwordText);

        etPhoneNumber.setHintTextColor(Color.parseColor("#FFEB3B"));
        etUserEmailId.setHintTextColor(Color.parseColor("#FFEB3B"));
        etUserName.setHintTextColor(Color.parseColor("#FFEB3B"));
        etPassword.setHintTextColor(Color.parseColor("#FFEB3B"));
        registerButton = (TextView) findViewById(R.id.textView11);
        goToLoginButton = (TextView) findViewById(R.id.textView8);
        parentLayout = (ScrollView) findViewById(R.id.parentLayout);
        utility = new Utility(getApplicationContext());
        dbHelper = new ZoloDbHelper(getApplicationContext());
        etPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (isValidMobile("" + s)) {

                } else {
                    etPhoneNumber.setError("Invalid Number");
                }
            }
        });
        etUserEmailId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (isValidMail("" + s)) {

                } else {
                    etUserEmailId.setError("Invalid email id");
                }
            }
        });
        goToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUserDetails();
            }
        });

    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void registerUserDetails() {
        if (etPhoneNumber.getText().toString().length() == 0 ||
                etUserEmailId.getText().toString().length() == 0 ||
                etUserName.getText().toString().length() == 0 ||
                etPassword.getText().toString().length() == 0) {
            Snackbar snackbar = Snackbar
                    .make(parentLayout, "Fill all the field.", Snackbar.LENGTH_LONG);

            snackbar.show();
        } else if (!isValidMail(etUserEmailId.getText().toString())) {
            Snackbar snackbar = Snackbar
                    .make(parentLayout, "Invalid Email id", Snackbar.LENGTH_LONG);

            snackbar.show();
        } else if (!isValidMobile(etPhoneNumber.getText().toString())) {
            Snackbar snackbar = Snackbar
                    .make(parentLayout, "Invalid Phone Number", Snackbar.LENGTH_LONG);

            snackbar.show();
        } else {
            if (utility.checkUserPhoneNumber(etPhoneNumber.getText().toString())) {
                Snackbar snackbar = Snackbar
                        .make(parentLayout, "This number is already registered.", Snackbar.LENGTH_LONG);

                snackbar.show();
            } else {
                dbHelper.addUserDetailsToUserDetailsTable(new User(etPhoneNumber.getText().toString(),
                        etUserEmailId.getText().toString(), etUserName.getText().toString(),
                        etPassword.getText().toString()));
                Snackbar snackbar = Snackbar
                        .make(parentLayout, "Registration Completed.", Snackbar.LENGTH_LONG);

                snackbar.show();
            }
        }
    }

}
