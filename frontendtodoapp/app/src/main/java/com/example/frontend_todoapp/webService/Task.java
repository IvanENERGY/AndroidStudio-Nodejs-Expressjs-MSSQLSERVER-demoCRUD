package com.example.frontend_todoapp.webService;

import com.google.gson.annotations.SerializedName;

public class Task {

    @SerializedName("id")
    private String id;
    @SerializedName("taskName")
    private String taskName;
    @SerializedName("deadline")
    private String deadline;
    @SerializedName("reps")
    private int reps;
    @SerializedName("filePath")
    private String filePath;

    public Task(String taskName, String deadline, String reps, String filePath) {
        this.taskName = taskName;
        this.deadline = deadline;
        this.reps = Integer.parseInt(reps);
        this.filePath = filePath;
    }
    public String getId() {
        return id;
    }
    public String getTaskName() {
        return taskName;
    }
    public String getDeadline() {
        return deadline;
    }
    public int getReps() {
        return reps;
    }
    public String getFilePath() {
        return filePath;
    }

}
