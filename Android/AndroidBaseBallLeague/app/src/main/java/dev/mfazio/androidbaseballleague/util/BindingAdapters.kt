package dev.mfazio.androidbaseballleague.util

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

/**
 *overload the android:src and set the source of the image to the ImageView
 */
@BindingAdapter("android:src")
fun setImageViewResource(imageView: ImageView, resourceId: Int) {
    imageView.setImageResource(resourceId)
}

@BindingAdapter("android:backgroundTint")
fun setBackgroundTint(view: View, colorId: Int) {
    view.background.setTintList(
        ContextCompat.getColorStateList(view.context, colorId)
    )
}