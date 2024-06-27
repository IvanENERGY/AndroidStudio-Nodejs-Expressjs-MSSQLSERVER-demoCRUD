package com.example.frontend_todoapp.webService;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateTaskResponse {
    public static class UpdateTaskResponseData{
        @SerializedName("rowsAffected")
        private List<Integer> rowsAffected;
        public List<Integer> getRowsAffected() {
            return rowsAffected;
        }
    }
    @SerializedName("data")
    private UpdateTaskResponse.UpdateTaskResponseData data;

    public UpdateTaskResponse.UpdateTaskResponseData getData() {
        return data;
    }

}
