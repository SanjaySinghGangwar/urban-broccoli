package dev.sanjaygangwar.tempproject.fcm

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dev.sanjaygangwar.tempproject.R
import dev.sanjaygangwar.tempproject.ui.activity.Main


class CustomFirebaseBaseMessaging : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        //todo will see what we can do
    }

    var NOTIFICATION_CHANNEL_ID = 1234
    var intent: Intent? = null
    var pendingIntent: PendingIntent? = null
    var context: Context? = null
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        context = baseContext
        intent = Intent(context, Main::class.java)
        remoteMessage.data?.let {
            receiveData(remoteMessage)
        }

    }

    private fun receiveData(remoteMessage: RemoteMessage) {
        if (!remoteMessage.notification?.title.isNullOrBlank() && !remoteMessage.notification?.body.isNullOrEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                createNotificationAboveOreo(remoteMessage)
            else
                createNotificationBelowOreo(remoteMessage)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationAboveOreo(data: RemoteMessage) {
        pendingIntent = PendingIntent.getActivity(
            context,
            NOTIFICATION_CHANNEL_ID,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val name: String? = context?.getString(R.string.app_name)
        val description = context?.getString(R.string.notification)
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID.toString(), name, importance)
        channel.description = description
        val notificationManage = getSystemService(NotificationManager::class.java)
        notificationManage.createNotificationChannel(channel)

        val builder = context?.let {
            NotificationCompat.Builder(it, NOTIFICATION_CHANNEL_ID.toString())
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(data.notification?.title)
                .setStyle(NotificationCompat.BigTextStyle().bigText(data.notification?.body))
                .setAutoCancel(true)
                .setLights(Color.BLUE, 500, 500)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_MAX)
        }

        val notificationManager = context?.let { NotificationManagerCompat.from(it) }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) { return }
        builder?.build()?.let { notificationManager?.notify(NOTIFICATION_CHANNEL_ID, it) }
    }

    private fun createNotificationBelowOreo(data: RemoteMessage) {
        pendingIntent = PendingIntent.getActivity(
            context,
            NOTIFICATION_CHANNEL_ID,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val mBuilder = context?.let {
            NotificationCompat.Builder(it)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setColor(resources.getColor(R.color.black))
                .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle(data.notification?.title)
                .setStyle(NotificationCompat.BigTextStyle().bigText(data.notification?.body))
                .setDefaults(Notification.DEFAULT_LIGHTS).setContentIntent(pendingIntent)
                .setAutoCancel(true)
        }
        mNotificationManager.notify(NOTIFICATION_CHANNEL_ID, mBuilder?.build())
    }
}