package com.example.frontend_todoapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.frontend_todoapp.webService.Methods;

import java.util.List;
import java.util.Map;

public class TodoAppStatic {
    public static List<Map<String,Object>> lmTasks; //store the retrieval from calling api
    public static Methods methods;
    public static void showInfoMsgDialogOK(Context context, String Msg, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Information")
                .setMessage(Msg)
                .setCancelable(false)
                .setPositiveButton("OK", onClickListener);// if onclicklistener is null, the dialog will still dimiss. No worry!
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }

    public static void showErrorMsgDialogOK(Context context, String Msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Oops")
                .setMessage(Msg)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
    public static void showQuestionDialogYesNo(Context context,String title, String question,DialogInterface.OnClickListener yesListener,DialogInterface.OnClickListener noListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(question)
                .setCancelable(false)
                .setPositiveButton("Yes",yesListener)
                .setNegativeButton("No",noListener);
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
}
