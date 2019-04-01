package com.smartsatu.android.core.content.res

import android.content.res.AssetManager

/**
 * Author
 * Created by danaimset on 2019-03-25 and 19:09.
 */

fun AssetManager.read(fileName: String): String {
    return open(fileName).reader().readText()
}