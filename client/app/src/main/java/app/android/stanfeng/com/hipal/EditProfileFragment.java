package app.android.stanfeng.com.hipal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class EditProfileFragment extends Fragment {

    private Button save,cancel;
    private TextView text1,text2,text3,text4;
    private OnButtonClick onButtonClick;
    private EditText tip1 = null;
    private EditText tip2 = null;
    private EditText tip3 = null;
    private EditText tip4 = null;

    public EditProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity main = (MainActivity) getContext();
        JSONObject user = main.getUser();

        View v = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        cancel = (Button) v.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        String tx1 = "  Nickname";
        text1 = (TextView) v.findViewById(R.id.nick2);
        text1.setText(tx1);
        tip1 = (EditText) v.findViewById(R.id.tip1);

        try {
            tip1.setText(user.getString("nickname"));
        } catch (JSONException e) {
            Log.e("JSON Error", e.toString());
        }

        tip1.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView arg0, int actionId, KeyEvent event) {
                if (actionId==EditorInfo.IME_ACTION_DONE
                        ||(event!=null&&event.getKeyCode()== KeyEvent.KEYCODE_ENTER)) {
                    //do something;
                    return true;
                }
                return false;
            }
        });

        String tx2 = "  Signature";
        text2 = (TextView) v.findViewById(R.id.signature2);
        text2.setText(tx2);
        tip2 = (EditText) v.findViewById(R.id.tip2);
        try {
            tip2.setText(user.getString("signature"));
        } catch (JSONException e) {
            Log.e("JSON Error", e.toString());
        }

        tip2.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView arg0, int actionId, KeyEvent event) {
                if (actionId==EditorInfo.IME_ACTION_DONE
                        ||(event!=null&&event.getKeyCode()== KeyEvent.KEYCODE_ENTER)) {
                    //do something;
                    return true;
                }
                return false;
            }
        });

        String tx3 = " City";
        text3 = (TextView) v.findViewById(R.id.region);
        text3.setText(tx3);
        tip3 = (EditText) v.findViewById(R.id.tip3);
        try {
            tip3.setText(user.getString("city"));
        } catch (JSONException e) {
            Log.e("JSON Error", e.toString());
        }

        tip3.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView arg0, int actionId, KeyEvent event) {
                if (actionId==EditorInfo.IME_ACTION_DONE
                        ||(event!=null&&event.getKeyCode()== KeyEvent.KEYCODE_ENTER)) {
                    //do something;
                    return true;
                }
                return false;
            }
        });

        String tx4 = "  Gender";
        text4 = (TextView) v.findViewById(R.id.gender);
        text4.setText(tx4);
        tip4 = (EditText) v.findViewById(R.id.tip4);
        try {
            tip4.setText(user.getString("gender"));
        } catch (JSONException e) {
            Log.e("JSON Error", e.toString());
        }

        tip4.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView arg0, int actionId, KeyEvent event) {
                if (actionId==EditorInfo.IME_ACTION_DONE
                        ||(event!=null&&event.getKeyCode()== KeyEvent.KEYCODE_ENTER)) {
                    //do something;
                    return true;
                }
                return false;
            }
        });

        save = (Button) v.findViewById(R.id.saveChange);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Save Change Clicked", Toast.LENGTH_LONG).show();
                getActivity().onBackPressed();
            }
        });


        return v;
    }



    public interface OnButtonClick{
        void onClick(View view);
    }

}
