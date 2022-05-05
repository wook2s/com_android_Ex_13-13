package com.example.asb_ex_13_9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    T1 t1;
    boolean flagForThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar pb1 = (ProgressBar) findViewById(R.id.sb1);
        ProgressBar pb2 = (ProgressBar) findViewById(R.id.sb2);
        Button btnStart = (Button) findViewById(R.id.btnStart);

        TextView tv1 = (TextView) findViewById(R.id.tv1);
        TextView tv2 = (TextView) findViewById(R.id.tv2);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flagForThread = true;
                t1 = new T1(pb1, tv1, flagForThread);
                t1.start();

                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        for(int i=0; i<100; i++){
                            if(flagForThread == false){
                                break;
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pb2.setProgress(pb2.getProgress() + 1);
                                    tv2.setText("진행률 : "+pb2.getProgress());
                                }
                            });

                            SystemClock.sleep(100);
                        }
                    }
                }.start();
            }

        });

        Button btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flagForThread = false;
                t1.stopThread(flagForThread);

                pb1.setProgress(10);
                pb2.setProgress(30);
            }
        });



        Button btnStop = (Button) findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flagForThread = false;
                t1.stopThread(flagForThread);
            }
        });

    }

}

class T1 extends Thread{

    ProgressBar pb;
    TextView tv;
    boolean flag;

    T1(ProgressBar pb, TextView tv, boolean flag){
        this.pb = pb;
        this.tv = tv;
        this.flag = flag;
    }

    @Override
    public void run() {
        //flag = true;
        super.run();
        for(int i=0; i<100; i++){
            if(flag == false){
                break;
            }
            pb.setProgress(pb.getProgress()+2);
            tv.setText("진행률 : " + pb.getProgress());
            SystemClock.sleep(100);
        }

    }
    public void stopThread(boolean flag){
        this.flag = flag;
    }
}
