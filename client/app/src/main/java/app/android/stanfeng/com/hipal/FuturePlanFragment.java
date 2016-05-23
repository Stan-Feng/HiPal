package app.android.stanfeng.com.hipal;

import android.app.Activity;
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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import app.android.stanfeng.com.hipal.utils.AJAX;
import app.android.stanfeng.com.hipal.utils.Callback;


public class FuturePlanFragment extends Fragment {
    private ListView lv1;
    private Button cancel;
    private Button add;
    private int[] play1 = {R.drawable.minions1,R.drawable.minions2,R.drawable.minions3,
            R.drawable.minions4,R.drawable.minions5};
    private ViewPager viewPager;

    private Plan[] allPlans;

    public FuturePlanFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_future_plan, container, false);
        viewPager = (ViewPager) getActivity().findViewById(R.id.pager);

        cancel = (Button) v.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        final List<Map<String, Object>> listItem1 = new ArrayList<Map<String, Object>>();

        final SimpleAdapter simple1 = new SimpleAdapter(container.getContext(), listItem1,
                R.layout.fragment_main_simple_item, new String[] { "time","play","place"},
                new int[] {R.id.time,R.id.play,R.id.place});
        lv1 = (ListView) v.findViewById(R.id.listView1);
        lv1.setAdapter(simple1);

        String method = "GET";
        String url = "http://45.79.1.223:3000/api/plan/" + getActivity().getIntent().getExtras().getString("token");
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        AJAX req = new AJAX(url, method, headers, null, null, new Callback() {
            @Override
            public void exec(Object target, JSONArray results) {
                try {
                    JSONArray plans = results.getJSONObject(0).getJSONArray("plans");
                    allPlans = new Plan[plans.length()];

                    for (int i = 0; i < plans.length(); i++) {
                        JSONObject plan = plans.getJSONObject(i);
                        Map<String, Object> item = new HashMap<String, Object>();
                        String startDate = plan.getString("startDate").substring(0, 10);
                        String endDate = plan.getString("endDate").substring(0, 10);

                        allPlans[i] = new Plan(plan.getString("_id"), plan.getString("name"), startDate, endDate,
                                plan.getBoolean("isArchieved"), plan.getJSONObject("city"),
                                plan.getJSONObject("user"), plan.getJSONArray("labels"));

                        if (plan.getBoolean("isArchieved")) continue;
                        item.put("time", startDate + " --- " + endDate);
                        item.put("play", play1[i % 5]);
                        item.put("place", plan.getJSONObject("city").getString("name") + " to "
                                + plan.getJSONObject("user").getString("city"));
                        listItem1.add(item);

                    }
                } catch (JSONException e) {
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                }

                MainActivity main = (MainActivity) getContext();
                main.setAllPlans(allPlans);

                simple1.notifyDataSetChanged();
            }
        });

        // Sending the request
        req.execute();


        add = (Button) v.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager = (ViewPager) getActivity().findViewById(R.id.pager);
                viewPager.setCurrentItem(1);
            }
        });

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    viewPager = (ViewPager) getActivity().findViewById(R.id.pager);
                    viewPager.setCurrentItem(1);
                    MainActivity activity = (MainActivity) getActivity();
                    activity.getCreatePlanFragment().updateView(allPlans[position]);
                } catch (JSONException e) {
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                }

           }
        });
        return v;


    }

    public interface OnButtonClick {
        void onClick(View view);
    }
}
