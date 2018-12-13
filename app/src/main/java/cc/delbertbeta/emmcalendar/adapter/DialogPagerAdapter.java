package cc.delbertbeta.emmcalendar.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cc.delbertbeta.emmcalendar.controller.DialogDayFragment;
import cc.delbertbeta.emmcalendar.controller.DialogNightFragment;

public class DialogPagerAdapter extends FragmentPagerAdapter {

    private int day, night;

    public DialogPagerAdapter (FragmentManager fm, int day, int night) {
        super(fm);
        this.day = day;
        this.night = night;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return DialogDayFragment.newInstance(0, day);
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return DialogNightFragment.newInstance(1, night);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
