package com.xr.wallpaper;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.xr.wallpaper.Service.ChangeService;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Button btn_start;
    private Button btn_stop;
    PendingIntent pi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Acp.getInstance(this).request(new AcpOptions.Builder().setPermissions(Manifest.permission.SET_WALLPAPER).build(), new AcpListener() {
//            @Override
//            public void onGranted() {
//
//            }
//
//            @Override
//            public void onDenied(List<String> permissions) {
//                Toast.makeText(MainActivity.this,permissions.toString() + "权限拒绝",Toast.LENGTH_SHORT).show();
//            }
//        });
        init();
        Intent intent = new Intent(MainActivity.this, ChangeService.class);
        pi=PendingIntent.getService(MainActivity.this,0,intent,0);

    }

    private void init() {
        btn_start= (Button)findViewById(R.id.btn1);
        btn_stop=(Button)findViewById(R.id.btn2);
    }

    public void button(View view) {
        AlarmManager manager=(AlarmManager) getSystemService(ALARM_SERVICE);
        switch (view.getId()){
            case R.id.btn1:
                manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,0,60000,pi);
                btn_start.setEnabled(false);
                btn_stop.setEnabled(true);
                Toast.makeText(MainActivity.this, "壁纸定时更换启动成功啦", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2:
                btn_start.setEnabled(true);
                btn_stop.setEnabled(false);
                manager.cancel(pi);
                break;
        }
    }
}
