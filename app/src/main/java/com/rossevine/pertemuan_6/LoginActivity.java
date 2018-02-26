package com.rossevine.pertemuan_6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.rossevine.pertemuan_6.entity.UserWrapper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.txtUsername)
    EditText txtUsername;
    @BindView(R.id.txtPassword)
    EditText txtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLogin)
    public void loginBtnOnClick() {

        LoginTask loginTask = new LoginTask(this);
        loginTask.execute(txtUsername.getText().toString(),txtPassword.getText().toString());
    }
    public void updateView(UserWrapper userWrapper) {
        Toast.makeText(this,userWrapper.getMessage() , Toast.LENGTH_LONG).show();
    }
}
