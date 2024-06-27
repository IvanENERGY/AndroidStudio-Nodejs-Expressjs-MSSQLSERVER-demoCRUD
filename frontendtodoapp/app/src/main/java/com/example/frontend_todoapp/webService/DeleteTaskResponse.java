package com.example.frontend_todoapp.webService;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeleteTaskResponse {
    public static class DeleteTaskResponseData{
        @SerializedName("rowsAffected")
        private List<Integer> rowsAffected;
        public List<Integer> getRowsAffected() {
            return rowsAffected;
        }
    }
    @SerializedName("data")
    private DeleteTaskResponse.DeleteTaskResponseData data;

    public DeleteTaskResponse.DeleteTaskResponseData getData() {
        return data;
    }
}
