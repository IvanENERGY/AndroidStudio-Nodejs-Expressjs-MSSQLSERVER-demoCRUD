package com.example.frontend_todoapp.webService;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Methods {
    @GET("tasks") //"http://127.0.0.1:3000/api/tasks"
    Call<GetAllTaskResponse> getAllTask();

    @POST("tasks")//"http://127.0.0.1:3000/api/tasks"
    Call<CreateTaskResponse> createTask(@Body Task task);

    @PUT("tasks/{id}/update")//"http://127.0.0.1:3000/api/tasks/3/update"
    Call<UpdateTaskResponse> updateTask(@Body Task task,@Path("id") String id);

    @DELETE("tasks/{id}/delete")//"http://127.0.0.1:3000/api/tasks/3/delete"
    Call<DeleteTaskResponse> deleteTask(@Path("id") String id);
}
