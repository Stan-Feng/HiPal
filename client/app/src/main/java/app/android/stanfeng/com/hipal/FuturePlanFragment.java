package app.android.stanfeng.com.hipal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android .support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FuturePlanFragment extends Fragment {
    private ListView lv1;
    private Button cancel;
    private Button add;
    private OnButtonClick onButtonClick;
    private String[] futureTime= new String[]{"2015.10.1-2015.10.7","2016.10.1-2016.10.7",
            "2017.10.1-2017.10.7","2018.10.1-2018.10.7","2019.10.1-2019.10.7"
            };
    private String[] futurePlace = new String[] {"Shanghai","Beijing","Hangzhou","Wuhan","Tianjing"};
    private int[] play1 = {R.drawable.pencil2,R.drawable.pencil2,R.drawable.pencil2,R.drawable.pencil2,R.drawable.pencil2};
    private ViewPager viewPager;

    public FuturePlanFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_future_plan, container, false);
        viewPager = (ViewPager) container.findViewById(R.id.pager);

        cancel = (Button) v.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
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

        add = (Button) v.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewPager.setCurrentItem(1);
            }
        });
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewPager = (ViewPager) getActivity().findViewById(R.id.pager);
                viewPager.setCurrentItem(1);
            }
        });
        return v;


    }

    public interface OnButtonClick {
        void onClick(View view);
    }
}
