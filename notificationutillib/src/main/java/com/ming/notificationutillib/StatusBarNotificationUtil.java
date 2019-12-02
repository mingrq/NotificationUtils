package com.ming.notificationutillib;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.VersionedPackage;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import static androidx.core.app.NotificationCompat.DEFAULT_VIBRATE;
import static androidx.core.app.NotificationCompat.PRIORITY_DEFAULT;

/**
 * 状态栏通知工具
 */
public class StatusBarNotificationUtil extends ContextWrapper {

    private static final String CHANNEL_ID = "StatusBarNotificationId";//通道id
    private static final CharSequence CHANNEL_NAME = "StatusBarNotificationName";//通道名称
    private static final int NOTIFIACTION_IMPORTANCE = NotificationManager.IMPORTANCE_DEFAULT;//通知重要
    private NotificationManager notificationManager;//通知管理器

    public StatusBarNotificationUtil(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O)
            createNotificationChannel();
    }

    /**
     * 获取通知管理器
     *
     * @return
     */
    private NotificationManager getNotificationManager() {
        if (notificationManager == null)
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        return notificationManager;
    }


    /**
     * 创建通道
     */
    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NOTIFIACTION_IMPORTANCE);

        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        getNotificationManager().createNotificationChannel(channel);
    }


    /**
     * 发送简单通知
     * <p>
     * 最基本、精简形式（也称为折叠形式）的通知会显示一个图标、一个标题和少量内容文本。在本节中，您将学习如何创建用户单击后可在应用中启动 Activity 的通知。
     *
     * @param notifyId 通知Id
     * @param title    通知标题
     * @param content  通知内容
     * @param icon     通知图标
     */
    public void sendSimpleNotification(int notifyId, String title, String content, int icon) {
        sendSimpleNotification(notifyId, title, content, icon, PRIORITY_DEFAULT);
    }

    /**
     * 发送简单通知
     * <p>
     * 最基本、精简形式（也称为折叠形式）的通知会显示一个图标、一个标题和少量内容文本。在本节中，您将学习如何创建用户单击后可在应用中启动 Activity 的通知。
     *
     * @param notifyId 通知Id
     * @param title    通知标题
     * @param content  通知内容
     * @param icon     通知图标
     * @param priority 通知优先级
     */
    public void sendSimpleNotification(int notifyId, String title, String content, int icon, int priority) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {

        } else {

        }
        builder.setPriority(priority);
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setSmallIcon(icon);
        builder.setOnlyAlertOnce(false);
        builder.setOngoing(false);
        Notification build = builder.build();
        getNotificationManager().notify(notifyId, build);
    }
}
