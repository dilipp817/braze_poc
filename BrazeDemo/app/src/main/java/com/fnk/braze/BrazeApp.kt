package com.fnk.braze

import android.app.Application
import android.util.Log
import com.braze.Braze
import com.braze.BrazeActivityLifecycleCallbackListener
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class BrazeApp: Application() {
    override fun onCreate() {
        super.onCreate()

        registerActivityLifecycleCallbacks(
            BrazeActivityLifecycleCallbackListener(
                sessionHandlingEnabled = true,
                registerInAppMessageManager = true
            )
        )
        getFCMToken()
    }

    private fun getFCMToken() {
        FirebaseMessaging.getInstance()
            .token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("FCM Token:", "Fetching FCM registration token failed", task.exception)
                    task.exception?.printStackTrace()
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token = task.result
                Braze.getInstance(applicationContext).registeredPushToken = token
                Log.w("FCM Token:", "FCM registration is: $token")

            })
    }
}
