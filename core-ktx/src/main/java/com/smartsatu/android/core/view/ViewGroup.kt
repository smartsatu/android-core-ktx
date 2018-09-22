@file:Suppress("UNCHECKED_CAST")

package com.smartsatu.android.core.view

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * Author
 * Created by danaimset on 9/2/18 and 2:51 PM.
 */

fun <V : View> ViewGroup.inflate(@LayoutRes resId: Int, attachToRoot: Boolean = false): V {
    return inflater().inflate(resId, this, attachToRoot) as V
}