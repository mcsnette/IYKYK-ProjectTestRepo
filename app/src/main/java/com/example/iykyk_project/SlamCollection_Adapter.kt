package com.example.iykyk_project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iykyk_project.databinding.SlamuserDesignBinding

class SlamCollection_Adapter(
    private val slamUserList: List<SlamBookData>,
    private var itemClickListener: OnItemClickListener? = null
) : RecyclerView.Adapter<SlamCollection_Adapter.SlamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlamViewHolder {
        val binding = SlamuserDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SlamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SlamViewHolder, position: Int) {
        val currentItem = slamUserList[position]
        holder.bind(currentItem)

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(currentItem)
        }
    }

    override fun getItemCount(): Int = slamUserList.size

    inner class SlamViewHolder(private val binding: SlamuserDesignBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(slamBookData: SlamBookData) {
            binding.slamUserNickname.text = slamBookData.q1
        }

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener?.onItemClick(slamUserList[position])
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(slamBookData: SlamBookData)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        itemClickListener = listener
    }
}
