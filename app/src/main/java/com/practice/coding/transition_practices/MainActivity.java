package com.practice.coding.transition_practices;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*
    Topics Covered
        1 --> Ripple Effects
        2 --> Circular Reveal Animation
        3 --> Shared Element Transition
        4 --> Slide Transition By Java AND XML Code
        5 --> Explode Transition By Java AND XML Code
        6 --> Fade Transition By Java AND XML Code
     */

    private ImageView img_logo, img_user;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //must be before oncreate and setContentView
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_logo = findViewById(R.id.imgLogo);
        img_user = findViewById(R.id.img_user);
        tvName = findViewById(R.id.tvCompanyName);

        //set window re-enter and existing transition
        setWindowReEnterAndExitTransition();


    }

    private void setWindowReEnterAndExitTransition()
    {
        //Re-enter transition executed when returning back to this activity
        Slide transition = new Slide();
        transition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        transition.setSlideEdge(Gravity.LEFT); //use start if using left to right locale

        getWindow().setReenterTransition(transition); //when we come back to main activity from another activity

        getWindow().setExitTransition(transition); //when exist main activity and enter to another activity

        /*
         Prevents the overlap of re-entering Activity MainActivity.java and Existing activity TransitionActivity.java
        */
        getWindow().setAllowReturnTransitionOverlap(false); //by default its true.. OR // we can also set in styles.xml

    }

    public void moveToRippleActivity(View view) {
        startActivity(new Intent(this, RippleEffects.class));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void sharedElementTransitionOnClick(View view) {
        Pair[] pair  = new Pair[3];
        pair[0] = new Pair<View, String>(img_logo, getString(R.string.logo_shared));
        pair[1] = new Pair<View, String>(tvName, getString(R.string.company_name));
        pair[2] = new Pair<View, String>(img_user, getString(R.string.user_image_shared));

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pair);
        Intent intent = new Intent(this, SharedElementActivity.class);
        startActivity(intent, options.toBundle());
    }

    public void explodeTransitionByJavaCode(View view) {

        //must enable the window content trasition FEATURE_CONTENT_TRANSITIONS in java code or style.xmll

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(this, TransitionActivity.class);
        intent.putExtra(Constants.KEY_ANIM_TYPE, Constants.TransitionType.EXPLODE_BY_JAVA);
        intent.putExtra(Constants.KEY_TITLE, "Explode by Java");
        startActivity(intent, options.toBundle());
    }

    public void explodeTransitionByXML(View view) {

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(this, TransitionActivity.class);
        intent.putExtra(Constants.KEY_ANIM_TYPE, Constants.TransitionType.EXPLODE_BY_XML);
        intent.putExtra(Constants.KEY_TITLE, "Explode by XML");
        startActivity(intent, options.toBundle());
    }

    public void slideTransitionByJavaCode(View view) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(this, TransitionActivity.class);
        intent.putExtra(Constants.KEY_ANIM_TYPE, Constants.TransitionType.EXPLODE_BY_XML);
        intent.putExtra(Constants.KEY_TITLE, "Slide by Java");
        startActivity(intent, options.toBundle());
    }

    public void slideTransitionByXML(View view) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(this, TransitionActivity.class);
        intent.putExtra(Constants.KEY_ANIM_TYPE, Constants.TransitionType.EXPLODE_BY_XML);
        intent.putExtra(Constants.KEY_TITLE, "Slide by XML");
        startActivity(intent, options.toBundle());
    }

    public void fadeTransitionByJava(View view) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(this, TransitionActivity.class);
        intent.putExtra(Constants.KEY_ANIM_TYPE, Constants.TransitionType.FADE_BY_JAVA);
        intent.putExtra(Constants.KEY_TITLE, "Fade by Java");
        startActivity(intent, options.toBundle());
    }

    public void fadeTransitionByXML(View view) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(this, TransitionActivity.class);
        intent.putExtra(Constants.KEY_ANIM_TYPE, Constants.TransitionType.FADE_BY_XML);
        intent.putExtra(Constants.KEY_TITLE, "Fade by XML");
        startActivity(intent, options.toBundle());
    }


}
