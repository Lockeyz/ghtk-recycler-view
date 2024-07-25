package bdl.lockey.ghtk_recycler_view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import bdl.lockey.ghtk_recycler_view.databinding.FragmentOrderBinding
import java.util.Collections
import kotlin.random.Random

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    lateinit var orderAdapter: OrderAdapter

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

        val list = DataSource().getDataSource()
        orderAdapter = OrderAdapter(list)
        binding.recyclerView.adapter = orderAdapter
        orderAdapter.notifyDataSetChanged()

        binding.swipeRefreshLayout.setOnRefreshListener {

            binding.swipeRefreshLayout.isRefreshing = false
            list.shuffle(Random(System.currentTimeMillis()))
            orderAdapter.notifyDataSetChanged()
        }

        binding.recyclerView.addOnScrollListener(object: OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}