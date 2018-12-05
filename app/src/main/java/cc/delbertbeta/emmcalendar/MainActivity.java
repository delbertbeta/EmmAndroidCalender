package cc.delbertbeta.emmcalendar;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private int themeId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (themeId == -1) {
            themeId = R.style.AppTheme;
        }
        setTheme(themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void homeClick(View v) {
//        RewardDialog rewardDialog = new RewardDialog();
//        rewardDialog.show(getSupportFragmentManager(), "notitile");
        FragmentManager manager = getSupportFragmentManager();
        RewardDialog dialog = RewardDialog.newInstance();
        dialog.show(manager, "fragment_reward_dialog");
    }

    public void faceClick(View v) {
        if (themeId == R.style.AppTheme) {
            themeId = R.style.AppThemeGirl;
        } else {
            themeId = R.style.AppTheme;
        }
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
        setTheme(themeId);

        setContentView(R.layout.activity_main);
    }
}
