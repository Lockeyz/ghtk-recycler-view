package bdl.lockey.ghtk_recycler_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {
    private val _orderList = MutableLiveData<MutableList<Order>>()
    val orderList: LiveData<MutableList<Order>> get() = _orderList

    fun setOrderList() {
        _orderList.value = DataSource().getDataSource()
            .reversed().take(20).toMutableList()
    }

    init {
        _orderList.value = DataSource().getDataSource()
            .reversed().take(20).toMutableList()
    }


}