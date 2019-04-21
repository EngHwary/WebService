package com.example.nh.webservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button getData,showData;
    TextView textView;
    String link="https://jsonplaceholder.typicode.com/users";
    URL url;
    InputStream inputStream;
    String result;
    HttpURLConnection urlConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showData=findViewById(R.id.ShowData);
        getData=findViewById(R.id.GetData);
        textView=findViewById(R.id.tex1);



        showData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView.setText(result);





            }
        });
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            url=new URL(link);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        try {
                            urlConnection= (HttpURLConnection) url.openConnection();
                            urlConnection.setConnectTimeout(15000);
                            urlConnection.setReadTimeout(15000);
                            urlConnection.setRequestMethod("GET");

                            inputStream=urlConnection.getInputStream();
                            int responseCode=urlConnection.getResponseCode();
                            int c=0;

                            StringBuffer buffer=new StringBuffer();
                            if (responseCode==HttpURLConnection.HTTP_OK){

                                while ((c=inputStream.read()) !=-1){
                                    buffer.append((char) c);
                                }
                            }

                            result=buffer.toString();
                            inputStream.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        finally {
                            urlConnection.disconnect();


                        }


                    }
                }).start();




            }
        });
    }
}
