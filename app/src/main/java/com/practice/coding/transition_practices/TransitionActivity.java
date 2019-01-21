package com.practice.coding.transition_practices;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.AnticipateOvershootInterpolator;

public class TransitionActivity extends AppCompatActivity {

    private Constants.TransitionType enumAnimationType;
    private static String toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        //call init page function
        initPage();

        //call init animation function
        initAnimation();


        /* prevents the overlap of enter transition of TransitionActivity.java & Exist transition of MainActivity.java */
        getWindow().setAllowEnterTransitionOverlap(false); //by default its true.. OR // we can also set in styles.xml

    }

    /////////////////    Init Page Function   //////////////
    public void initPage() {
        //Received Intent Data
        Intent intent = getIntent();
        if (intent != null) {

            enumAnimationType = (Constants.TransitionType) intent.getSerializableExtra(Constants.KEY_ANIM_TYPE);
            toolbarTitle = intent.getStringExtra(Constants.KEY_TITLE);
        }
        getSupportActionBar().setTitle(toolbarTitle);
        getSupportActionBar().setSubtitle("By Alif");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //back arrow button
    }

    /////////////////////////     back button define in xml , back button onClick   //////////////////
    public void backButtonOnClick(View view) {
         /*finishAfterTransition(); The activity will be finished with the Reverse Enter Transition with which activity opened
        Comment this and see the effect
           *//*
        finishAfterTransition();*/

        finish();
    }

    /////////////////////////    Actionbar  back button onClick     //////////////////
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        /* un comment and see the effect */
        // finish();
        return true;
    }


    /////////////////////////   initAnimation Function    //////////////////
    private void initAnimation() {
        switch (enumAnimationType) {
            case EXPLODE_BY_JAVA: {
                /* explode transition moves the views in towards the center and views out from the center of the scene/activity */
                Explode enterTransition = new Explode();
                enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
                enterTransition.setInterpolator(new FastOutLinearInInterpolator()); /* comment and see what effect gone*/
                getWindow().setEnterTransition(enterTransition);
            }
            break;

            case EXPLODE_BY_XML: {
                /* explode transition moves the views in towards the center and views out from the center of the scene/activity */
                Transition enterTransition = TransitionInflater.from(this).inflateTransition(R.transition.explode_transition);
                getWindow().setEnterTransition(enterTransition);
            }
            break;

            case SLIDE_BY_JAVA:
            {
                /* Slide Transition Moves the views in or out from one of the Edges of the scene/activity */
                Slide enterTransition = new Slide();
                enterTransition.setSlideEdge(Gravity.TOP);
               /* enterTransition.setSlideEdge(Gravity.RIGHT);
                enterTransition.setSlideEdge(Gravity.BOTTOM);
                enterTransition.setSlideEdge(Gravity.LEFT);*/
                enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
                enterTransition.setInterpolator(new AnticipateOvershootInterpolator()); /* comment and see what effect gone*/
                getWindow().setEnterTransition(enterTransition);
            }
            break;

            case SLIDE_BY_XML:
            {
                /* Slide Transition Moves the views in or out from one of the EDGES of the scene/activity */
                Transition enterTransition = TransitionInflater.from(this).inflateTransition(R.transition.slide_by_xml);
                getWindow().setEnterTransition(enterTransition);
            }
            break;

            case FADE_BY_JAVA:
            {
                /* Fade transition add or removes the views from scene means activity by changing its opacity means fadeout the activity */
                Fade enterTransition = new Fade();
                enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
                enterTransition.setMode(Fade.MODE_IN);
                //enterTransition.setMode(Fade.MODE_OUT);
                getWindow().setEnterTransition(enterTransition);
            }
            break;

            case FADE_BY_XML:
            {
                /* Fade transition add or removes the views from scene means activity by changing its opacity means fadeout the activity */
                Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fade_by_xml);
                getWindow().setEnterTransition(transition);
            }
            break;
        }
    }

}
