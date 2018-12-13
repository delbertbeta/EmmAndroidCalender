package cc.delbertbeta.emmcalendar.controller;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import cc.delbertbeta.emmcalendar.adapter.DialogPagerAdapter;
import cc.delbertbeta.emmcalendar.R;

public class RewardDialog extends DialogFragment {

    View view;

    private int day;
    private int night;

    public static RewardDialog newInstance(int day, int night) {
        RewardDialog frag = new RewardDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("day", day);
        bundle.putInt("night", night);
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            day = bundle.getInt("day");
            night = bundle.getInt("night");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog.getWindow();

        Display display = getActivity().getWindowManager().getDefaultDisplay();

        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);

        WindowManager.LayoutParams params = window.getAttributes();
        params.width = (int)Math.round(dm.widthPixels * 0.8);
        params.height = 1000;
        window.setAttributes(params);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setWindowAnimations(R.style.dialogWindowAnim);
        view = inflater.inflate(R.layout.reward_dialog, container);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager pager = view.findViewById(R.id.dialog_view_pager);
        pager.setAdapter(new DialogPagerAdapter(getChildFragmentManager(), day, night));
    }
}
