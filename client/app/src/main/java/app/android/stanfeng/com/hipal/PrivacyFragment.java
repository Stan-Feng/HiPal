package app.android.stanfeng.com.hipal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class PrivacyFragment extends Fragment {
    private Button cancel;
    private Button showID;

    private Boolean flag = false;
    private String show = "Show WechatID to others.";
    private String hide = "Hide WechatID to others.";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_privacy, container, false);
        cancel = (Button) v.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });


        showID = (Button) v.findViewById(R.id.showID);
        showID.setText("Show WechatID to others.");
        showID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    showID.setText(show);
                    flag = false;
                } else {
                    showID.setText(hide);
                    flag = true;
                }
            }
        });
        return v;
    }


    public interface OnButtonClick{
        void onClick(View view);
    }
}
