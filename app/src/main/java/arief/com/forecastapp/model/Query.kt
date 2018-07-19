package arief.com.forecastapp.model

data class Return(val query: Query)
data class Query(val created:String, val results: Result)
