package com.smartsatu.android.core.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


fun <T : ViewDataBinding> LayoutInflater.inflateBinding(
        @LayoutRes layoutResId: Int, parent: ViewGroup? = null, attachToParent: Boolean = false): T {
    return DataBindingUtil.inflate(this, layoutResId, parent, attachToParent)
}