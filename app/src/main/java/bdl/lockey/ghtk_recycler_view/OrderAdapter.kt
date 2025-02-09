package bdl.lockey.ghtk_recycler_view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bdl.lockey.ghtk_recycler_view.databinding.ItemDealBinding
import bdl.lockey.ghtk_recycler_view.databinding.ItemNotDealBinding

class OrderAdapter(private var dataset: MutableList<Order>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ItemDealViewHolder(private val binding: ItemDealBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(order: Order) {
            binding.tvStt.text = order.id.toString()
            binding.tvName.text = order.name
            binding.tvIsDeal.text = "Đã chốt"
            binding.tvPhoneNumber.text = order.phone
            binding.tvGoods.text = "/ " + order.goods
            binding.tvTime.text = "7 ngày"
            binding.tvCod.text = "Tiền CoD: " + order.cod.toString() + " đ / SP"
            binding.tvAddress.text = "Địa chỉ: " + order.address
            binding.tvStatus.text = "Trạng thái: Đẩy sang GHKT không thành công!"
        }
    }

    class ItemNotDealViewHolder(private val binding: ItemNotDealBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(order: Order) {
            binding.tvStt.text = order.id.toString()
            binding.tvName.text = order.name
            binding.tvIsDeal.text = "Đang chốt"
            binding.tvPhoneNumber.text = order.phone
            binding.tvGoods.text = "/ " + order.goods
            binding.tvTime.text = "7 ngày"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_DEAL -> ItemDealViewHolder(
                ItemDealBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            VIEW_TYPE_NOT_DEAL -> ItemNotDealViewHolder(
                ItemNotDealBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    override fun getItemCount(): Int {
        return dataset.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataset[position]
        when (holder) {
            is ItemNotDealViewHolder -> item.let { holder.bind(it) }
            is ItemDealViewHolder -> item.let { holder.bind(it) }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataset[position].isDeal) {
            true -> VIEW_TYPE_DEAL
            false -> VIEW_TYPE_NOT_DEAL
        }
    }

    companion object {
        const val VIEW_TYPE_NOT_DEAL = 0
        const val VIEW_TYPE_DEAL = 1
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(item: List<Order>){
        dataset.addAll(item)
        notifyDataSetChanged()
    }
}