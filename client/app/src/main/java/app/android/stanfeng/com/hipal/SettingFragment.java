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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SettingFragment extends Fragment {
    private ListView lv3;
    private TextView tv5;
    private int photo = R.drawable.niuniu_duanwu;
    private Button cancel,edit,jump4;
    private OnButtonClick onButtonClick;
    private String[] leftContent = {"Name", "wechatID", "Signature", "Gender", "City"};
    private String[] rightContent = {"Anan Wang", "Anan_wechatID", "I am Wang Anan", "Female", "Suzhou"};

    public SettingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        cancel = (Button) v.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });



        String s = "Profile Photo";
        tv5 = (TextView) v.findViewById(R.id.nick);
        tv5.setText(s);
        List<Map<String, Object>> listItem3 = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < leftContent.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("left", leftContent[i]);
            item.put("right", rightContent[i]);
            listItem3.add(item);
        }
        SimpleAdapter simple3 = new SimpleAdapter(container.getContext(), listItem3,
                R.layout.fragment_setting_list, new String[]{"left", "right"},
                new int[]{R.id.left, R.id.right});
        lv3 = (ListView) v.findViewById(R.id.listView3);
        lv3.setAdapter(simple3);



        edit = (Button) v.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_setting, new EditProfileFragment())
                        .addToBackStack(null)
                        .commit();            }
        });

        return v;


    }

    public interface OnButtonClick {
        void onClick(View view);
    }
}
