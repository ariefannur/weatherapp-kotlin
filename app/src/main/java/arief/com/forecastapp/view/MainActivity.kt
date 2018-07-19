package arief.com.forecastapp.view

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import arief.com.forecastapp.R
import arief.com.forecastapp.model.Forecast
import arief.com.forecastapp.model.Item
import arief.com.forecastapp.network.Api
import arief.com.forecastapp.network.RestClient
import arief.com.forecastapp.viewmodel.AppViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.DividerItemDecoration



class MainActivity : BaseActivity() {

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun start() {

        val restClient = RestClient()
        val api = restClient.retrofit.create(Api::class.java)
        Log.d("AF ","api : "+(if(api == null){ "null" }else{"tidak null"}))
        val appViewModel =  AppViewModel(api)
        val layManager = LinearLayoutManager(this)
        rv_forecast.layoutManager = layManager
        val dividerItemDecoration = DividerItemDecoration(this, layManager.orientation)
        rv_forecast.addItemDecoration(dividerItemDecoration)

        appViewModel.getData()
        appViewModel.data.observe(this, Observer {
            Log.d("AF ", "data "+Gson().toJson(it))
            img_layout.displayImage(it?.results!!.channel.image.url)
            tv_name.text = it?.results!!.channel.title
            tv_wind.text = "Wind : chill "+it?.results!!.channel.wind.chill+", direction : "+it?.results!!.channel.wind.direction+", speed : "+it?.results!!.channel.wind.speed
            tv_atmosphere.text = "Atmosphere : humidity "+it?.results!!.channel.atmosphere.humidity
            val adapter = Adapter(it?.results!!.channel.item.forecast)
            rv_forecast.adapter = adapter
        })

    }

    override fun initToolbar(toolbar: Toolbar) {
        toolbar.title =  "Forecast App"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


    class Adapter(val lsData: List<Forecast>): RecyclerView.Adapter<ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, null, false))
        }

        override fun getItemCount(): Int {
           return lsData.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val forecast = lsData[position]
            holder?.textDate.text = forecast.day+"-"+forecast.date
            holder?.textList.text = "Temp, High: "+forecast.high+" Low: "+forecast.low
            holder?.textType.text = forecast.text

            var idRess = R.drawable.ic_cloudy
            when(forecast.code.toInt()){
                26, 28 -> idRess = R.drawable.ic_cloudy
                11, 39 -> idRess = R.drawable.ic_rain
                34 -> idRess = R.drawable.ic_sunny
                30 -> idRess = R.drawable.ic_party_cloud
            }
            holder?.img.setImageResource(idRess)

        }

    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        val img by lazy {
            itemView?.findViewById(R.id.img_list) as ImageView
        }

        val textDate by lazy {
            itemView?.findViewById(R.id.tv_date) as TextView
        }

        val textList by lazy {
            itemView?.findViewById(R.id.tv_list_text) as TextView
        }

        val textType by lazy {
            itemView?.findViewById(R.id.tv_type) as TextView
        }


    }
}
