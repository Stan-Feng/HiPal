package app.android.stanfeng.com.hipal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.android.stanfeng.com.hipal.utils.AJAX;
import app.android.stanfeng.com.hipal.utils.Callback;

public class UserResultActivity extends AppCompatActivity {

    private int[] user_image = {R.drawable.minions1,R.drawable.minions2,
            R.drawable.minions3,R.drawable.minions4,R.drawable.minions5,R.drawable.minions6};

    private GridView gridview_user_research;
    private Button cancelBtn;
    private String token;
    private User[] users;
    private SimpleAdapter simple;
    private  List<Map<String, Object>> gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_result);

        this.token = getIntent().getExtras().getString("token");

        cancelBtn = (Button) findViewById(R.id.resultCancel);

        //  TODO: show the label by gridView
        gridView = new ArrayList<Map<String, Object>>();

        simple = new SimpleAdapter(this, gridView,
                R.layout.user_research_result_gridview, new String[]{"user_image", "user_name"},
                new int[]{R.id.user_image, R.id.user_name});

        gridview_user_research = (GridView) findViewById(R.id.UserGridView);
        gridview_user_research.setAdapter(simple);
        gridview_user_research.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO: jump to new intent
                Intent profile = new Intent(UserResultActivity.this, UserActivity.class);
                profile.putExtra("token", getIntent().getExtras().getString("token"));
                profile.putExtra("_id", users[position].getId());
                profile.putExtra("nickname", users[position].getNickname());
                profile.putExtra("username", users[position].getUsername());
                profile.putExtra("age", users[position].getAge());
                profile.putExtra("signature", users[position].getSignature());
                profile.putExtra("gender", users[position].getGender());
                profile.putExtra("city", users[position].getCity());
                startActivity(profile);
            }
        });

        setBtnEvents();


        String method = "GET";
        String url = "http://45.79.1.223:3000/api/user";
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + this.token);
        AJAX req = new AJAX(url, method, headers, null, null, new Callback() {
            @Override
            public void exec(Object target, JSONArray results) {
                users = new User[results.length()];

                for (int i = 0; i < results.length(); i++ ) {
                    try {
                        JSONObject user = results.getJSONObject(i);
                        int age=  user.getInt("age");
                        String id =  user.getString("_id");
                        String city =  user.getString("city");
                        String gender =  user.getString("gender");
                        String username =  user.getString("username");
                        String nickname =  user.getString("nickname");
                        String signature =  user.getString("signature");

                        users[i] = new User(username, nickname, id, signature, city, gender, age);
//                        Toast.makeText(getBaseContext(), users[i].toString(), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                }

                String currNickname = getIntent().getExtras().getString("nickname");
                for (int i = 0; i < users.length; i++) {
                    Map<String, Object> item = new HashMap<String, Object>();
                    try {
                        if (currNickname.equals(users[i].getNickname())) continue;
                    } catch (NullPointerException e) {
                        Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                    item.put("user_image", user_image[i]);
                    item.put("user_name", users[i].getNickname());
                    item.put("user_id", users[i].getId());
                    gridView.add(item);
                }

                simple.notifyDataSetChanged();
            }
        });

        // Sending the request
        req.execute();
    }

    public void setBtnEvents () {
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(UserResultActivity.this, MainActivity.class);
                mainIntent.putExtra("token", getIntent().getExtras().getString("token"));
                UserResultActivity.this.startActivity(mainIntent);
            }
        });

    }
}
