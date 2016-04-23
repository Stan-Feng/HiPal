package app.android.stanfeng.com.hipal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class SecurityFragment extends Fragment {

    private Button change,privacy,logOut;
    private Button cancel;
    private OnButtonClick onButtonClick;

    public SecurityFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_security, container, false);
        cancel = (Button) v.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        change = (Button) v.findViewById(R.id.changePassword);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Change Password Clicked", Toast.LENGTH_LONG).show();

            }
        });

        privacy = (Button) v.findViewById(R.id.privacy);

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Privacy Clicked", Toast.LENGTH_LONG).show();

            }
        });

        logOut = (Button) v.findViewById(R.id.logOut);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Log Out Clicked", Toast.LENGTH_LONG).show();

            }
        });

        return v;
    }

    public interface OnButtonClick{
        void onClick(View view);
    }



}
