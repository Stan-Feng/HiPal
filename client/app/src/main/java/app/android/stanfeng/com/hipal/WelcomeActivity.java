package app.android.stanfeng.com.hipal;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<View> mImageViews;
    private int[] imageResId; // image ID
    private int currentItem = 0;
    private GestureDetector gestureDetector; // slide
    private int flaggingWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        gestureDetector = new GestureDetector(new GuideViewTouch());

        // To obtain the resolution
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        flaggingWidth = dm.widthPixels / 3;

        imageResId = new int[] { R.drawable.bg4, R.drawable.bg10 };

        mImageViews = new ArrayList<View>();

        // Initialize the image resources
        LayoutInflater viewInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 0
        View convertView0 = viewInflater.inflate(R.layout.fragment_welcome, null);
        LinearLayout linearLayout0 = (LinearLayout) convertView0
                .findViewById(R.id.guide_item);
        linearLayout0.setBackgroundResource(imageResId[0]);
        mImageViews.add(linearLayout0);
        // 1
        View convertView1 = viewInflater.inflate(R.layout.fragment_welcome, null);
        LinearLayout linearLayout1 = (LinearLayout) convertView1
                .findViewById(R.id.guide_item);
        linearLayout1.setBackgroundResource(imageResId[1]);
        Button btn = (Button) convertView1.findViewById(R.id.start);
        btn.setVisibility(View.VISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                GoToLoginActivity();
            }
        });

        mImageViews.add(linearLayout1);

        viewPager = (ViewPager) findViewById(R.id.guide_view);
        viewPager.setAdapter(new MyAdapter());
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (gestureDetector.onTouchEvent(event)) {
            event.setAction(MotionEvent.ACTION_CANCEL);
        }
        return super.dispatchTouchEvent(event);
    }

    private class GuideViewTouch extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            if (currentItem == 1) {
                if (Math.abs(e1.getX() - e2.getX()) > Math.abs(e1.getY()
                        - e2.getY())
                        && (e1.getX() - e2.getX() <= (-flaggingWidth) || e1
                        .getX() - e2.getX() >= flaggingWidth)) {
                    if (e1.getX() - e2.getX() >= flaggingWidth) {
                        GoToLoginActivity();
                        return true;
                    }
                }
            }
            return false;
        }
    }

    //Enter login interface
    void GoToLoginActivity() {
        Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }


    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         * This method will be invoked when a new page becomes selected.
         * position: Position index of the new selected page.
         */
        public void onPageSelected(int position) {
            currentItem = position;
        }

        public void onPageScrollStateChanged(int arg0) {
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
    }


    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageResId.length;
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(mImageViews.get(arg1));
            return mImageViews.get(arg1);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            GoToLoginActivity();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

}
