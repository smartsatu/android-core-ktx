package com.smartsatu.android.core.app

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.core.app.ActivityCompat

fun Activity?.handlePermissionRequest(permissions: Array<String>, requestCode: Int) {
    if (permissions.isNotEmpty()) {
        this?.let {
            if (ActivityCompat.shouldShowRequestPermissionRationale(it,
                            permissions[0])) {
                // For now there is no additional explanation why do we need permissions
                ActivityCompat.requestPermissions(this, permissions, requestCode)
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, permissions, requestCode)
            }
        }
    }
}

fun Activity?.dialPhoneNumber(phoneNumber: String) {
    this?.let {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent)
        }
    }
}