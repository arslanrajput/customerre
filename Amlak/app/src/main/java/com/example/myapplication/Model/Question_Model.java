package com.example.myapplication.Model;

import java.util.ArrayList;

public class Question_Model
{
    public String device_id;
    public String question;
    public String type;
    public String nameid;
    String form_id;
    public ArrayList<ValueModel> valueModelList;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNameid() {
        return nameid;
    }

    public void setNameid(String nameid) {
        this.nameid = nameid;
    }

    public ArrayList<ValueModel> getValueModelList() {
        return valueModelList;
    }

    public void setValueModelList(ArrayList<ValueModel> valueModelList) {
        this.valueModelList = valueModelList;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public Question_Model(String form_id, String device_id, String question, String type, String nameid, ArrayList<ValueModel> valueModelList)
    {
        this.form_id = form_id;
        this.device_id = device_id;
        this.question = question;
        this.type = type;
        this.nameid = nameid;
        this.valueModelList = valueModelList;
    }
}
