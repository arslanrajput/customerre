package com.example.myapplication.activity;

import static android.view.View.GONE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Model.Question_Model;
import com.example.myapplication.R;
import com.example.myapplication.Utility.Config;
import com.example.myapplication.Utility.Tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class AppIntroo extends AppCompatActivity {



    public Context context = AppIntroo.this;
    public String deviceId, android_id;

    public ArrayList<Question_Model> question_modelList = new ArrayList<>();




    private static final int MAX_STEP = 6;

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private Button btn_next,btn_prev,btn_fed;
    private String title_array[] = {
            "Ready to Travel",
            "Pick the Ticket",
            "Flight to Destination",
            "Enjoy Holiday"
    };
    private String description_array[] = {
            "Choose your destination, plan Your trip. Pick the best place for Your holiday",
            "Select the day, pick Your ticket. We give you the best prices. We guarantee!",
            "Safe and Comfort flight is our priority. Professional crew and services.",
            "Enjoy your holiday, Dont forget to feel the moment and take a photo!",
    };
    private int about_images_array[] = {
            R.drawable.adverone,
            R.drawable.advertwo,
            R.drawable.advertthr,
            R.drawable.advertfour,
            R.drawable.advertfiv,
            R.drawable.advertsix
    };
    private int color_array[] = {
            R.color.md_red_700,
            R.color.md_blue_400,
            R.color.purple_200,
            R.color.md_deep_orange_200
    };
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_introo);
        android_id = Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID);

        SharedPreferences preef = getApplicationContext().getSharedPreferences("MyPreef", MODE_PRIVATE);
        deviceId= preef.getString("device_id",android_id);
        Toast.makeText(context, "android_id= " + android_id, Toast.LENGTH_LONG).show();


        initComponent();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                viewPager.post(new Runnable(){

                    @Override
                    public void run() {
                        viewPager.setCurrentItem((viewPager.getCurrentItem()+1)% about_images_array.length);
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 3000, 3000);
        Tools.setSystemBarTransparent(this);

    }
    private void initComponent() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_prev = (Button) findViewById(R.id.btn_pre);
        btn_fed = (Button) findViewById(R.id.btn_febback);
        // adding bottom dots
        bottomProgressDots(0);

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

      //  btn_got_it.setVisibility(View.GONE);
        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPager.getCurrentItem() - 1;
                if (current < MAX_STEP) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    finish();
                }
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPager.getCurrentItem() + 1;
                if (current < MAX_STEP) {
                    // move to next screen
                    Intent i = new Intent(AppIntroo.this, MainActivity.class);
                    startActivity(i);
                    finish();
                    viewPager.setCurrentItem(current);

                } else {
                    finish();
                }
            }
        });
        btn_fed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handlerr = new Handler();
                handlerr.postDelayed(new Runnable() {
                    public void run()
                    {
//                        Intent i = new Intent(AppIntroo.this, MainActivity.class);
//                        startActivity(i);
//                        finish();
                        feebback();

                    }
                }, 2000);

            }
        });

//        ((Button) findViewById(R.id.btn_skip)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

    private void bottomProgressDots(int current_index) {
        LinearLayout dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        ImageView[] dots = new ImageView[MAX_STEP];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(getResources().getColor(R.color.dark_grey), PorterDuff.Mode.SRC_IN);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current_index].setImageResource(R.drawable.shape_circle);
            dots[current_index].setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        }
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(final int position) {
            bottomProgressDots(position);
//            if (position == about_images_array.length - 1) {
//
//               // btn_got_it.setVisibility(View.VISIBLE);
//            } else {
//              //  btn_got_it.setVisibility(View.GONE);
//            }
//            if (viewPager.getCurrentItem() == about_images_array.length - 1)
//            {
////                btn_got_iit.setText("Get Started");
//                Intent i = new Intent(AppIntroo.this, AppIntroo.class);
//                startActivity(i);
//                finish();
//            } else {
//                //btnNext.setText("Next");
//            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.item_stepper_wizard_color, container, false);
            //((TextView) view.findViewById(R.id.title)).setText(title_array[position]);
        //    ((TextView) view.findViewById(R.id.description)).setText(description_array[position]);
            ((ImageView) view.findViewById(R.id.image)).setImageResource(about_images_array[position]);
          //  ((RelativeLayout) view.findViewById(R.id.lyt_parent)).setBackgroundColor(getResources().getColor(color_array[position]));
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return about_images_array.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
    @Override
    protected void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

    private void feebback()
    {

        final ProgressDialog mMailDialog = new ProgressDialog(this);
        mMailDialog.setMessage("loading");
        mMailDialog.show();

//        StringRequest myReq = new StringRequest(Request.Method.POST,
//                Config.DRIVHIST, new com.android.volley.Response.Listener<String>() {

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(AppIntroo.this);

        StringRequest myReq = new StringRequest(Request.Method.POST,
                Config.QUES, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                  mMailDialog.dismiss();
                Log.e("myTag:", "" + response);
                try
                {
                    JSONObject jObj = new JSONObject(response);
                    String success = jObj.getString("status");
                    if (success.equals( "true"))
                    {
                    /*    JSONArray jsonArray = jObj.getJSONArray("data");

                        for (int n = 0; n < jsonArray.length(); n++)
                        {
                            JSONObject resultsObj = jsonArray.getJSONObject(n);
                            //question_modelList.add(new Question_Model(resultsObj.getString("device_id"),resultsObj.getString("question"),resultsObj.getString("type"),resultsObj.getString("name"),resultsObj.getString("value")));

                        }*/

                    }
                    else
                    {
                        Toast.makeText(AppIntroo.this, "Something went wrong!", Toast.LENGTH_LONG).show();


                    }


                }

                catch (JSONException e)
                {

//                    if (sweetProgressDialog.isShowing()) {
//                        sweetProgressDialog.dismiss();
//                    }
//                    Log.e("myTag", e.toString());
                }
            }

        },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("myTag", "error response" + error.toString());
//                        if (sweetProgressDialog.isShowing()) {
//                            sweetProgressDialog.dismiss();
//                        }
                    }
                }) {


            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("device_id","2");

                return params;
            }
        };

        myReq.setRetryPolicy(new DefaultRetryPolicy(
                20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        myReq.setShouldCache(false);
        queue.add(myReq);
//        if (!sweetProgressDialog.isShowing()) {
//            sweetProgressDialog.show();
//        }

    }


}