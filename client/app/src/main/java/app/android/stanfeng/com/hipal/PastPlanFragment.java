package app.android.stanfeng.com.hipal;

import android.content.Context;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PastPlanFragment extends Fragment {
    private ListView lv2;
    private Button cancel,jump4;
    private OnButtonClick onButtonClick;
    private String[] pastTime = new String[]{"2015.10.1-2015.10.7", "2014.10.1-2014.10.7",
            "2013.10.1-2013.10.7","2012.10.1-2012.10.7","2011.10.1-2011.10.7","2011.10.1-2011.10.7"};
    private String[] PastPlace = new String[] {"Shanghai","Shanghai","Beijing","Hangzhou","Chongqing","Nanjing"};
    private int[] play2 = {R.drawable.pencil3,R.drawable.pencil3,R.drawable.pencil3,R.drawable.pencil3,R.drawable.pencil3,R.drawable.pencil3};

    public PastPlanFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_past_plan, container, false);
        cancel = (Button) v.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
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
        SimpleAdapter simple2 = new SimpleAdapter(container.getContext(), listItem2,
                R.layout.fragment_main_simple_item, new String[] { "time","play","place"},
                new int[] {R.id.time,R.id.play,R.id.place});
        lv2 = (ListView) v.findViewById(R.id.listView2);
        lv2.setAdapter(simple2);

        jump4 = (Button) v.findViewById(R.id.jump4);

        jump4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_past_plan, new FuturePlanFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        return v;


    }

    public interface OnButtonClick {
        void onClick(View view);
    }
}
