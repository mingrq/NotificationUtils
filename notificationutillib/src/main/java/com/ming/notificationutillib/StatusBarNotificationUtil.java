package com.ming.notificationutillib;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.VersionedPackage;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import static androidx.core.app.NotificationCompat.DEFAULT_VIBRATE;
import static androidx.core.app.NotificationCompat.PRIORITY_DEFAULT;

/**
 * 状态栏通知工具
 */
public class StatusBarNotificationUtil extends ContextWrapper {

    public StatusBarNotificationUtil(Context base) {
        super(base);
    }

    /**
     * 发送简单通知
     * <p>
     * 最基本、精简形式（也称为折叠形式）的通知会显示一个图标、一个标题和少量内容文本。在本节中，您将学习如何创建用户单击后可在应用中启动 Activity 的通知。
     *
     * @param notifyId 通知Id
     * @param title 通知标题
     * @param content 通知内容
     * @param icon 通知图标
     *
     */
    public void sendSimpleNotification(int notifyId, String title, String content , int icon ) {
       sendSimpleNotification(notifyId, title, content, icon,PRIORITY_DEFAULT);
    }

    /**
     * 发送简单通知
     * <p>
     * 最基本、精简形式（也称为折叠形式）的通知会显示一个图标、一个标题和少量内容文本。在本节中，您将学习如何创建用户单击后可在应用中启动 Activity 的通知。
     *
     * @param notifyId 通知Id
     * @param title 通知标题
     * @param content 通知内容
     * @param icon 通知图标
     *@param priority 通知优先级
     */
    public void sendSimpleNotification(int notifyId, String title, String content , int icon ,int priority) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setPriority(priority);
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setSmallIcon(icon);
        builder.setOnlyAlertOnce(false);
        builder.setOngoing(false);
        Notification build = builder.build();
        ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(notifyId, build);
    }

}
