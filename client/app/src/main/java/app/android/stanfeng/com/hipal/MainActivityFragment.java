package app.android.stanfeng.com.hipal;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import app.android.stanfeng.com.hipal.utils.AJAX;
import app.android.stanfeng.com.hipal.utils.Callback;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private TextView tv1,tv2,tv3;
    private String nn;
    private String sn;
    private ViewPager viewPager;
    private Button profile;
    private Button jump1,jump2,jump3;

    private String userToken;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        this.userToken = getActivity().getIntent().getExtras().getString("token");
//        Toast.makeText(getContext(), getActivity().getIntent().getExtras().getString("token"), Toast.LENGTH_LONG).show();

        viewPager = (ViewPager) container.findViewById(R.id.pager);
        nn = "niuniu";
        sn = "I love duanwu";
        tv1 = (TextView) v.findViewById(R.id.nickname);
//        tv1.setText(nn);
        tv2 = (TextView) v.findViewById(R.id.signature);
        tv2.setText(sn);
        tv3 = (TextView) v.findViewById(R.id.blue);
        tv3.setText("");

        initViewEvent(v);

        // AJAX request to extract new data from server
        String method = "GET";
        String url = "http://45.79.1.223:3000/api/user/me";
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + this.userToken);

        AJAX req = new AJAX(url, method, headers, null, null, new Callback() {
            @Override
            // The parameter "target" in this case is corresponded to param "myAdapter" above
            public void exec(Object target, JSONArray results) {
                // Extract "title" property from results
                try {
                    JSONObject response = results.getJSONObject(0);

                    MainActivity main = (MainActivity) getContext();
                    main.setUser(response);

                    tv1.setText(response.getString("nickname"));
                    tv2.setText(response.getString("signature"));
                } catch (JSONException e) {
                    Toast.makeText(getContext(), "JSON Error", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Sending the request
        req.execute();

       return v;

    }

    private void initViewEvent (View v) {
        profile = (Button) v.findViewById(R.id.profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, new SettingFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        jump1 = (Button) v.findViewById(R.id.jump1);

        jump1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, new FuturePlanFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        jump2 = (Button) v.findViewById(R.id.jump2);

        jump2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, new PastPlanFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        jump3 = (Button) v.findViewById(R.id.jump3);

        jump3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, new SecurityFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

}
