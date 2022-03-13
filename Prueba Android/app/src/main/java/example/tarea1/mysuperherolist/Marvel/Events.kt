package example.tarea1.mysuperherolist.Marvel

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)