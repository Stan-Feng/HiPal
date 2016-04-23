package app.android.stanfeng.com.hipal;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CreatePlan extends Fragment {

    private GridView gridview_label;
    private String[] lable = new String[]{"taste","movie","sport","music","sleep","idol"};
    private int[] label_image = {R.drawable.minions1,R.drawable.minions2,
            R.drawable.minions3,R.drawable.minions4,R.drawable.minions5,R.drawable.minions6};
    private Spinner departure_spinner;
    private Spinner destination_spinner;
    private ArrayAdapter<CharSequence> adapter1;
    private ArrayAdapter<CharSequence> adapter2;

    public CreatePlan() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_plan, container, false);
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

                TextView tv = (TextView)view;
                tv.setTextColor(getResources().getColor(R.color.colorW));    //设置颜色

                tv.setTextSize(20.0f);    //设置大小

                tv.setGravity(android.view.Gravity.CENTER_HORIZONTAL);
                Toast.makeText(getContext(), parent.getItemAtPosition(position) + "selected",
                        Toast.LENGTH_LONG).show();
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
                TextView tv = (TextView)view;
                tv.setTextColor(getResources().getColor(R.color.colorW));    //设置颜色

                tv.setTextSize(20.0f);    //设置大小

                tv.setGravity(android.view.Gravity.CENTER_HORIZONTAL);
                Toast.makeText(getContext(), parent.getItemAtPosition(position) + "selected",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //  show the label by grid view
        List<Map<String, Object>> gridView = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < lable.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("label_text", lable[i]);
            item.put("label_image", label_image[i]);
            gridView.add(item);
        }

        final ArrayList<View> selectedViews = new ArrayList<>();

        SimpleAdapter simple = new SimpleAdapter(container.getContext(), gridView,
                R.layout.fragment_create_plan_gridview_item, new String[] { "label_text","label_image"},
                new int[] {R.id.label_title,R.id.label_image});
        gridview_label = (GridView) v.findViewById(R.id.gridView);
        gridview_label.setAdapter(simple);
        gridview_label.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (selectedViews.indexOf(view) >= 0) {
                    view.setBackgroundColor(Color.parseColor("#EEEEEE"));
                    selectedViews.remove(selectedViews.indexOf(view));
                } else if (selectedViews.size() >= 5) {
                    Toast.makeText(getActivity(), "You can only selected 5 labels!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    view.setBackgroundColor(Color.parseColor("#68bee6"));
                        selectedViews.add(view);
                }
            }
        });


        return v;
    }

}
