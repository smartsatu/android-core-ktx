package com.smartsatu.android.core.content

import android.accounts.AccountManager
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
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

fun Context.am() = getSystemService(Context.ACCOUNT_SERVICE) as AccountManager

fun Context.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

// Permissions

fun Context.isPermissionsRequired(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED
}