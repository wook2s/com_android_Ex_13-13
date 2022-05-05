package com.example.asb_ex_13_9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar pb1 = (ProgressBar) findViewById(R.id.sb1);
        ProgressBar pb2 = (ProgressBar) findViewById(R.id.sb2);
        Button btnStart = (Button) findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                T1 t1 = new T1(pb1);
                t1.start();

                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        for(int i=0; i<100; i++){
                            pb2.setProgress(pb2.getProgress() + 1);
                            SystemClock.sleep(100);
                        }
                    }
                }.start();
            }

        });

    }
}

class T1 extends Thread{

    ProgressBar pb;

    T1(ProgressBar pb){
        this.pb = pb;
    }

    @Override
    public void run() {
        super.run();
        for(int i=0; i<100; i++){
            pb.setProgress(pb.getProgress()+2);
            SystemClock.sleep(100);
        }

    }
}
