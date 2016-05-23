package app.android.stanfeng.com.hipal;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android .support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import app.android.stanfeng.com.hipal.utils.AJAX;
import app.android.stanfeng.com.hipal.utils.Callback;


public class PastPlanFragment extends Fragment {
    private ListView lv2;
    private Button cancel,jump4;
    private OnButtonClick onButtonClick;
    private String[] pastTime = new String[]{"2015.10.1-2015.10.7", "2014.10.1-2014.10.7",
            "2013.10.1-2013.10.7","2012.10.1-2012.10.7","2011.10.1-2011.10.7","2011.10.1-2011.10.7"};
    private String[] PastPlace = new String[] {"Shanghai","Shanghai","Beijing","Hangzhou","Chongqing","Nanjing"};
    private int[] play2 = {R.drawable.minions1,R.drawable.minions2,R.drawable.minions3,
            R.drawable.minions4,R.drawable.minions5,R.drawable.minions6};

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

        MainActivity main = (MainActivity) getContext();
        final Plan[] allPlans = main.getAllPlans();
        if (allPlans == null) {
            main.getNewPlans();
            cancel.performClick();

            return v;
        }
        LinkedList<Plan> archivedPlans = new LinkedList<Plan>();

        for (int i = 0; i < allPlans.length; i++) {
            if (!allPlans[i].getIsArchived()) archivedPlans.add(allPlans[i]);
        }

        List<Map<String, Object>> listItem2 = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < archivedPlans.size(); j++) {
            try {
                Map<String, Object> item2 = new HashMap<String, Object>();
                Plan p = archivedPlans.get(j);
                item2.put("time", p.getStartDate() + " --- " + p.getEndDate());
                item2.put("play", play2[j % 5]);
                item2.put("place","from " + main.getUser().getString("city") + " to " + p.getCity().getString("name"));
                listItem2.add(item2);
            } catch (JSONException e) {
                Log.e("JSON Parse Error", e.toString());
            }

        }
        SimpleAdapter simple2 = new SimpleAdapter(container.getContext(), listItem2,
                R.layout.fragment_main_simple_item, new String[] { "time","play","place"},
                new int[] {R.id.time,R.id.play,R.id.place});
        lv2 = (ListView) v.findViewById(R.id.listView2);
        lv2.setAdapter(simple2);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity main = (MainActivity) getContext();
                Intent detail = new Intent(getActivity(), PostDetail.class);
                try {
                    detail.putExtra("nickname", main.getUser().getString("nickname"));
                    detail.putExtra("signature", main.getUser().getString("signature"));
                    detail.putExtra("city", allPlans[position].getCity().getString("name"));
                    detail.putExtra("token", getActivity().getIntent().getExtras().getString("token"));
                } catch (JSONException e) {
                    Log.e("JSON parser error", e.toString());
                }
                startActivity(detail);
            }
        });

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
