package com.smartsatu.android.core.app

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smartsatu.android.core.content.toast


fun Fragment.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    context?.toast(text, duration)
}

// Navigation


fun Fragment.onSupportBackPressed(): Boolean {
    return childFragmentManager.onSupportBackPressed()
            || (isAdded && isVisible && this is OnSupportBackPressedListener && onBackPressed())
}

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