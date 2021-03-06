package cc.delbertbeta.emmcalendar.controller;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cc.delbertbeta.emmcalendar.R;
import cc.delbertbeta.emmcalendar.controller.RewardDialog;
import cc.delbertbeta.emmcalendar.controller.TopPanelDialog;
import cc.delbertbeta.emmcalendar.model.Reward;

public class MainActivity extends AppCompatActivity {

    private int themeId = -1;
    private List<ImageButton> imageButtons = new ArrayList<ImageButton>();
    private List<TextView> textViews = new ArrayList<TextView>();
    private List<ImageView> stars = new ArrayList<ImageView>();

    private List<Reward> rewards = new ArrayList<Reward>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (themeId == -1) {
            themeId = R.style.AppTheme;
        }
        setTheme(themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findAllReward();
    }

    private void initReward() {
        rewards.add(new Reward(1, 2, 4));
        rewards.add(new Reward(5, 4, 12));
        rewards.add(new Reward(18, 23, 11));
        int total = 0;
        for (final Reward reward : rewards) {
            textViews.get(reward.getIndex()).setVisibility(View.VISIBLE);
            textViews.get(reward.getIndex()).setText(String.valueOf(reward.getDay() + reward.getNight()));
            stars.get(reward.getIndex()).setVisibility(View.VISIBLE);
            imageButtons.get(reward.getIndex()).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager manager = getSupportFragmentManager();
                    RewardDialog dialog = RewardDialog.newInstance(reward.getDay(), reward.getNight());
                    dialog.show(manager, "fragment_reward_dialog");
                }
            });
            total += reward.getDay() + reward.getNight();
        }
        ((TextView)findViewById(R.id.textView2)).setText(String.valueOf(total));
    }

    private void findAllReward() {
        stars.clear();
        textViews.clear();
        imageButtons.clear();
        LinearLayout root = findViewById(R.id.content_linear_layout);
        assert root != null;
        for (int i = 1; i < root.getChildCount(); i++) {
            LinearLayout subRoot = (LinearLayout) root.getChildAt(i);
            for (int j = 0; j < subRoot.getChildCount(); j++) {
                RelativeLayout relativeLayout = (RelativeLayout) subRoot.getChildAt(j);
                stars.add((ImageView)relativeLayout.getChildAt(1));
                textViews.add((TextView)relativeLayout.getChildAt(2));
                imageButtons.add((ImageButton)relativeLayout.getChildAt(0));
            }
        }
        initReward();
    }

    public void homeClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        TopPanelDialog dialog = TopPanelDialog.newInstance(
                Integer.valueOf(((TextView)findViewById(R.id.textView2)).getText().toString()),
                rewards.get(rewards.size() - 2).getSum(),
                rewards.get(rewards.size() - 1).getSum());
        dialog.setOnUserChange(new TopPanelDialog.OnUserChange() {
            @Override
            public void onUserChange() {
                Window window = getWindow();
                if (themeId == R.style.AppTheme) {
                    themeId = R.style.AppThemeGirl;
                    window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPink));
                } else {
                    themeId = R.style.AppTheme;
                    window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                }
                setTheme(themeId);
                setContentView(R.layout.activity_main);
                findAllReward();
            }
        });
        dialog.show(manager, "fragment_top_panel");
    }
}
