package com.smartsatu.android.core.app

import androidx.fragment.app.Fragment

fun Fragment.handlePermissionRequest(permissions: Array<String>, requestCode: Int) {
    if (permissions.isNotEmpty()) {
        if (shouldShowRequestPermissionRationale(permissions[0])) {
            // For now there is no additional explanation why do we need permissions
            requestPermissions(permissions, requestCode)
        } else {
            // No explanation needed, we can request the permission.
            requestPermissions(permissions, requestCode)
        }
    }
}