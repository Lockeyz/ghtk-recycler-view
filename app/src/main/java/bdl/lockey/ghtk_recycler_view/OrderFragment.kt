package bdl.lockey.ghtk_recycler_view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import bdl.lockey.ghtk_recycler_view.databinding.FragmentOrderBinding
import kotlin.random.Random

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    private lateinit var orderAdapter: OrderAdapter
    private lateinit var list: MutableList<Order>

    private val viewModel: OrderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        list = DataSource().getDataSource()
//        orderAdapter = OrderAdapter(list)
//        binding.recyclerView.adapter = orderAdapter
//        orderAdapter.notifyDataSetChanged()



        Log.d("orderList", viewModel.orderList.value?.size.toString())

        viewModel.setOrder()

        viewModel.orderList.observe(viewLifecycleOwner) {
            orderAdapter = OrderAdapter(it)
            binding.recyclerView.adapter = orderAdapter
            orderAdapter.notifyDataSetChanged()
        }
        Log.d("orderList", viewModel.orderList.value?.size.toString())


        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
            orderAdapter.notifyDataSetChanged()
        }

        binding.recyclerView.addOnScrollListener(object: OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

            }
        })
    }

//    private fun loadMore() {
////        list.add(null)
//        orderAdapter.notifyItemInserted(list.size - 1)
//
//
//        val handler: Handler = Handler()
//        handler.postDelayed(Runnable {
//            rowsArrayList.remove(rowsArrayList.size() - 1)
//            val scrollPosition: Int = rowsArrayList.size()
//            recyclerViewAdapter.notifyItemRemoved(scrollPosition)
//            var currentSize = scrollPosition
//            val nextLimit = currentSize + 10
//
//            while (currentSize - 1 < nextLimit) {
//                rowsArrayList.add("Item $currentSize")
//                currentSize++
//            }
//
//            recyclerViewAdapter.notifyDataSetChanged()
//            isLoading = false
//        }, 2000)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}