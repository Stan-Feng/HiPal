package app.android.stanfeng.com.hipal;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private TextView tv1,tv2,tv3,tv4;
    private String nn;
    private String sn;
    private ListView lv1,lv2;

    private String[] futureTime= new String[]{"2016.10.1-2016.10.7", "2017.10.1-2017.10.7",
            "2015.10.1-2015.10.7"};
    private String[] pastTime = new String[]{"2015.10.1-2015.10.7", "2014.10.1-2014.10.7",
            "2013.10.1-2013.10.7","2012.10.1-2012.10.7","2011.10.1-2011.10.7"};
    private String[] futurePlace = new String[] {"Shanghai","Beijing","Hangzhou"};
    private String[] PastPlace = new String[] {"Shanghai","Beijing","Hangzhou","Chongqing","Nanjing"};

    private int[] play1 = {R.drawable.pencil2,R.drawable.pencil2,R.drawable.pencil2};
    private int[] play2 = {R.drawable.pencil3,R.drawable.pencil3,R.drawable.pencil3,R.drawable.pencil3,R.drawable.pencil3};
    private ImageButton setting;
    private ImageButton add;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        nn = "niuniu";
        sn = "I love duanwu";
        tv1 = (TextView) v.findViewById(R.id.nickname);
        tv1.setText(nn);
        tv2 = (TextView) v.findViewById(R.id.signature);
        tv2.setText(sn);
        tv3 = (TextView) v.findViewById(R.id.futurePlan);
        String cp = "Future Travel Plan";
        tv3.setText(cp);
        tv4 = (TextView) v.findViewById(R.id.pastPlan);
        String hp = "Past Travel Plan";
        tv4.setText(hp);

        setting = (ImageButton) v.findViewById(R.id.imageButton1);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(container.getContext(), "Setting Button Clicked ", Toast.LENGTH_LONG).show();
            }
        });

        add = (ImageButton) v.findViewById(R.id.imageButton2);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(container.getContext(), "Add Button Clicked ", Toast.LENGTH_LONG).show();
            }
        });

        List<Map<String, Object>> listItem1 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < futureTime.length; i++) {
            Map<String, Object> item1 = new HashMap<String, Object>();
            item1.put("time", futureTime[i]);
            item1.put("play", play1[i]);
            item1.put("place",futurePlace[i]);
            listItem1.add(item1);
        }
        SimpleAdapter simple1 = new SimpleAdapter(container.getContext(), listItem1,
                R.layout.fragment_main_simple_item, new String[] { "time","play","place"},
                new int[] {R.id.time,R.id.play,R.id.place});
        lv1 = (ListView) v.findViewById(R.id.listView1);
        lv1.setAdapter(simple1);



        List<Map<String, Object>> listItem2 = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < pastTime.length; j++) {
            Map<String, Object> item2 = new HashMap<String, Object>();
            item2.put("time", pastTime[j]);
            item2.put("play", play2[j]);
            item2.put("place",PastPlace[j]);
            listItem2.add(item2);
        }
        SimpleAdapter simple2 = new SimpleAdapter(container.getContext(), listItem2,
                R.layout.fragment_main_simple_item, new String[] { "time","play","place"},
                new int[] {R.id.time,R.id.play,R.id.place});
        lv2 = (ListView) v.findViewById(R.id.listView2);
        lv2.setAdapter(simple2);



        return v;
    }
}
