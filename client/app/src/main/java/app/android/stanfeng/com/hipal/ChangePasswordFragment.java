package app.android.stanfeng.com.hipal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ChangePasswordFragment extends Fragment {
    private Button cancel,change;
    private EditText tip1 = null;
    private EditText tip2 = null;
    private EditText tip3 = null;
    private EditText tip4 = null;
    private TextView text1,text2,text3,text4;
    private OnButtonClick onButtonClick;

    public ChangePasswordFragment() {
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View v=inflater.inflate(R.layout.fragment_change_password, container, false);
        cancel = (Button) v.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        String tx1 = "  Old Password";
        text1 = (TextView) v.findViewById(R.id.t1);
        text1.setText(tx1);
        tip1 = (EditText) v.findViewById(R.id.tip1);
        tip1.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView arg0, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    //do something;
                    return true;
                }
                return false;
            }
        });
        String tx2 = "  New Password";
        text2 = (TextView) v.findViewById(R.id.t2);
        text2.setText(tx2);
        tip2 = (EditText) v.findViewById(R.id.tip2);
        tip2.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView arg0, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    //do something;
                    return true;
                }
                return false;
            }
        });
        String tx3 = "  Verify";
        text3 = (TextView) v.findViewById(R.id.t3);
        text3.setText(tx3);
        tip3= (EditText) v.findViewById(R.id.tip3);
        tip3.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView arg0, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    //do something;
                    return true;
                }
                return false;
            }
        });




        change = (Button) v.findViewById(R.id.button2);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Password changed", Toast.LENGTH_LONG).show();
                getActivity().onBackPressed();

            }
        });

        return v;
    }


    public interface OnButtonClick{
        void onClick(View view);
    }
}
