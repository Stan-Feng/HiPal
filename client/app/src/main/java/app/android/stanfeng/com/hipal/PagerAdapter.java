// Create by Anan Wang
package app.android.stanfeng.com.hipal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    private MainActivityFragment tab1;
    private CreatePlan tab2;
    private ShareFragment tab3;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        tab1 = new MainActivityFragment();
        tab2 = new CreatePlan();
        tab3 = new ShareFragment();
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
//                MainActivityFragment tab1 = new MainActivityFragment();
                return tab1;
            case 1:
//                CreatePlan tab2 = new CreatePlan();
                return tab2;
            case 2:
//                ShareFragment tab3 = new ShareFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
