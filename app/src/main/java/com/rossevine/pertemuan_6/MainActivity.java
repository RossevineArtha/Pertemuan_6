package com.rossevine.pertemuan_6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.rossevine.pertemuan_6.entity.StatusMessage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.name)
    EditText txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    void btnClick() {
        HelloTask helloTask = new HelloTask(this);
        helloTask.execute(txtName.getText().toString());

    }

    public void updateView(StatusMessage statusMessage) {
        Toast.makeText(this, statusMessage.getMessage(), Toast.LENGTH_LONG).show();
    }
}
