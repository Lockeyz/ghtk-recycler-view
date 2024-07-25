package bdl.lockey.ghtk_recycler_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel: ViewModel() {
    private val _order = MutableLiveData<MutableList<Order>>()
    val order: LiveData<MutableList<Order>> get() = _order

    fun setOrder(order: MutableList<Order>) {
        _order.value = order
    }
}