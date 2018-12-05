package cc.delbertbeta.emmcalendar;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class TopPanelDialog extends DialogFragment {

    public interface OnUserChange {
        public void onUserChange();
    }

    public void setOnUserChange(OnUserChange onUserChange) {
        this.onUserChange = onUserChange;
    }

    OnUserChange onUserChange;

    View view;

    String user;

    public static TopPanelDialog newInstance(String user) {
        TopPanelDialog frag = new TopPanelDialog();
        Bundle bundle = new Bundle();
        bundle.putString("user", user);
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getString("user");
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
        params.gravity = Gravity.TOP;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        params.width = (int)Math.round(dm.widthPixels * 0.8);
//        params.height = 1000;
        window.setAttributes(params);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setWindowAnimations(R.style.panelWindowAnim);
        view = inflater.inflate(R.layout.fragment_top_panel, container);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
            }
        });
        view.findViewById(R.id.user_switch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserChange.onUserChange();
                getDialog().cancel();
            }

        });
    }
}