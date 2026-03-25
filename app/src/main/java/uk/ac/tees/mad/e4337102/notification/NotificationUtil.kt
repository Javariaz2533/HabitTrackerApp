package uk.ac.tees.mad.e4337102.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

object NotificationUtil {

    const val CHANNEL_ID = "habit_reminder_channel"

    fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Habit Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "Reminders for daily habits"

            val manager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}
