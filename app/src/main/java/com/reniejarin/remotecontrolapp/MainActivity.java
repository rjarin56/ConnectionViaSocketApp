package com.reniejarin.remotecontrolapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

//    Button send;
    EditText editText;
    String message;

    private static Socket socket;
//    private static ServerSocket serverSocket;
//    private static InputStreamReader inputStreamReader;
//    private static BufferedWriter bufferedWriter;
    private static PrintWriter printWriter;
    private static String ipAddress = "10.65.33.35";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

    }

    public void sendMessage(View v){
        message = editText.getText().toString();
        myTask task = new myTask();
        task.execute();

        Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_LONG).show();
    }

    class myTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            try{
                socket = new Socket(ipAddress, 5000); //Connect to socket at port 5000
                printWriter = new PrintWriter(socket.getOutputStream()); // set the output stream
                printWriter.write(message); // send the message via socket
                printWriter.flush();  //this will flush the socket of any data
                printWriter.close(); //this closes the printwriter
                socket.close();     //this closes the socket
            }
            catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }
    }
}
