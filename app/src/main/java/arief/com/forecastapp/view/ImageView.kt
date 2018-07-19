package arief.com.forecastapp.view

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.displayImage(url:String){
    Picasso.get().load(url).into(this)
}