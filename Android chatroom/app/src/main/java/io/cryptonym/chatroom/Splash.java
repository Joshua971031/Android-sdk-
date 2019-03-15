package io.cryptonym.chatroom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import io.agora.chatroom.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
        getSupportActionBar().hide();  //隐藏标题栏
        setContentView(R.layout.activity_splash);
        Thread myThread=new Thread(){//创建子线程
            @Override
            public void run(){
                try{
                    sleep(3000);//使程序休眠五秒
                    Intent it=new Intent(getApplicationContext(),MainActivity.class);//启动主程序
                    startActivity(it);
                    finish();//关闭当前活动
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        myThread.start();//启动线程
    }
}