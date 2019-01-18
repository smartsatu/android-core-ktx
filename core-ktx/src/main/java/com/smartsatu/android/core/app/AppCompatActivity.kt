package com.smartsatu.android.core.app

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

/**
 * Author
 * Created by danaimset on 10/14/18 and 12:06 AM.
 */

// Navigation

fun AppCompatActivity.onSupportBackPressed(): Boolean {
    return supportFragmentManager.onSupportBackPressed()
}

// Runtime permissions

fun AppCompatActivity.isPermissionGranted(permission: String) =
        ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun AppCompatActivity.shouldShowPermissionRationale(permission: String) =
        ActivityCompat.shouldShowRequestPermissionRationale(this, permission)

fun AppCompatActivity.requestPermission(permission: String, requestId: Int) =
        ActivityCompat.requestPermissions(this, arrayOf(permission), requestId)

fun AppCompatActivity.batchRequestPermissions(permissions: Array<String>, requestId: Int) =
        ActivityCompat.requestPermissions(this, permissions, requestId)

