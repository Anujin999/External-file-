package com.example.externalfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button b1, b2;
    private TextView tv;
    private EditText et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        et= (EditText)findViewById(R.id.et1);
        tv= (TextView) findViewById(R.id.result);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.b1) {
            writeExternal();
        } else if (v.getId() == R.id.b2) {
            readExternal();

        }

    }
    private void writeExternal() {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)) {
            File root = Environment.getExternalStorageDirectory();
            File dir = new File(root.getAbsolutePath() + "/Huree");
            if (!dir.exists()){
                dir.mkdir();

                File file = new File(dir, "external.txt");
                String message = et.getText().toString();

                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(message.getBytes());
                    fos.close();

                    et.setText("");
                    Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    } private void readExternal() {
        File root = Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath()+ "/Huree");
        File file = new File(dir, "external.txt");

        String message;
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            while ((message = br.readLine()) !=null){
                sb.append(message + "\n");
            }
            tv.setText(sb.toString());


        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}