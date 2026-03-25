package uk.ac.tees.mad.e4337102.notification

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import uk.ac.tees.mad.e4337102.R

object ReminderNotifier {

    fun show(context: Context, habitName: String) {
        val notification = NotificationCompat.Builder(context, NotificationUtil.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Habit Reminder")
            .setContentText("Don't forget: $habitName")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val manager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(habitName.hashCode(), notification)
    }
}
