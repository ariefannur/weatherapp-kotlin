package arief.com.forecastapp.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import kotlinx.android.synthetic.main.toolbar_layout.*

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        start()
        initToolbar(toolbar)
    }

    abstract fun getLayout():Int
    abstract fun start()
    abstract fun initToolbar(toolbar: Toolbar)

    fun BaseActivity.snackThis(rootView:View, message:String){
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
    }
}

