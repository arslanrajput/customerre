package com.example.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Model.AnswerModel;
import com.example.myapplication.Model.Question_Model;
import com.example.myapplication.R;
import com.example.myapplication.Utility.Config;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.activity.ThankSscreen;
import com.vanniktech.emoji.EmojiTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder> {
    Context context;
    Question_Model question_model;
    private ArrayList<Question_Model> question_modelList;
    JSONObject obj = new JSONObject();
    ArrayList<AnswerModel> answerModelArrayList = new ArrayList<>();

    public QuestionAdapter(Context context, ArrayList<Question_Model> question_modelList) {
        this.context = context;
        this.question_modelList = question_modelList;
    }

    @NonNull
    @Override
    public QuestionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_lay, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        question_model = question_modelList.get(position);

        //binding the data with the viewholder views
        holder.question.setText(question_model.getQuestion());
        holder.opt.setText(question_model.getValueModelList().get(0).getValue());
        holder.txtNo.setText(question_model.getValueModelList().get(1).getValue());
        holder.txtYes.setText(question_model.getValueModelList().get(2).getValue());
        holder.noSureEmoji.setText(Html.fromHtml(question_model.getValueModelList().get(0).getLabel()));
        holder.noEmoji.setText(Html.fromHtml(question_model.getValueModelList().get(1).getLabel()));
        holder.yesEmoji.setText(Html.fromHtml(question_model.getValueModelList().get(2).getLabel()));//question_model.getValueModelList().get(2).getLabel()
        String qNumber = "Q" + (position + 1) + ":";
        holder.txtTitlevalue.setText(qNumber);

//        Picasso.with(context).load(question_model.getImage()).placeholder(R.drawable.ic_account_circle_black_24dp).into(holder.image);


/*        holder.imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();

                // check if item still exists
                if (pos != RecyclerView.NO_POSITION) {

                    Question_Model events = question_modelList.get(pos);


                    Toast.makeText(context, "Postion" + events, Toast.LENGTH_SHORT).show();

                }
            }
        });*/
        holder.firstLinear.setOnClickListener(v -> {
            try {
                //obj.put(question_model.getNameid(), question_model.getValueModelList().get(0).getValue());
                holder.frameAds.setEnabled(false);
                holder.firstLinear.setEnabled(false);
                holder.secondLinear.setEnabled(false);
                holder.thirdLinear.setEnabled(false);
                AnswerModel answerModel = new AnswerModel(question_modelList.get(position).getNameid(), question_modelList.get(position).getValueModelList().get(0).getValue());
                answerModelArrayList.add(answerModel);
                if (context instanceof MainActivity) {
                    Objects.requireNonNull(((MainActivity) context).mRecyclerView.getLayoutManager()).scrollToPosition(position + 1);
                }
                if (answerModelArrayList.size() == question_modelList.size()) {
                    if (context instanceof MainActivity) {
                        try {
                            for (int j = 0; j < answerModelArrayList.size(); j++) {
                                obj.put(answerModelArrayList.get(j).getValue(), answerModelArrayList.get(j).getName());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        answers(obj);
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        holder.secondLinear.setOnClickListener(v -> {
            try {
                //obj.put(question_model.getNameid(), question_model.getValueModelList().get(1).getValue());
                holder.frameAds.setEnabled(false);
                holder.firstLinear.setEnabled(false);
                holder.secondLinear.setEnabled(false);
                holder.thirdLinear.setEnabled(false);
                AnswerModel answerModel = new AnswerModel(question_modelList.get(position).getNameid(), question_modelList.get(position).getValueModelList().get(1).getValue());
                answerModelArrayList.add(answerModel);
                if (context instanceof MainActivity) {
                    Objects.requireNonNull(((MainActivity) context).mRecyclerView.getLayoutManager()).scrollToPosition(position + 1);
                }
                if (answerModelArrayList.size() == question_modelList.size()) {
                    try {
                        for (int j = 0; j < answerModelArrayList.size(); j++) {
                            obj.put(answerModelArrayList.get(j).getValue(), answerModelArrayList.get(j).getName());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    answers(obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        holder.thirdLinear.setOnClickListener(v -> {
            try {
                //obj.put(question_model.getNameid(), question_model.getValueModelList().get(2).getValue());
                holder.frameAds.setEnabled(false);
                holder.firstLinear.setEnabled(false);
                holder.secondLinear.setEnabled(false);
                holder.thirdLinear.setEnabled(false);
                AnswerModel answerModel = new AnswerModel(question_modelList.get(position).getNameid(), question_modelList.get(position).getValueModelList().get(2).getValue());
                answerModelArrayList.add(answerModel);
                if (context instanceof MainActivity) {
                    Objects.requireNonNull(((MainActivity) context).mRecyclerView.getLayoutManager()).scrollToPosition(position + 1);
                }
                if (answerModelArrayList.size() == question_modelList.size()) {
                    try {
                        for (int j = 0; j < answerModelArrayList.size(); j++) {
                            obj.put(answerModelArrayList.get(j).getValue(), answerModelArrayList.get(j).getName());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    answers(obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public int getItemCount() {
        return question_modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView question, txtTitlevalue;
        public TextView opt, txtNo, txtYes;
        public EmojiTextView noSureEmoji, noEmoji, yesEmoji;
        public ImageView imagebutton;
        public LinearLayout frameAds, firstLinear, secondLinear, thirdLinear;

        public MyViewHolder(@NonNull View v) {
            super(v);
            question = v.findViewById(R.id.title);
            opt = v.findViewById(R.id.txt_nosure);
            txtNo = v.findViewById(R.id.txt_no);
            txtYes = v.findViewById(R.id.txt_yes);
            noSureEmoji = v.findViewById(R.id.emojiTextView);
            noEmoji = v.findViewById(R.id.emojiTextViewww);
            yesEmoji = v.findViewById(R.id.emojiTextVieww);
            imagebutton = v.findViewById(R.id.btn_ansrone);
            txtTitlevalue = v.findViewById(R.id.txtTitlevalue);
            frameAds = v.findViewById(R.id.frameAds);
            firstLinear = v.findViewById(R.id.first_linear);
            secondLinear = v.findViewById(R.id.second_linear);
            thirdLinear = v.findViewById(R.id.third_linear);
        }
    }

    private void answers(JSONObject jsonObject) {
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest myReq = new StringRequest(Request.Method.POST,
                Config.ANSWER, new com.android.volley.Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {


                Log.e("myTag:", "" + response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    String success = jObj.getString("status");
                    if (success.equals("true")) {
                        String message = jObj.getString("data");
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, ThankSscreen.class);
                        context.startActivity(intent);
                        ((Activity)context).finish();
                        //((Activity) context).finish();
                    } else {

                        Toast.makeText(context, "Something went wrong!", Toast.LENGTH_LONG).show();


                    }


                } catch (JSONException e) {

                    Toast.makeText(context, "Server is not responding at that time, Please waiat and refresh your page again.", Toast.LENGTH_LONG).show();

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
                params.put("device_id", question_modelList.get(0).getDevice_id());
                params.put("form_id", question_modelList.get(0).getForm_id());
                params.put("answers", jsonObject.toString());

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