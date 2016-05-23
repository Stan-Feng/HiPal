package app.android.stanfeng.com.hipal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.android.stanfeng.com.hipal.utils.AJAX;
import app.android.stanfeng.com.hipal.utils.Callback;


public class CreatePlan extends Fragment {

    // show label
    private GridView gridview_label;

    private String[] label = new String[]{"taste","movie","sport","music","sleep","idol"};
    private int refreshFlag = 0;
    private String[] allLabels;
    private HashMap<Integer, Boolean> selectedLabels;

    private int[] label_image = {R.drawable.minions1,R.drawable.minions2,
            R.drawable.minions3,R.drawable.minions4,R.drawable.minions5,R.drawable.minions6};
    private Spinner departure_spinner;
    private Spinner destination_spinner;

    private ArrayAdapter<CharSequence> adapter1;
    private ArrayAdapter<CharSequence> adapter2;

    // choose date by dialog
    private int Year;
    private int Month;
    private int Day;
    public DatePickerDialog departure_date_dialog;
    private DatePickerDialog destination_date_dialog;
    private TextView departure_date_text_view;
    private TextView destination_date_text_view;

    private SimpleAdapter simple;
    private List<Map<String, Object>> gridView;
    private String[] a = {"false","true","false","true","false","false"};


    public CreatePlan() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_plan, container, false);

        initLabels();

        String tx4 = "----";
        TextView text = (TextView) v.findViewById(R.id.lineline);
        text.setText(tx4);
        // choose departure city
        departure_spinner = (Spinner) v.findViewById(R.id.departure_spinner);
        adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.city_name,
                android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        departure_spinner.setAdapter(adapter1);
        departure_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent == null || view == null) {
                    return;
                }
                TextView tv = (TextView)view;
                tv.setTextColor(getResources().getColor(R.color.colorW));    //设置颜色

                tv.setTextSize(20.0f);    //设置大小

                tv.setGravity(android.view.Gravity.CENTER_HORIZONTAL);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // choose destination city
        destination_spinner = (Spinner) v.findViewById(R.id.destination_spinner);
        adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.city_name,
                android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        destination_spinner.setAdapter(adapter2);
        destination_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (view == null || parent == null) return;

                TextView tv = (TextView)view;
                tv.setTextColor(getResources().getColor(R.color.colorW));    //设置颜色

                tv.setTextSize(20.0f);    //设置大小

                tv.setGravity(android.view.Gravity.CENTER_HORIZONTAL);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //  TODO: show the label by gridView
        gridView = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < label.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("label_text", label[i]);
            item.put("label_image", label_image[i]);
            gridView.add(item);
        }

        simple = new SimpleAdapter(container.getContext(), gridView,
                R.layout.fragment_create_plan_gridview_item, new String[]{"label_text", "label_image"},
                new int[]{R.id.label_title, R.id.label_image});
        gridview_label = (GridView) v.findViewById(R.id.gridView);
        gridview_label.setAdapter(simple);

        final ArrayList<View> selectedViews = new ArrayList<>();
        gridview_label.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int indexPosition = selectedViews.indexOf(view);
                if (indexPosition >= 0) {
                    view.setBackgroundColor(Color.parseColor("#ffffff"));
                    selectedViews.remove(selectedViews.indexOf(view));
                    selectedLabels.put(position, false);
                    a[indexPosition] = "true";
                } else if (selectedViews.size() >= 5) {
                    Toast.makeText(getActivity(), "You can only selected 5 labels!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    view.setBackgroundColor(Color.parseColor("#68bee6"));
                    selectedViews.add(view);
                    selectedLabels.put(position, true);
                }
            }
        });


        // TODO: user choose the date for the departure and destination by two separate dialogs

        //  get the current date
        final Calendar cal = Calendar.getInstance();
        Year = cal.get(Calendar.YEAR);
        Month = cal.get(Calendar.MONTH) + 1;
        Day = cal.get(Calendar.DAY_OF_MONTH);
        Log.v("Year", "this is year");


        // init the date for departure
        departure_date_text_view = (TextView) v.findViewById(R.id.departure_date_text_view);
        departure_date_text_view.setText(Month + " - " + Day + " - " + Year);
        departure_date_text_view.setTextColor(getResources().getColor(R.color.colorW));
        departure_date_text_view.setTextSize(13.0f);    //设置大小

        //  init the DatePickerDialog
        DatePickerDialog.OnDateSetListener dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear++;
                departure_date_text_view.setText(monthOfYear + " - " + dayOfMonth + " - " + year);
            }
        };
        departure_date_dialog = new DatePickerDialog(getContext(), dateSetListener1, Year, Month, Day);


        //  set the clickListener to show the datePickerDialog
        departure_date_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                departure_date_dialog.show();
            }
        });



        //  init the date  for destination
        destination_date_text_view = (TextView) v.findViewById(R.id.destination_date_text_view);
        destination_date_text_view.setText(Month + " - " + Day + " - " + Year);
        destination_date_text_view.setTextColor(getResources().getColor(R.color.colorW));
        destination_date_text_view.setTextSize(13.0f);    //设置大小



        //  init the DatePickerDialog
        DatePickerDialog.OnDateSetListener dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                monthOfYear++;
                destination_date_text_view.setText(monthOfYear + " - " + dayOfMonth + " - " + year);

            }
        };
        destination_date_dialog = new DatePickerDialog(getContext(), dateSetListener2, Year, Month, Day);


        // set the clickListener to show the datePickerDialog
        destination_date_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destination_date_dialog.show();
            }
        });

        // Init selectedLabels map
        selectedLabels = new HashMap<Integer, Boolean>();
        for (int i = 0; i < 6; i++ ) {
            selectedLabels.put(i, false);
        }

        //click refresh button to send request and update the label from serve
        final Button refresh = (Button) v.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int[] unselectedPos = unselectedPosition();
                for (int i = refreshFlag, j = 0; i < refreshFlag + unselectedPos.length && j < unselectedPos.length; i++, j++) {
                    if (i >= allLabels.length - 1) refreshFlag = 0;
                    Map<String, Object> item = new HashMap<String, Object>();
                    item.put("label_text", allLabels[i]);
                    item.put("label_image", label_image[i % 6]);
                    gridView.set(unselectedPos[j], item);
                }
                simple.notifyDataSetChanged();
                refreshFlag += 6;
            }
        });

        // TODO: click confirm button to send request and show the search results from serve
        Button confirm = (Button) v.findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Make AJAX Request
                Intent userResult = new Intent(getActivity(), UserResultActivity.class);
                userResult.putExtra("token", getActivity().getIntent().getExtras().getString("token"));
                try {
                    MainActivity main = (MainActivity) getContext();
                    String nickname = main.getUser().getString("nickname");
                    userResult.putExtra("nickname", nickname);
                } catch (JSONException e) {
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
                startActivity(userResult);
            }
        });


        return v;
    }

    private void initLabels () {
        String method = "GET";
        String url = "http://45.79.1.223:3000/api/label";
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        AJAX req = new AJAX(url, method, headers, null, null, new Callback() {
            @Override
            public void exec(Object target, JSONArray results) {
                allLabels = new String[results.length()];

                for (int i = 0; i < results.length(); i++) {
                    try {
                         allLabels[i] = results.getJSONObject(i).getString("name");
                    } catch (JSONException e) {
                        Log.e("JSON Exception", e.toString());
                    }
                }
            }
        });

        // Sending the request
        req.execute();
    }

    private int[] unselectedPosition () {
        int selectedNum = 0;
        int[] pos = {-1, -1, -1, -1, -1, -1};

        for (Integer key: this.selectedLabels.keySet()) {
            if (!selectedLabels.get(key)) {
                pos[key] = key;
                selectedNum++;
            }
        }

        int[] selectedLabelPos = new int[selectedNum];
        for (int i = 0, j = 0; i < pos.length && j < selectedLabelPos.length; i++) {
            if (pos[i] != -1) {
                selectedLabelPos[j] = pos[i];
                j++;
            }
        }

        return selectedLabelPos;
    }

    public void updateView (Plan newPlan) throws JSONException {
        String starDate = newPlan.getStartDate();
        String endDate = newPlan.getEndDate();
        String destination = newPlan.getCity().getString("name");
        JSONObject label = newPlan.getLabels().getJSONObject(0);
        String departure = newPlan.getUser().getString("city");

        destination_date_text_view.setText(endDate);
        departure_date_text_view.setText(starDate);

        for (int i = 0; i < 19; i++) {
            if (departure_spinner.getItemAtPosition(i).equals(destination)) {
                departure_spinner.setSelection(i);
                break;
            }
        }

        for (int i = 0; i < 19; i++) {
            if (destination_spinner.getItemAtPosition(i).equals(departure)) {
                destination_spinner.setSelection(i);
                break;
            }
        }

        String labelName = label.getString("name");
        Map<String, Object> item = new HashMap<String, Object>();
        item.put("label_text", labelName);
        item.put("label_image", label_image[0]);
        gridView.set(0, item);
        simple.notifyDataSetChanged();
        gridview_label.performItemClick(gridview_label.getChildAt(0), 0, gridview_label.getItemIdAtPosition(0));
        Log.e("View", simple.getView(0, null, null).toString());
    }
}
