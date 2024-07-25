package bdl.lockey.ghtk_recycler_view.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import bdl.lockey.ghtk_recycler_view.DataSource
import bdl.lockey.ghtk_recycler_view.Order
import bdl.lockey.ghtk_recycler_view.OrderAdapter
import bdl.lockey.ghtk_recycler_view.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = DataSource().getDataSource()
        binding . recyclerView . adapter = OrderAdapter (list)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}