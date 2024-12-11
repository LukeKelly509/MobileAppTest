package com.example.assignment2.CloudMessaging

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.assignment2.R
import com.google.firebase.messaging.FirebaseMessaging

class NotificationMessaging(private val context: Context) {
    //https://www.appsdeveloperblog.com/push-notifications-example-kotlin-firebase/
    //https://www.youtube.com/watch?v=mW8CZFdCr_M
    //https://www.youtube.com/watch?v=2xoJi-ZHmNI
    val notifications = mutableStateListOf<Notifications>()

    init {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
            }
        }
    }

    fun triggerNotification(title: String, message: String) {
        val newNotification = Notifications(title, message)
        notifications.add(newNotification)

        showNotification(title, message)
    }

    private fun showNotification(title: String, message: String) {
        val channelID = "favorite_quiz_channel"
        val channelName = "Favorite Quiz Notifications"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelID,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for favorited quizzes"
                enableVibration(true)
                vibrationPattern = longArrayOf(0, 500, 500, 500)
            }

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.mipmap.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(0, 500, 500, 500))
            .build()

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(System.currentTimeMillis().toInt(), notification)
    }


    data class Notifications(
        val title: String,
        val message: String
    )
}