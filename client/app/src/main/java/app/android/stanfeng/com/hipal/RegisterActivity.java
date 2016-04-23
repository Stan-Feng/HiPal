package app.android.stanfeng.com.hipal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText ed3 = (EditText) findViewById(R.id.ed3);
        final EditText ed4 = (EditText) findViewById(R.id.ed4);
        final EditText ed5 = (EditText) findViewById(R.id.ed5);
        final EditText ed6 = (EditText) findViewById(R.id.ed6);
        final Button signup1 = (Button) findViewById(R.id.signup1);

        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(loginIntent);

            }
        });

    }
}
