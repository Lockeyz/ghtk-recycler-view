package bdl.lockey.ghtk_recycler_view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import bdl.lockey.ghtk_recycler_view.databinding.FragmentOrderBinding
import kotlin.random.Random

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    private lateinit var orderAdapter: OrderAdapter
    private var isLoading = false
    private var currentPage = 1
    private val pageSize = 20


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

        viewModel.orderList.observe(viewLifecycleOwner) {
            orderAdapter = OrderAdapter(it)
            binding.recyclerView.adapter = orderAdapter
            orderAdapter.notifyDataSetChanged()
        }
        Log.d("orderList", viewModel.orderList.value?.size.toString())

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            refresh()
            orderAdapter.notifyDataSetChanged()
        }

        binding.recyclerView.addOnScrollListener(object: OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (!isLoading && totalItemCount <= (lastVisibleItem + 1)
                    && totalItemCount < DataSource().getDataSource().size) {
                    loadMoreItems()
                    orderAdapter.notifyDataSetChanged()
                    isLoading = false
                }

            }
        })
    }

    // Load more moi lan them 20 item
    @SuppressLint("NotifyDataSetChanged")
    private fun loadMoreItems() {
        val start = currentPage * pageSize
        val end = minOf(start + pageSize, DataSource().getDataSource().size)
        val newItems = DataSource().getDataSource().reversed().subList(start, end)
        viewModel.orderList.value?.addAll(newItems)
        currentPage ++
        Toast.makeText(requireContext(), "Load more", Toast.LENGTH_SHORT).show()
    }

    // Moi khi co du lieu moi, se lay 20 phan tu dau tien cua du lieu moi va dat lai current page la 1
    private fun refresh() {
        currentPage = 1
        viewModel.orderList.value?.addAll(DataSource().getDataSource()
            .reversed().take(20).toMutableList())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}