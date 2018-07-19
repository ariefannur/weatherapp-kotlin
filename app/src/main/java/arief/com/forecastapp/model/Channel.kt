package arief.com.forecastapp.model

data class Channel (val title:String, val description:String, val link: String, val item: Item, val wind:Wind,val image:Image, val atmosphere: Atmosphere)

data class Wind(val chill:String, val direction:String, val speed:String)

data class Atmosphere(val humidity:String, val pressure:String, val rising:String)

data class Image(val title:String, val url:String)
