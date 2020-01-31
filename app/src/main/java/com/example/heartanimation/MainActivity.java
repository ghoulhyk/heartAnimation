package com.example.heartanimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    SpringAnimation xAnimation , yAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.rLayout);

        rlayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        final ImageView imageView = new ImageView(getApplicationContext());
                        imageView.setImageResource(R.drawable.heart);
                        Random rnd = new Random();


                        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        imageView.setX(event.getX());
                        imageView.setY(event.getY());
                        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                        imageView.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
                        rlayout.addView(imageView , params);
                        startSpringAnimation(imageView);


                        new CountDownTimer(30000, 1000) {

                            public void onTick(long millisUntilFinished) {
                                imageView.setAlpha((float) (imageView.getAlpha() - 0.2));
                            }

                            public void onFinish() {
                                imageView.setVisibility(View.GONE);
                            }
                        }.start();




                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:



                        break;
                    default:
                        break;
                }

                return false;

            }
        });


    }

    private void startSpringAnimation(View view){
        SpringAnimation animation = new SpringAnimation(view, SpringAnimation.Y);
        SpringForce spring = new SpringForce();
        spring.setFinalPosition(-100f);
        spring.setStiffness(SpringForce.STIFFNESS_MEDIUM);
        spring.setDampingRatio(48f);
        animation.setSpring(spring);
        animation.start();
    }
}
