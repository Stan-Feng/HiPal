package app.android.stanfeng.com.hipal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CreatePlan extends Fragment {

    private GridView gridview_label;

    private String[] lable = new String[]{"Happy","Happy","Happy","Happy","Happy","Happy"};

    private int[] label_image = {R.drawable.label,R.drawable.label,
            R.drawable.label,R.drawable.label,R.drawable.label,R.drawable.label};

    public CreatePlan() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_plan, container, false);

        List<Map<String, Object>> gridView = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < lable.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("label_text", lable[i]);
            item.put("label_image", label_image[i]);
            gridView.add(item);
        }

        SimpleAdapter simple = new SimpleAdapter(container.getContext(), gridView,
                R.layout.fragment_create_plan_gridview_item, new String[] { "label_text","label_image"},
                new int[] {R.id.label_text,R.id.label_image});
        gridview_label = (GridView) v.findViewById(R.id.gridView);
        gridview_label.setAdapter(simple);



        return v;
    }
}
