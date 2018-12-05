package cc.delbertbeta.emmcalendar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class DialogPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;

    public DialogPagerAdapter (FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return DialogDayFragment.newInstance(0);
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return DialogNightFragment.newInstance(1);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
