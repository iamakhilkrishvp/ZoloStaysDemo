package zolo.in.zolostays.zolostaysdemo.UI.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.xwray.passwordview.PasswordView;

import zolo.in.zolostays.zolostaysdemo.R;
import zolo.in.zolostays.zolostaysdemo.UI.Model.User;
import zolo.in.zolostays.zolostaysdemo.UI.Model.ZoloDbHelper;
import zolo.in.zolostays.zolostaysdemo.UI.Presenter.Utility;

public class UserRegistrationActivity extends AppCompatActivity implements View.OnFocusChangeListener, TextWatcher {
    EditText etPhoneNumber, etUserEmailId, etUserName;
    PasswordView etPassword;
    TextView registerButton, goToLoginButton;
    ScrollView parentLayout;
    Utility utility;
    ZoloDbHelper dbHelper;
    private int whoHasFocus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        etPhoneNumber = (EditText) findViewById(R.id.phoneNumertextView);
        etUserEmailId = (EditText) findViewById(R.id.emailIdText);
        etUserName = (EditText) findViewById(R.id.nameText);
        etPassword = (PasswordView) findViewById(R.id.passwordText);

        etPhoneNumber.setHintTextColor(getResources().getColor(R.color.yellow));
        etUserEmailId.setHintTextColor(getResources().getColor(R.color.yellow));
        etUserName.setHintTextColor(getResources().getColor(R.color.yellow));
        etPassword.setHintTextColor(getResources().getColor(R.color.yellow));
        registerButton = (TextView) findViewById(R.id.textView11);
        goToLoginButton = (TextView) findViewById(R.id.textView8);
        parentLayout = (ScrollView) findViewById(R.id.parentLayout);
        utility = new Utility(getApplicationContext());
        dbHelper = new ZoloDbHelper(getApplicationContext());
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        etPhoneNumber.setOnFocusChangeListener(this);
        etPhoneNumber.addTextChangedListener(this);
        etUserEmailId.setOnFocusChangeListener(this);
        etUserEmailId.addTextChangedListener(this);
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        switch (whoHasFocus) {
            case 1:
                if (isValidMobile("" + s)) {

                } else {
                    etPhoneNumber.setError("Invalid Number");
                }
                break;
            case 2:
                if (isValidMail("" + s)) {

                } else {
                    etUserEmailId.setError("Invalid email id");
                }
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.phoneNumertextView:
                whoHasFocus = 1;
                break;
            case R.id.emailIdText:
                whoHasFocus = 2;
                break;
        }
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
