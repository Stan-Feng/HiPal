// Create by Anan Wang
package app.android.stanfeng.com.hipal;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.android.stanfeng.com.hipal.utils.AJAX;
import app.android.stanfeng.com.hipal.utils.Callback;


public class MainActivity extends AppCompatActivity {

    private JSONObject user;
    private CreatePlan createPlanFragment;
    private Plan[] allPlans = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("CreatePlan"));
        tabLayout.addTab(tabLayout.newTab().setText("Share"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) this.findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());


        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        createPlanFragment = (CreatePlan) adapter.getItem(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);


    }

    public JSONObject getUser() {
        return user;
    }

    public void setUser(JSONObject user) {
        this.user = user;
    }

    public CreatePlan getCreatePlanFragment() {
        return createPlanFragment;
    }

    public Plan[] getAllPlans() {
        return allPlans;
    }

    public void setAllPlans(Plan[] allPlans) {
        this.allPlans = allPlans;
    }

    public void getNewPlans () {
        String method = "GET";
        String url = "http://45.79.1.223:3000/api/plan/" + getIntent().getExtras().getString("token");
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

                    }
                } catch (JSONException e) {
                    Log.e("JSON Parse Error", e.toString());
                }
            }
        });

        // Sending the request
        req.execute();
    }
}
