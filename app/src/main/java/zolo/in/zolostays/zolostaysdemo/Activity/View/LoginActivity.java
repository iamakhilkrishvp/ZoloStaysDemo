package zolo.in.zolostays.zolostaysdemo.Activity.View;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xwray.passwordview.PasswordView;

import zolo.in.zolostays.zolostaysdemo.Activity.Presenter.Utility;
import zolo.in.zolostays.zolostaysdemo.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputLayout tlPhoneNumberLayout, tlpasswordLayout;
    EditText etPhoneNumber;
    PasswordView etPassword;
    TextView tvForgottenPassword, tvCreateAccountBtn;
    Button btnLogin;
    Utility utility;
    RelativeLayout parentLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginActivity.this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        etPassword = (PasswordView) findViewById(R.id.passwordText);
        etPhoneNumber = (EditText) findViewById(R.id.phoneNumberText);
        tvForgottenPassword = (TextView) findViewById(R.id.forgottenPasswordText);
        tvCreateAccountBtn = (TextView) findViewById(R.id.createAccountButton);
        btnLogin = (Button) findViewById(R.id.loginButton);
        parentLayout = (RelativeLayout) findViewById(R.id.parentLayout);
        btnLogin.setOnClickListener(this);
        tvCreateAccountBtn.setOnClickListener(this);
        tvForgottenPassword.setOnClickListener(this);
        etPhoneNumber.setHintTextColor(Color.parseColor("#FFEB3B"));
        etPassword.setHintTextColor(Color.parseColor("#FFEB3B"));
        btnLogin.setTextColor(Color.parseColor("#40000000"));
        btnLogin.setBackgroundColor(Color.parseColor("#CCFFEB3B"));
        btnLogin.setEnabled(false);

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
                    btnLogin.setTextColor(Color.parseColor("#000000"));
                    btnLogin.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    btnLogin.setEnabled(true);
                } else {
                    btnLogin.setTextColor(Color.parseColor("#40000000"));
                    btnLogin.setBackgroundColor(Color.parseColor("#CCFFEB3B"));
                    btnLogin.setEnabled(false);
                    etPhoneNumber.setError("Enter valid number");
                }
            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isValidPassword()) {

                    btnLogin.setTextColor(Color.parseColor("#000000"));
                    btnLogin.setBackgroundColor(Color.parseColor("#CCFFEB3B"));
                    btnLogin.setEnabled(true);
                } else {
                    btnLogin.setTextColor(Color.parseColor("#40000000"));
                    btnLogin.setBackgroundColor(Color.parseColor("#CCFFEB3B"));
                    btnLogin.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                loginSession();
                break;
            case R.id.createAccountButton:
               /* Intent intent = new Intent(getApplicationContext(),UserRegistrationActivity.class);
                startActivity(intent);*/
                break;
            case R.id.forgottenPasswordText:
                break;
        }
    }

    public boolean isValidPassword() {

        // boolean isReady = etPassword.getText().toString().length() > 1;
        return etPassword.getText().toString().length() > 1;
    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    public void loginSession() {
        utility = new Utility(getApplicationContext());
        String phoneNumber = etPhoneNumber.getText().toString();
        String password = etPassword.getText().toString();
        int responseValue = utility.getValidityOfUser(phoneNumber, password);
        Log.e("response....", "" + responseValue);
        if (responseValue == 1) {
            Snackbar snackbar = Snackbar
                    .make(parentLayout, "Log in successfully.", Snackbar.LENGTH_LONG);

            snackbar.show();
        } else if (responseValue == 0) {
            Snackbar snackbar = Snackbar
                    .make(parentLayout, "Sorry, we couldin't find you.", Snackbar.LENGTH_LONG);

            snackbar.show();
        } else {
            Snackbar snackbar = Snackbar
                    .make(parentLayout, "Incorrect password.", Snackbar.LENGTH_LONG);

            snackbar.show();
        }

    }
}
