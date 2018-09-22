package com.smartsatu.android.core.content

import android.accounts.AccountManager
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.util.SparseArray
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.core.util.containsKey
import androidx.localbroadcastmanager.content.LocalBroadcastManager

/**
 * Author
 * Created by danaimset on 9/22/18 and 9:04 PM.
 */

fun Context.getApp() = this.applicationContext as Application

fun Context.getLayoutInflater() = LayoutInflater.from(this)

fun Context.broadcasts() = LocalBroadcastManager.getInstance(this)

fun Context.am() = getSystemService(Context.ACCOUNT_SERVICE) as AccountManager

// Permissions

fun Context.isPermissionsRequired(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED
}

// Container for managers: lazy initialization and map container

@Suppress("UNCHECKED_CAST")
object ManagersContainer {
    private val managers = SparseArray<Any>()

    fun contains(id: Int): Boolean {
        return managers.containsKey(id)
    }

    fun put(id: Int, manager: Any) {
        if (!managers.containsKey(id)) {
            managers.put(id, manager)
        }
    }

    fun <S> get(id: Int): S {
        if (managers.containsKey(id)) {
            return managers.get(id) as S
        }

        throw IllegalArgumentException("System service with ID = $id was not found in managers container")
    }

    fun typeOf(name: String): Class<*> {
        when (name) {
            Context.ACCOUNT_SERVICE -> {
                return AccountManager::class.java
            }
            else -> {
                throw IllegalArgumentException("Unknown service name = $name")
            }
        }
    }
}
