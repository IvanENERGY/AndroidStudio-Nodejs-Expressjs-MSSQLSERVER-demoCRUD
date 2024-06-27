package com.example.frontend_todoapp.webService;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateTaskResponse {
    public static class CreateTaskResponseData{
        @SerializedName("rowsAffected")
        private List<Integer> rowsAffected;
        public List<Integer> getRowsAffected() {
            return rowsAffected;
        }
    }
    @SerializedName("data")
    private CreateTaskResponseData data;

    public CreateTaskResponseData getData() {
        return data;
    }

}
