package bdl.lockey.ghtk_recycler_view

data class Order(
    val id: Int,
    val name: String,
    val isDeal: Boolean,
    val date: String,
    val phone: String,
    val goods: String,
    val cod: Int,
    val address: String,
    val status: Boolean
)
