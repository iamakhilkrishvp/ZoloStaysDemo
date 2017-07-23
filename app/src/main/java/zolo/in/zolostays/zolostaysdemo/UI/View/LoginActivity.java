package zolo.in.zolostays.zolostaysdemo.UI.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.xwray.passwordview.PasswordView;

import zolo.in.zolostays.zolostaysdemo.R;
import zolo.in.zolostays.zolostaysdemo.UI.Presenter.Utility;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher, View.OnFocusChangeListener {
    TextInputLayout tlPhoneNumberLayout, tlpasswordLayout;
    EditText etPhoneNumber;
    PasswordView etPassword;
    TextView tvForgottenPassword, tvCreateAccountBtn;
    Button btnLogin;
    Utility utility;
    LinearLayout deaActivatedLayout, activatedLayout;
    ScrollView parentLayout;
    private int whoHasFocus = 0;

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
        parentLayout = (ScrollView) findViewById(R.id.parentLayout);
        deaActivatedLayout = (LinearLayout) findViewById(R.id.deActivatedLayout);
        activatedLayout = (LinearLayout) findViewById(R.id.activatedLayout);
        btnLogin.setOnClickListener(this);
        tvCreateAccountBtn.setOnClickListener(this);
        tvForgottenPassword.setOnClickListener(this);
        etPhoneNumber.setHintTextColor(getResources().getColor(R.color.yellow));
        etPassword.setHintTextColor(getResources().getColor(R.color.yellow));
        deaActivatedLayout.setVisibility(View.VISIBLE);
        activatedLayout.setVisibility(View.GONE);
        btnLogin.setEnabled(false);
        btnLogin.setEnabled(false);

        etPhoneNumber.setOnFocusChangeListener(this);
        etPhoneNumber.addTextChangedListener(this);
        etPassword.setOnFocusChangeListener(this);
        etPassword.addTextChangedListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                loginSession();
                break;
            case R.id.createAccountButton:
                Intent intent = new Intent(getApplicationContext(), UserRegistrationActivity.class);
                startActivity(intent);
                break;
            case R.id.forgottenPasswordText:
                Intent intent1 = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(intent1);
                break;
        }
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
                    deaActivatedLayout.setVisibility(View.GONE);
                    activatedLayout.setVisibility(View.VISIBLE);
                    btnLogin.setEnabled(true);
                } else {
                    deaActivatedLayout.setVisibility(View.VISIBLE);
                    activatedLayout.setVisibility(View.GONE);
                    btnLogin.setEnabled(false);
                    etPhoneNumber.setError("Enter valid number");
                }
                break;
            case 2:
                if (isValidPassword()) {
                    deaActivatedLayout.setVisibility(View.GONE);
                    activatedLayout.setVisibility(View.VISIBLE);
                    btnLogin.setEnabled(true);
                } else {
                    deaActivatedLayout.setVisibility(View.VISIBLE);
                    activatedLayout.setVisibility(View.GONE);
                    btnLogin.setEnabled(false);
                }
                break;

        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.phoneNumberText:
                whoHasFocus = 1;
                break;
            case R.id.passwordText:
                whoHasFocus = 2;
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
        if (responseValue == 1) {
            Snackbar snackbar = Snackbar
                    .make(parentLayout, "Log in successfully.", Snackbar.LENGTH_LONG);
            snackbar.show();
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            intent.putExtra("USER_NUMBER", phoneNumber);
            startActivity(intent);
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
