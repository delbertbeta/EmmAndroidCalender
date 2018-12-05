package cc.delbertbeta.emmcalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class RewardDialog extends DialogFragment {

    View view;

    public static RewardDialog newInstance() {
        RewardDialog frag = new RewardDialog();
        return frag;
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
//        params.gravity = Gravity.CENTER;
        params.width = (int)Math.round(dm.widthPixels * 0.8);
        params.height = 1000;
        window.setAttributes(params);
    }
//
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
////        view = LayoutInflater.from(getActivity()).inflate(R.layout.reward_dialog, null);
//        FragmentManager manager = getFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.dialog_frame_layout, new cc.delbertbeta.emmcalendar.DialogFragment());
//        transaction.commit();
//        builder.setView(view);
//        return builder.create();
//    }

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
        pager.setAdapter(new DialogPagerAdapter(getChildFragmentManager()));
    }
}
