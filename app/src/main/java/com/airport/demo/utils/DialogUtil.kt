package com.airport.demo.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.airport.demo.R

class DialogUtil {
    fun showDialog(context: Context) {
        AlertDialog.Builder(context)
            .setTitle(context.getString(R.string.notice))
            .setMessage(context.getString(R.string.error_msg))
            .setPositiveButton(context.getString(R.string.confirm)
            ) { dialog, p1 -> dialog?.dismiss() }.show()
    }
}