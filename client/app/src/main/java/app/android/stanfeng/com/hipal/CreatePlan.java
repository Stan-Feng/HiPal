package app.android.stanfeng.com.hipal;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CreatePlan extends Fragment {

    // show label
    private GridView gridview_label;
    private String[] lable = new String[]{"taste", "movie", "sport", "music", "sleep", "idol"};
    private int[] label_image = {R.drawable.label, R.drawable.label,
            R.drawable.label, R.drawable.label, R.drawable.label, R.drawable.label};

    // choose city by spinner
    private Spinner departure_city_spinner;
    private Spinner destination_city_spinner;
    private ArrayAdapter<CharSequence> adapter1;
    private ArrayAdapter<CharSequence> adapter2;

    // choose date by dialog
    private int Year;
    private int Month;
    private int Day;
    private DatePickerDialog departure_date_dialog;
    private DatePickerDialog destination_date_dialog;
    private TextView departure_date_text_view;
    private TextView destination_date_text_view;


    public CreatePlan() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_plan, container, false);


        // TODO : choose the two city by spinner
        //  choose departure city
        departure_city_spinner = (Spinner) v.findViewById(R.id.departure_city);
        adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.city_name, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        departure_city_spinner.setAdapter(adapter1);
        departure_city_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getContext(), parent.getItemAtPosition(position) + "selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //  choose destination city
        destination_city_spinner = (Spinner) v.findViewById(R.id.destination_city);
        adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.city_name, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        destination_city_spinner.setAdapter(adapter2);
        destination_city_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getContext(), parent.getItemAtPosition(position) + "selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //  TODO: show the label by gridView
        List<Map<String, Object>> gridView = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < lable.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("label_text", lable[i]);
            item.put("label_image", label_image[i]);
            gridView.add(item);
        }

        final ArrayList<View> selectedViews = new ArrayList<>();

        SimpleAdapter simple = new SimpleAdapter(container.getContext(), gridView,
                R.layout.fragment_create_plan_gridview_item, new String[]{"label_text", "label_image"},
                new int[]{R.id.label_title, R.id.label_image});
        gridview_label = (GridView) v.findViewById(R.id.gridView);
        gridview_label.setAdapter(simple);
        gridview_label.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (selectedViews.indexOf(view) >= 0) {
                    view.setBackgroundColor(Color.parseColor("#EEEEEE"));
                    selectedViews.remove(selectedViews.indexOf(view));
                } else if (selectedViews.size() >= 5) {
                    Toast.makeText(getActivity(), "You can only selected 5 labels!", Toast.LENGTH_SHORT).show();
                } else {
                    view.setBackgroundColor(Color.parseColor("#68bee6"));
                    selectedViews.add(view);
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
        return v;
    }
}
