package bdl.lockey.ghtk_recycler_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {
    private val _orderList = MutableLiveData<MutableList<Order>>()
    val orderList: LiveData<MutableList<Order>> get() = _orderList

    fun setOrder() {
        _orderList.value = DataSource().getDataSource()
            .reversed().take(20).toMutableList()
    }

    private var currentPage = 0
    private var isLoading = false

    init {
        loadMoreItems()
    }

    fun refresh() {
        currentPage = 0
        _orderList.value = DataSource().getDataSource()
            .reversed().take(20).toMutableList()
        loadMoreItems()
    }


    fun loadMoreItems() {
//        if (isLoading) return
//
//        isLoading = true
//        _orderList.value = _orderList.value?.plus(ListOrder.Loading)
//
//        // Simulate network call or database query
//        viewModelScope.launch {
//            delay(2000) // Simulate network delay
//            val newItems = List(20) { ListItem.Item("Item ${currentPage * 20 + it}") }
//            currentPage++
//            _orderList.value = _orderList.value?.dropLast(1)?.plus(newItems)
//            isLoading = false
//        }
    }
}