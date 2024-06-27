package com.example.frontend_todoapp.Adapter;

import static com.example.frontend_todoapp.TodoAppStatic.*;


import android.app.Activity;
import android.content.Context;


import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.frontend_todoapp.Act.MainActivity;
import com.example.frontend_todoapp.R;
import com.example.frontend_todoapp.webService.CreateTaskResponse;
import com.example.frontend_todoapp.webService.DeleteTaskResponse;
import com.example.frontend_todoapp.webService.Task;
import com.example.frontend_todoapp.webService.UpdateTaskResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TaskListArrayAdapter extends ArrayAdapter  {
    private Context context;
    private int iLayoutSingleItem;
    private List<Map<String, Object>> lmTask = new ArrayList<>();
    public TaskListArrayAdapter(Context context, int piLayoutSingleItem, List<Map<String,Object>> plmTasks) {
        super(context, piLayoutSingleItem, plmTasks);
        this.context=context;
        this.iLayoutSingleItem=piLayoutSingleItem;
        this.lmTask=plmTasks;
    }
    @NonNull
    @Override
    public View getView(int position,  View convertView, @NonNull ViewGroup parent) {
        View curItemView=convertView;
        Map<String ,Object> mTask = lmTask.get(position);

        if(curItemView==null){
            curItemView= LayoutInflater.from(getContext()).inflate(R.layout.view_lv_cell,parent,false);
        }
        TextView tvTaskName=curItemView.findViewById(R.id.tvTaskName);
        TextView tvDeadline=curItemView.findViewById(R.id.tvDeadline);
        TextView tvReps=curItemView.findViewById(R.id.tvReps);
        TextView tvFilePath=curItemView.findViewById(R.id.tvFilePath);
        Button btnUp=curItemView.findViewById(R.id.btnUpdate);
        Button btnDel=curItemView.findViewById(R.id.btnDelete);


        tvTaskName.setText(mTask.get("taskName")==null?"":mTask.get("taskName").toString());
        tvDeadline.setText(mTask.get("deadline")==null?"":mTask.get("deadline").toString());
        tvReps.setText(mTask.get("reps")==null?"":mTask.get("reps").toString());
        tvFilePath.setText(mTask.get("filePath")==null?"":mTask.get("filePath").toString());

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "u pressed btnUp on position "+position, Toast.LENGTH_SHORT).show();

                final View customLayout=((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_alertdialog_task,null);
                AlertDialog.Builder builder=new AlertDialog.Builder(context);

                TextView dialogTitle = new TextView(context);
                dialogTitle.setText("Update Task (alertDialogTitle)");
                dialogTitle.setTextSize(40);

                AlertDialog dialog=builder.setCustomTitle(dialogTitle)
                        .setView(customLayout)
                        .setPositiveButton("OK", null)
                        .create();
                dialog.show();
                EditText etTaskName=dialog.findViewById(R.id.etTaskName);
                EditText etDeadline=dialog.findViewById(R.id.etDeadline);
                EditText etReps=dialog.findViewById(R.id.etReps);
                EditText etFilePath=dialog.findViewById(R.id.etFilePath);

                etTaskName.setText(lmTask.get(position).get("taskName").toString());
                etDeadline.setText(lmTask.get(position).get("deadline").toString());
                etReps.setText(lmTask.get(position).get("reps").toString());
                etFilePath.setText(lmTask.get(position).get("filePath").toString());

                //check empty field
                dialog.getButton(dialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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
                            Task task= new Task(newTaskName,newDeadline,newReps,newFilePath);
                            /*******************************
                             * CALL API & Update Task
                             * ************************/
                            updateTask(task,lmTask.get(position).get("id").toString()); //CALL API & UPDATE TASK
                            dialog.dismiss();
                        }
                    }
                });
            }

        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(context, "u pressed btnDel on position "+position, Toast.LENGTH_SHORT).show();
                String clickedTaskName=lmTask.get(position).get("taskName").toString();
                showQuestionDialogYesNo(context, "Confirmation", "Are you sure you want to delete " + clickedTaskName + "?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {//YES Listener
                        /*******************************
                         * CALL API & Delete Task
                         * ************************/
                        deleteTask(lmTask.get(position).get("id").toString());

                    }
                },null);

            }
        });

        return curItemView;
    }

    private void deleteTask(String id) {
        //DELETE
        Call<DeleteTaskResponse> call = methods.deleteTask(id);
        call.enqueue(new Callback<DeleteTaskResponse>() {
            @Override
            public void onResponse(Call<DeleteTaskResponse> call, Response<DeleteTaskResponse> response) {
                if(response.isSuccessful()){
                    DeleteTaskResponse deleteTaskResponse=response.body();
                    assert deleteTaskResponse != null;
                    DeleteTaskResponse.DeleteTaskResponseData data=deleteTaskResponse.getData();
                    List<Integer> liRowsAffected=data.getRowsAffected();
                    showInfoMsgDialogOK(context, "Rows affected is " + liRowsAffected.toString(), null);
                    ((MainActivity)context).getListMapTask();
                }
                else{
                    showErrorMsgDialogOK(context,response.code()+": Some error happened when deleting task");
                }
            }

            @Override
            public void onFailure(Call<DeleteTaskResponse> call, Throwable t) {
                showErrorMsgDialogOK(context,t.getMessage());
            }
        });
    }

    private void updateTask(Task task, String id) {
        //UPDATE
        Call<UpdateTaskResponse> call = methods.updateTask(task, id);
        call.enqueue(new Callback<UpdateTaskResponse>() {
            @Override
            public void onResponse(Call<UpdateTaskResponse> call, Response<UpdateTaskResponse> response) {
                if(response.isSuccessful()){
                    UpdateTaskResponse updateTaskResponse=response.body();
                    assert updateTaskResponse != null;
                    UpdateTaskResponse.UpdateTaskResponseData data=updateTaskResponse.getData();
                    List<Integer> liRowsAffected=data.getRowsAffected();
                    showInfoMsgDialogOK(context, "Rows affected is " + liRowsAffected.toString(), null);
                    ((MainActivity)context).getListMapTask();
                }
                else{
                    showErrorMsgDialogOK(context,response.code()+": Some error happened when updating task");
                }
            }
            @Override
            public void onFailure(Call<UpdateTaskResponse> call, Throwable t) {
                showErrorMsgDialogOK(context,t.getMessage());
            }
        });
    }

}


