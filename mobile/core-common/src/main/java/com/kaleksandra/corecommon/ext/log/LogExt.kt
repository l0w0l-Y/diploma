package com.kaleksandra.corecommon.ext.log

import android.util.Log

private const val DEBUG_TAG = "DEBUG"

fun <Data> debug(data: Data, tag: String? = DEBUG_TAG) {
    Log.d(tag, data.toString())
}