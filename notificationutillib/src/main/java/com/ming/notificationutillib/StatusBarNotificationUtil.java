package com.ming.notificationutillib;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.VersionedPackage;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import static android.app.Notification.PRIORITY_HIGH;
import static android.app.Notification.PRIORITY_LOW;
import static android.app.Notification.PRIORITY_MAX;
import static android.app.Notification.PRIORITY_MIN;
import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
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
    private NotificationCompat.Builder builder;//通知构建器
    private PriorityEnum priorityEnum;//通知重要性

    public StatusBarNotificationUtil(Context base) {
        super(base);
    }

    public enum PriorityEnum {
        DEFAULT,        //默认
        HIGH,           //高：发出声音
        LOW,            //低：不发出声音，也不在状态栏中显示
        UNSPECIFIED,    //紧急：发出声音并以浮动通知的形式显示
        MIN,           //中：不发出声音
        NONE            //不重要的通知:不显示在阴影中
    }

    /**
     * 设置通知重要性
     */
    public void setNotificationPriority(PriorityEnum priorityEnum) {
        /*if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
            switch (priorityEnum){
                case LOW:
                    priority=;
                    break;
                case MIN:
                    priority=;
                    break;
                case HIGH:
                    priority=;
                    break;
                case NONE:
                    priority=;
                    break;
                case DEFAULT:
                    priority=;
                    break;
                case UNSPECIFIED:
                    priority=;
                    break;
            }
        }else {
            switch (priorityEnum){
                case LOW:
                    priority=;
                    break;
                case MIN:
                    priority=;
                    break;
                case HIGH:
                    priority=;
                    break;
                case NONE:
                    priority=;
                    break;
                case DEFAULT:
                    priority=;
                    break;
                case UNSPECIFIED:
                    priority=;
                    break;
            }
        }*/
        this.priorityEnum = priorityEnum;
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
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NOTIFIACTION_IMPORTANCE);

        //设置重要性
        if (priorityEnum != null) {
            int priority;
            switch (priorityEnum) {
                case LOW:
                    priority = NotificationManager.IMPORTANCE_LOW;
                    break;
                case MIN:
                    priority = NotificationManager.IMPORTANCE_MIN;
                    break;
                case HIGH:
                    priority = NotificationManager.IMPORTANCE_HIGH;
                    break;
                case NONE:
                    priority = NotificationManager.IMPORTANCE_NONE;
                    break;
                case DEFAULT:
                    priority = NotificationManager.IMPORTANCE_DEFAULT;
                    break;
                case UNSPECIFIED:
                    priority = NotificationManager.IMPORTANCE_UNSPECIFIED;
                    break;
                default:
                    priority = NotificationManager.IMPORTANCE_DEFAULT;
                    break;
            }
            channel.setImportance(priority);//设置重要性
        }
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
    public void sendSimpleNotification(int notifyId, String title, String content, int icon, Intent intent) {
        if (builder == null)
            builder = getNotificationCompatBuilder();
        if (title != null)
            builder.setContentTitle(title);//标题
        if (content != null)
            builder.setContentText(content);//内容
        if (icon != -1)
            builder.setSmallIcon(icon);//图标

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 3, intent, FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        Notification build = builder.build();
        getNotificationManager().notify(notifyId, build);
    }

    /**
     * 发送自定义视图通知
     *
     * @param notifyId    通知Id
     * @param remoteViews 通知视图
     */
    public void sendCurrentViewNotification(int notifyId, RemoteViews remoteViews) {
        if (builder == null)
            builder = getNotificationCompatBuilder();
        if (remoteViews != null)
            builder.setCustomContentView(remoteViews);//自定义模板
        Notification build = builder.build();
        getNotificationManager().notify(notifyId, build);
    }


    /**
     * 获取通知构造器
     *
     * @return NotificationCompat.Builder
     */
    public NotificationCompat.Builder getNotificationCompatBuilder() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O)
            createNotificationChannel();
        if (builder == null)
            builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setAutoCancel(true);//点击后自动关闭
        //设置重要性
        if (priorityEnum != null && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            int priority;
            switch (priorityEnum) {
                case LOW:
                    priority = PRIORITY_LOW;
                    break;
                case MIN:
                    priority = PRIORITY_MIN;
                    break;
                case HIGH:
                    priority = PRIORITY_HIGH;
                    break;
                case NONE:
                    priority = PRIORITY_MIN;
                    break;
                case DEFAULT:
                    priority = PRIORITY_DEFAULT;
                    break;
                case UNSPECIFIED:
                    priority = PRIORITY_MAX;
                    break;
                default:
                    priority = PRIORITY_DEFAULT;
                    break;
            }
            builder.setPriority(priority);
        }
        return builder;
    }
}
