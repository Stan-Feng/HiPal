package app.android.stanfeng.com.hipal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserResultActivity extends AppCompatActivity {

    private String[] user_name = new String[]{"Sam","Mike","Tony","Kim","Linda","idol"};
    private int[] user_image = {R.drawable.minions1,R.drawable.minions2,
            R.drawable.minions3,R.drawable.minions4,R.drawable.minions5,R.drawable.minions6};

    private GridView gridview_user_research;
    private Button cancelBtn;
    private Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_result);

        cancelBtn = (Button) findViewById(R.id.resultCancel);
        confirmBtn = (Button) findViewById(R.id.resultConfirm);

        //  TODO: show the label by gridView
        List<Map<String, Object>> gridView = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < user_name.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("user_image", user_image[i]);
            item.put("user_name", user_name[i]);
            gridView.add(item);
        }

        final ArrayList<View> selectedViews = new ArrayList<>();

        SimpleAdapter simple = new SimpleAdapter(this, gridView,
                R.layout.user_research_result_gridview, new String[]{"user_image", "user_name"},
                new int[]{R.id.user_image, R.id.user_name});
        gridview_user_research = (GridView) findViewById(R.id.UserGridView);
        gridview_user_research.setAdapter(simple);

        setBtnEvents();
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

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(UserResultActivity.this, MainActivity.class);
                UserResultActivity.this.startActivity(mainIntent);
            }
        });
    }
}
