package com.aman.realstate.extensions

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.Fragment
import com.aman.realstate.BuildConfig
import com.aman.realstate.ui.MainViewModel.Companion.TAG

fun Activity.showToast(message : String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(message : String){
    Toast.makeText(activity , message, Toast.LENGTH_SHORT).show()
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun AppCompatImageView.setImage(name: String?) {
    name?.let {
        val drawableName = when (it) {
            "apartment" -> "ic_apartment"
            "condo" -> "ic_condo"
            "boat" -> "ic_boat"
            "land" -> "ic_land"
            "rooms" -> "ic_room"
            "no-room" -> "ic_no_room"
            "swimming" -> "ic_swimming"
            "garden" -> "ic_garden"
            "garage" -> "ic_garage"
            else -> "ic_apartment"
        }

        try {
            val resourceID = resources.getIdentifier(drawableName, "drawable", BuildConfig.APPLICATION_ID)
            this.setImageDrawable(getDrawable(this.context, resourceID))
        } catch (exception: Exception) {
            Log.e(TAG, "Failed to get resources ${exception.localizedMessage}")
        }
    }
}