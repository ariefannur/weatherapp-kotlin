package arief.com.forecastapp.model

data class Item (val forecast: List<Forecast>)


data class Forecast(val code:String, val date:String, val day:String, val high:String, val low:String, val text:String)