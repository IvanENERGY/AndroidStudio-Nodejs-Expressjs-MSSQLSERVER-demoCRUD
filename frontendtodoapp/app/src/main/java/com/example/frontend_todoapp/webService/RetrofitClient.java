package com.example.frontend_todoapp.webService;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    private static String BASE_URL="http://192.168.1.6:3000/api/"; //last char. must be backslash

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {    //this method add header to every request sent out, instead of adding it separately on each one
                        @NonNull
                        @Override
                        public Response intercept(@NonNull Chain chain) throws IOException {
                            Request originalRequest = chain.request();
                            Request newRequest = originalRequest.newBuilder()
                                    .header("Interceptor-Header", "xyz") // if header key are same as the one set up separately, this will replace them
                                    //if wanting header with samename, use addHeader method
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    }) // must be added before log interceptor, if we want to log this header
                    .addInterceptor(loggingInterceptor) //http request will be logged to logcat
                    .build();

            // Gson gson=new GsonBuilder().serializeNulls().create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) //gson inside create() can make gson NOT ignore null value (eg.when patching null,null will still used to replace to object)
                    .client(okHttpClient) //tell retrofit use this one instead of default one
                    .build();
        }
        return retrofit;
    }
}
