package com.talo.notification_oreo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.talo.notification_oreo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNotification.setOnClickListener {
            notificationManager()
        }

    }

    private fun notificationManager(
        channelId: String = "type",
        channelName: String = "Note"
    ): NotificationManager {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val channel =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH).also {
                    manager.createNotificationChannel(it)
                    val builder = Notification.Builder(this)
                        .setSmallIcon(com.google.android.material.R.drawable.ic_clock_black_24dp)
                        .setContentTitle("Is Title")
                        .setContentText("Notification")
                        .setSubText("Info")
                        .setWhen(System.currentTimeMillis())
                        .setChannelId(channelId)
                    manager.notify(1, builder.build())
                }

            } else {
                TODO("VERSION.SDK_INT < O")
            }
        manager.createNotificationChannel(channel)
        return manager
    }


}