package app.android.stanfeng.com.hipal;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserActivity extends AppCompatActivity {
    private TextView t1, t2,t3;
    private ListView l1,l2,l3;
    private Button cancel;
    private String[] leftContent = {"Name", "ID Number", "Signature", "Gender", "Region"};
    private String[] rightContent = {"niuniu", "123456789", "I love duanwu", "Female", "China"};
    private String[] futureTime= new String[]{"2015.10.1-2015.10.7","2016.10.1-2016.10.7",
            "2017.10.1-2017.10.7","2018.10.1-2018.10.7","2019.10.1-2019.10.7"};
    private String[] futurePlace = new String[] {"Shanghai","Beijing","Hangzhou","Wuhan","Tianjing"};
    private int[] play1 = {R.drawable.minions1,R.drawable.minions2,R.drawable.minions3,
            R.drawable.minions4,R.drawable.minions5};
    private String[] pastTime = new String[]{"2015.10.1-2015.10.7", "2014.10.1-2014.10.7",
            "2013.10.1-2013.10.7","2012.10.1-2012.10.7","2011.10.1-2011.10.7","2011.10.1-2011.10.7"};
    private String[] PastPlace = new String[] {"Shanghai","Shanghai","Beijing","Hangzhou","Chongqing","Nanjing"};
    private int[] play2 = {R.drawable.minions1,R.drawable.minions2,R.drawable.minions3,
            R.drawable.minions4,R.drawable.minions5,R.drawable.minions6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        String nn = "Profile Photo";
        t1 = (TextView) findViewById(R.id.nick);
        t1.setText(nn);
        t3 = (TextView) findViewById(R.id.blue);
        t3.setText("");

        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        List<Map<String, Object>> listItem3 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < leftContent.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("left", leftContent[i]);
            item.put("right", rightContent[i]);
            listItem3.add(item);
        }
        SimpleAdapter simple3 = new SimpleAdapter(findViewById(R.id.user_layout).getContext(), listItem3,
                R.layout.fragment_setting_list, new String[]{"left", "right"},
                new int[]{R.id.left, R.id.right});
        l3 = (ListView) findViewById(R.id.listView3);
        l3.setAdapter(simple3);

        List<Map<String, Object>> listItem1 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < futureTime.length; i++) {
            Map<String, Object> item1 = new HashMap<String, Object>();
            item1.put("time", futureTime[i]);
            item1.put("play", play1[i]);
            item1.put("place",futurePlace[i]);
            listItem1.add(item1);
        }
        SimpleAdapter simple1 = new SimpleAdapter(findViewById(R.id.user_layout).getContext(), listItem1,
                R.layout.fragment_main_simple_item, new String[] { "time","play","place"},
                new int[] {R.id.time,R.id.play,R.id.place});
        l1 = (ListView) findViewById(R.id.listView4);
        l1.setAdapter(simple1);
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(findViewById(R.id.user_layout).getContext(), "Future plan is Clicked",
                        Toast.LENGTH_LONG).show();

            }
        });
        List<Map<String, Object>> listItem2 = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < pastTime.length; j++) {
            Map<String, Object> item2 = new HashMap<String, Object>();
            item2.put("time", pastTime[j]);
            item2.put("play", play2[j]);
            item2.put("place",PastPlace[j]);
            listItem2.add(item2);
        }
        SimpleAdapter simple2 = new SimpleAdapter(findViewById(R.id.user_layout).getContext(), listItem2,
                R.layout.fragment_main_simple_item, new String[] { "time","play","place"},
                new int[] {R.id.time,R.id.play,R.id.place});
        l2 = (ListView) findViewById(R.id.listView5);
        l2.setAdapter(simple2);
        l2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(findViewById(R.id.user_layout).getContext(), "Past plan is Clicked",
                        Toast.LENGTH_LONG).show();
            }
        });


    }
}
