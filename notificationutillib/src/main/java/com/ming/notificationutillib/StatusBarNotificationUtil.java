package com.ming.notificationutillib;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;

import androidx.core.app.NotificationCompat;

/**
 * 状态栏通知工具
 */
public class StatusBarNotificationUtil extends ContextWrapper {

    public StatusBarNotificationUtil(Context base) {
        super(base);
    }

    /**
     * 发送简单通知
     *
     *最基本、精简形式（也称为折叠形式）的通知会显示一个图标、一个标题和少量内容文本。在本节中，您将学习如何创建用户单击后可在应用中启动 Activity 的通知。
     */
    public void sendSimpleNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

    }
}
