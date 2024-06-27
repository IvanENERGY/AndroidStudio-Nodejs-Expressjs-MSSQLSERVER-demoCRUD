package com.example.frontend_todoapp.webService;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllTaskResponse {

    @SerializedName("data")
    private List<Task> data;

    public List<Task> getData() {
        return data;
    }


}
