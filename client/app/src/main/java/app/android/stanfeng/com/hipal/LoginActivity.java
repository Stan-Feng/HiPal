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

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText ed1 = (EditText) findViewById(R.id.ed1);
        final EditText ed2 = (EditText) findViewById(R.id.ed2);
        final Button login1 = (Button) findViewById(R.id.login1);

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://45.79.1.223:3000/auth/signin";
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                JSONObject data = new JSONObject();


                try {
                    data.put("username", "Junwen Feng"); // Exchange data to the view
                    data.put("password", "test123");  // The same
                } catch (JSONException e) {
                    Log.e("JSON parse Error", e.toString());
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
                                Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                                LoginActivity.this.startActivity(mainIntent);
                            } else {
                                Toast.makeText(getBaseContext(), "Invalid Username or Password", Toast.LENGTH_LONG).show();
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
