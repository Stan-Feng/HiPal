// Created by Mengyu Wang
package app.android.stanfeng.com.hipal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import app.android.stanfeng.com.hipal.utils.AJAX;
import app.android.stanfeng.com.hipal.utils.Callback;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText ed3 = (EditText) findViewById(R.id.ed3);
        final EditText ed4 = (EditText) findViewById(R.id.ed4);
        final EditText ed5 = (EditText) findViewById(R.id.ed5);
        final EditText ed6 = (EditText) findViewById(R.id.ed6);
        final EditText ed7 = (EditText) findViewById(R.id.ed7);
        final EditText ed8 = (EditText) findViewById(R.id.ed8);
        final EditText ed9 = (EditText) findViewById(R.id.ed9);
        final Button signup1 = (Button) findViewById(R.id.signup1);

        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://45.79.1.223:3000/api/user";
                String method = "POST";
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                JSONObject data = new JSONObject();

                try {
                    data.put("username", ed3.getText().toString());
                    data.put("nickname", ed4.getText().toString());
                    data.put("gender", ed5.getText().toString());
                    data.put("age", ed6.getText().toString());
                    data.put("city", ed7.getText().toString());
                    data.put("signature", ed8.getText().toString());
                    data.put("password", ed9.getText().toString());
                } catch (JSONException e) {
                    Toast.makeText(getBaseContext() ,"Invalid input", Toast.LENGTH_LONG).show();

                    return;
                }

                // We do nothing on view layer in this example, so set the 5th param to "null"
                AJAX req = new AJAX(url, "POST", headers, data, null, new Callback() {
                    @Override
                    public void exec(Object target, JSONArray results) {
                        // Do nothing, just printout the response token
                        String token = null;

                        Log.w("Auth Response", results.toString());
                        try {
                            token = results.getJSONObject(0).getString("token");
                        } catch (JSONException e) {
                            Log.e("Parse JSON Error", e.toString());
                        } finally {
                            if (token != null) {
                                // After authentication
                                Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                                mainIntent.putExtra("token", token);
                                RegisterActivity.this.startActivity(mainIntent);
                            } else {
                                Toast.makeText(getBaseContext(), "Account name has been used", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
                // Sending request
                req.execute();
            }
        });

    }
}
