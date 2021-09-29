package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.QuestionAdapter;
import com.example.myapplication.Model.Question_Model;
import com.example.myapplication.Model.ValueModel;
import com.example.myapplication.R;
import com.example.myapplication.Utility.Config;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.EmojiTextView;
import com.vanniktech.emoji.twitter.TwitterEmojiProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private ImageView btn_ansr;
    public EmojiTextView emojiTextView;

    public Context context = MainActivity.this;
    public String deviceId, android_id;

    ArrayList<Question_Model> question_models = new ArrayList<>();

    QuestionAdapter questionAdapter;
    public RecyclerView mRecyclerView;


    @SuppressLint({"WrongViewCast", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EmojiManager.install(new TwitterEmojiProvider());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

        SharedPreferences preef = getApplicationContext().getSharedPreferences("MyPreef", MODE_PRIVATE);
        deviceId = preef.getString("device_id", android_id);
        Toast.makeText(context, "android_id= " + android_id, Toast.LENGTH_LONG).show();

        //    emojiTextView = (EmojiTextView) findViewById(R.id.emojiTextView);

//        String emoji="\uD83D\uDD25";
//        emojiTextView.setText(emoji);


//        btn_ansr = (ImageView) findViewById(R.id.btn_ansrone);
//
//        btn_ansr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Answer Received", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(MainActivity.this, Questiontwo.class);
//                startActivity(i);
//                finish();
//            }
//        });
        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setOnTouchListener((v, event) -> true);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        questionAdapter = new QuestionAdapter(MainActivity.this, question_models);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(questionAdapter);

        // use a linear layout manager
        /*mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);*/

        Questions();

    }

    private void Questions() {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        StringRequest myReq = new StringRequest(Request.Method.POST,
                Config.QUES, new com.android.volley.Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {


                Log.e("myTag:", "" + response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    String success = jObj.getString("staus");
                    if (success.equals("true")) {
                        //      Toast.makeText(ActivityHadithByCat.this, "" + response, Toast.LENGTH_LONG).show();
                        JSONArray jsonArray = jObj.getJSONArray("questions");

                        for (int n = 0; n < jsonArray.length(); n++) {

                            JSONObject resultsObj = jsonArray.getJSONObject(n);
                            ArrayList<ValueModel> valueModelList = new ArrayList<>();
                            JSONArray valueArray = resultsObj.getJSONArray("values");
                            for (int i = 0; i < valueArray.length(); i++) {
                                JSONObject valueObj = valueArray.getJSONObject(i);
                                ValueModel valueModel = new ValueModel(valueObj.getString("label"), valueObj.getString("value"));
                                valueModelList.add(valueModel);
                            }
                            question_models.add(new Question_Model(jObj.getString("form_id"), jObj.getString("device_id"), resultsObj.getString("question"), resultsObj.getString("type"), resultsObj.getString("name"), valueModelList));

                        }
                        questionAdapter = new QuestionAdapter(MainActivity.this, question_models);
                        mRecyclerView.setAdapter(questionAdapter);
                        questionAdapter.notifyDataSetChanged();
                    } else {

                        Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_LONG).show();


                    }


                } catch (JSONException e) {

                    Toast.makeText(MainActivity.this, "Server is not responding at that time, Please waiat and refresh your page again.", Toast.LENGTH_LONG).show();

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
                params.put("device_id", "3");

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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity.this, AppIntroo.class);
        startActivity(intent);
        finish();
    }

}