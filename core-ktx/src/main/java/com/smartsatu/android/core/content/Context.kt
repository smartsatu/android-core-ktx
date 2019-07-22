package com.smartsatu.android.core.content

import android.accounts.AccountManager
import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager

/**
 * Author
 * Created by danaimset on 9/22/18 and 9:04 PM.
 */

fun Context.getApp() = this.applicationContext as Application

fun Context.inflater() = LayoutInflater.from(this)

fun Context.broadcasts() = LocalBroadcastManager.getInstance(this)

fun Context.broadcastSync(action: String, extras: Bundle? = null) = broadcastSync(Intent(action), extras)

fun Context.broadcastSync(intent: Intent, extras: Bundle? = null) = broadcasts()
        .sendBroadcastSync(intent.apply { extras?.let { putExtras(extras) } })

fun Context.register(receiver: BroadcastReceiver, vararg actions: String) = LocalBroadcastManager.getInstance(this)
        .registerReceiver(receiver, IntentFilter().apply { actions.forEach { addAction(it) } })

fun Context.unregister(receiver: BroadcastReceiver) = LocalBroadcastManager.getInstance(this)
        .unregisterReceiver(receiver)

fun Context.am() = getSystemService(Context.ACCOUNT_SERVICE) as AccountManager

fun Context.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

// Permissions

fun Context.isPermissionsRequired(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED
}