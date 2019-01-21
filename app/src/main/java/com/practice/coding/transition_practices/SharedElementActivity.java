package com.practice.coding.transition_practices;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SharedElementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element);

        getSupportActionBar().setTitle("Shared Element Transition");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }



    public void onExit(View view) {
        onBackPressed();
    }

    public void circularRevealAnimation(View view) {

    }

    public void makeCircularRevealAnimation(View view) {
        final TextView tvDescription = findViewById(R.id.descCircularRevealAnimation);

        int centerX = (view.getLeft() + view.getRight()) / 2;
        int centerY = (view.getTop() + view.getBottom()) / 2;

        float radius = Math.max(tvDescription.getWidth() , tvDescription.getHeight()) * 2.0f;
        if(tvDescription.getVisibility() == View.INVISIBLE)
        {
            tvDescription.setVisibility(View.VISIBLE);
            tvDescription.setText(getString(R.string.description));

            ViewAnimationUtils.createCircularReveal(tvDescription, centerX, centerY, 0, radius).start();
        }else
        {
            Animator reverseCircularRevealAnim = ViewAnimationUtils.createCircularReveal(tvDescription, centerX, centerY, radius, 0);

            reverseCircularRevealAnim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    tvDescription.setVisibility(View.INVISIBLE);
                }
            });
            reverseCircularRevealAnim.start();
        }
    }

    public void layoutOnClickRevealAnim(View view) {

        makeCircularRevealAnimation(view);
    }
    ///////////////////////////////

    @Override
    public boolean onSupportNavigateUp() {
        /*finishAfterTransition(); The activity will be finished with the Reverse Enter Transition with which activity opened*/
        finishAfterTransition();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {

        /*finishAfterTransition(); The activity will be finished with the Reverse Enter Transition with which activity opened
        Comment this and see the effect
           */
        finishAfterTransition();

        //super.onBackPressed();
    }
}
