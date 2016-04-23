package app.android.stanfeng.com.hipal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShareFragment extends Fragment {

    private String[] ID = new String[]{"Cindy", "Mary", "James", "John", "Linda", "Helen", "Gary", "Ann", "Diana", "Fred"};
    private String[] comment = new String[]{"There are many beautiful scenic spots in this city.", "So beautiful!!",
            "There are many beautiful scenic spots in this city.", "So beautiful!!",
            "There are many beautiful scenic spots in this city.", "So beautiful!!",
            "There are many beautiful scenic spots in this city.", "So beautiful!!",
            "There are many beautiful scenic spots in this city.", "So beautiful!!"};
    private int[] avatar = {R.drawable.share1, R.drawable.share2, R.drawable.share3, R.drawable.share4, R.drawable.share5, R.drawable.share6, R.drawable.share7, R.drawable.share8, R.drawable.share9, R.drawable.share10};
    private int[] images1 = {R.drawable.image1,R.drawable.image2,R.drawable.image4,R.drawable.image5,R.drawable.image6,R.drawable.image10,R.drawable.image7,R.drawable.image1,R.drawable.image9,R.drawable.image10};
    private int[] images2 = {R.drawable.image2,R.drawable.image7,R.drawable.image7,R.drawable.image2,R.drawable.image6,R.drawable.image7,R.drawable.image1,R.drawable.image9,R.drawable.image10,R.drawable.image1};
    private int[] images3 = {R.drawable.image9,R.drawable.image4,R.drawable.image5,R.drawable.image6,R.drawable.image7,R.drawable.image2,R.drawable.image9,R.drawable.image10,R.drawable.image2,R.drawable.image1};
    private int[] images4 = {R.drawable.image4,R.drawable.image5,R.drawable.image6,R.drawable.image7,R.drawable.image4,R.drawable.image9,R.drawable.image10,R.drawable.image1,R.drawable.image1,R.drawable.image4};

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View r = inflater.inflate(R.layout.fragment_share, container, false);

        spinner = (Spinner)r.findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(getActivity(),R.array.city_name,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                TextView tv = (TextView) view;
//                tv.setTextColor(getResources().getColor(R.color.colorW));    //设置颜色
//                tv.setTextSize(20.0f);    //设置大小
//                tv.setGravity(android.view.Gravity.CENTER_HORIZONTAL);
                Toast.makeText(getContext(), parent.getItemAtPosition(position) + "selected", Toast.LENGTH_LONG).show();
                Log.w("Share wrong selected: ", parent.getItemAtPosition(position) + "selected");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        List<Map<String, Object>> listItem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < ID.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("ID", ID[i]);
            item.put("Avatar", avatar[i]);
            item.put("Comment",comment[i]);
            item.put("Images1", images1[i]);
            item.put("Images2", images2[i]);
            item.put("Images3", images3[i]);
            item.put("Images4", images4[i]);
            listItem.add(item);
        }
        SimpleAdapter simple = new SimpleAdapter(container.getContext(), listItem,
                R.layout.fragment_share_item_view, new String[] { "ID","Avatar","Comment","Images1","Images2","Images3","Images4"},
                new int[] {R.id.ID,R.id.avatar,R.id.comment,R.id.images1,R.id.images2,R.id.images3,R.id.images4});
        ListView listView = (ListView) r.findViewById(R.id.ListView);
        listView.setAdapter(simple);



        return r;

    }
}