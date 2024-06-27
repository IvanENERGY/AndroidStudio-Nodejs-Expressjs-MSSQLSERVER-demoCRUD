package com.example.frontend_todoapp.Act;

import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frontend_todoapp.R;
import com.example.frontend_todoapp.Adapter.TaskListArrayAdapter;
import com.example.frontend_todoapp.webService.CreateTaskResponse;
import com.example.frontend_todoapp.webService.GetAllTaskResponse;
import com.example.frontend_todoapp.webService.Methods;
import com.example.frontend_todoapp.webService.RetrofitClient;
import com.example.frontend_todoapp.webService.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.example.frontend_todoapp.TodoAppStatic.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    /******************
     * This frontend application use the backend nodeJS application which utilize .query [PLAIN SQL instead of stored proc.] for connecting MSSQL server
     * ********************/
    private Context context;
    private ListView lvTasks;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        methods = RetrofitClient.getRetrofitInstance().create(Methods.class); //retrofit create the implementation of methods
        context=this;
        lvTasks=findViewById(R.id.lvTasks);
        btnAdd=findViewById(R.id.btnAdd);
        /*******************************
         * CALL API & Populate ListView
         * ************************/
        getListMapTask();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View customLayout=getLayoutInflater().inflate(R.layout.view_alertdialog_task,null);
                AlertDialog.Builder builder=new AlertDialog.Builder(context);

                TextView dialogTitle = new TextView(context);
                dialogTitle.setText("Add a new Task (alertDialogTitle)");
                dialogTitle.setTextSize(40);

                AlertDialog dialog=builder.setCustomTitle(dialogTitle)
                                            .setView(customLayout)
                                            .setPositiveButton("OK", null)
                                            .create();
                dialog.show();
                //check empty field
                dialog.getButton(dialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText etTaskName=dialog.findViewById(R.id.etTaskName);
                        EditText etDeadline=dialog.findViewById(R.id.etDeadline);
                        EditText etReps=dialog.findViewById(R.id.etReps);
                        EditText etFilePath=dialog.findViewById(R.id.etFilePath);

                        String newTaskName=etTaskName.getText().toString();
                        String newDeadline=etDeadline.getText().toString();
                        String newReps=etReps.getText().toString();
                        String newFilePath=etFilePath.getText().toString();

                        if(newTaskName.equals("")
                                ||newDeadline.equals("")
                                ||newReps.equals("")
                                ||newFilePath.equals("")){
                            Toast.makeText(context, "empty", Toast.LENGTH_SHORT).show();
                        }else{
                            Task newTask= new Task(newTaskName,newDeadline,newReps,newFilePath);
                            /*******************************
                             * CALL API & CREATE TASK
                             * ************************/
                            createTask(newTask);
                            dialog.dismiss(); //dismissing create dialog
                        }
                    }
                });
            }
        });
//        /*********Sample listMapTask for using TaskListArrayAdapter Start********/
//        ArrayList<Map<String, Object>> lmSampleTasks=new ArrayList<>();
//
//        Map<String,Object> mSampleTask=new HashMap<String,Object>();
//        mSampleTask.put("taskName","fyp");
//        mSampleTask.put("deadline","2021-12-12");
//        mSampleTask.put("reps",3);
//        mSampleTask.put("filePath","some.png");
//
//        lmSampleTasks.add(mSampleTask);
//        /*********Sample listMapTask for using TaskListArrayAdapter  End********/
    }

    private void createTask(Task newTask) {
        //POST
        Call<CreateTaskResponse> call = methods.createTask(newTask);
        call.enqueue(new Callback<CreateTaskResponse>() {
            @Override
            public void onResponse(Call<CreateTaskResponse> call, Response<CreateTaskResponse> response) {
                if(response.isSuccessful()){
                    CreateTaskResponse createTaskResponse=response.body();
                    assert createTaskResponse != null;
                    CreateTaskResponse.CreateTaskResponseData data=createTaskResponse.getData();
                    List<Integer> liRowsAffected=data.getRowsAffected();
                    showInfoMsgDialogOK(context, "Create Response: Rows affected is " + liRowsAffected.toString(), null);
                    getListMapTask();
                }
                else{
                    showErrorMsgDialogOK(context,response.code()+": Some error happened when adding task");
                }
            }

            @Override
            public void onFailure(Call<CreateTaskResponse> call, Throwable t) {
                showErrorMsgDialogOK(context,t.getMessage());
            }
        });
    }


    public void getListMapTask(){ //public so that update_button in Adapter can call after update
    //READ
        Call<GetAllTaskResponse> call= methods.getAllTask()  ;
        call.enqueue(new Callback<GetAllTaskResponse>() {//execute the call and get the response;network op. need to be run in background thread
            @Override
            public void onResponse(Call<GetAllTaskResponse> call, Response<GetAllTaskResponse> response) {

                if (response.isSuccessful()) {
                    GetAllTaskResponse getAllTaskResponse=response.body();
                    assert getAllTaskResponse != null;
                    List<Task> lTasks=getAllTaskResponse.getData();
                    lmTasks=convertListTasksToListMapTask(lTasks);
                    TaskListArrayAdapter taskListArrayAdapter=new TaskListArrayAdapter(context,R.layout.view_lv_cell,lmTasks);
                    lvTasks.setAdapter(taskListArrayAdapter);

                } else {
                    Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<GetAllTaskResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<Map<String, Object>> convertListTasksToListMapTask(List<Task> lTasks) {
        List<Map<String, Object>> result=new ArrayList<>();
        for(Task task:lTasks){
            Map<String,Object> mTask=new HashMap<>();
            mTask.put("id",task.getId());
            mTask.put("taskName",task.getTaskName());
            mTask.put("deadline",task.getDeadline());
            mTask.put("reps",task.getReps());
            mTask.put("filePath",task.getFilePath());
            result.add(mTask);
        }
        return result;
    }
}