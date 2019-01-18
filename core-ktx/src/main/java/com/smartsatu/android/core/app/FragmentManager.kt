package com.smartsatu.android.core.app

import androidx.fragment.app.FragmentManager

/**
 * Author
 * Created by danaimset on 10/14/18 and 12:36 AM.
 */


fun FragmentManager.onSupportBackPressed(): Boolean {
    for (fragment in fragments) {
        if (fragment.onSupportBackPressed())
            return true
    }
    return false
}