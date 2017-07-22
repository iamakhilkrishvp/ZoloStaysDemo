package zolo.in.zolostays.zolostaysdemo.Activity.View;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import zolo.in.zolostays.zolostaysdemo.R;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText etUserEmailId;
    TextView resetPasswordButton, goToLoginButton;
    RelativeLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ForgotPasswordActivity.this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        etUserEmailId = (EditText) findViewById(R.id.emailIdText);
        resetPasswordButton = (TextView) findViewById(R.id.textView7);
        goToLoginButton = (TextView) findViewById(R.id.textView3);
        etUserEmailId.setHintTextColor(Color.parseColor("#FFEB3B"));
        parentLayout = (RelativeLayout) findViewById(R.id.parentLayout);
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
        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyBoard();
                if (etUserEmailId.getText().toString().length() == 0) {
                    Snackbar snackbar = Snackbar
                            .make(parentLayout, "Invalid Email address", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else if (!isValidMail(etUserEmailId.getText().toString())) {
                    Snackbar snackbar = Snackbar
                            .make(parentLayout, "Invalid Email Address.", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else {
                    Snackbar snackbar = Snackbar
                            .make(parentLayout, "The reset password email has been sent.", Snackbar.LENGTH_LONG);
                    snackbar.show();
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
    }

    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void hideSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        if (imm.isAcceptingText()) { // verify if the soft keyboard is open
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

}
