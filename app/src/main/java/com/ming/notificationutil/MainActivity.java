package com.ming.notificationutil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ming.notificationutillib.StatusBarNotificationUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final StatusBarNotificationUtil statusBarNotificationUtil = new StatusBarNotificationUtil(this);
        /**
         * 普通通知
         */
        final int[] i = {1};
        Button simpleNotification = findViewById(R.id.id_simpleNotification);
        simpleNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TestActivity.class);
                statusBarNotificationUtil.setNotificationPriority(StatusBarNotificationUtil.PriorityEnum.MIN);
                statusBarNotificationUtil.sendSimpleNotification(i[0],i[0]+"dsfsd","sdfas",R.mipmap.ic_launcher,intent);
                i[0]++;
            }
        });
    }
}
