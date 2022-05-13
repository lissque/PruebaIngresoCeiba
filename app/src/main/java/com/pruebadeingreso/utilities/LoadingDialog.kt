package com.pruebadeingreso.utilities

import android.app.Activity
import android.app.AlertDialog
import com.example.pruebadeingreso.R
import com.pruebadeingreso.activities.MainActivity

class LoadingDialog(val activity: Activity) {

    private lateinit var dialog: AlertDialog

    fun startLoading(){
        val layoutInflater = activity.layoutInflater
        val progress = layoutInflater.inflate(R.layout.progressbar,null)

        val builder = AlertDialog.Builder(activity)
        builder.setView(progress)
        builder.setCancelable(false)
        dialog = builder.create()
        dialog.show()
    }

    fun dismiss(){
        dialog.dismiss()
    }

}