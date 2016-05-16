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
    private OnButtonClick onButtonClick;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        Toast.makeText(getContext(), getActivity().getIntent().getExtras().getString("token"), Toast.LENGTH_LONG).show();

        viewPager = (ViewPager) container.findViewById(R.id.pager);
        nn = "niuniu";
        sn = "I love duanwu";
        tv1 = (TextView) v.findViewById(R.id.nickname);
        tv1.setText(nn);
        tv2 = (TextView) v.findViewById(R.id.signature);
        tv2.setText(sn);
        tv3 = (TextView) v.findViewById(R.id.blue);
        tv3.setText("");

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


        return v;

    }

    public interface OnButtonClick{
         void onClick(View view);
    }
}
