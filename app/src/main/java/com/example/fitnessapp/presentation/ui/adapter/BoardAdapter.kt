package com.example.fitnessapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.databinding.ItemBoardBinding
import com.example.fitnessapp.presentation.model.OnBoard

class BoardAdapter(private val onBoardItem: List<OnBoard>) :
    RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardAdapter.ViewHolder {
        return ViewHolder(
            ItemBoardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BoardAdapter.ViewHolder, position: Int) {
        holder.bind(onBoardItem[position])
    }

    override fun getItemCount() = onBoardItem.size
    inner class ViewHolder(private var binding: ItemBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: OnBoard) {
            binding.image.setImageResource(position.image)
            binding.tittle.text = position.title
            binding.description.text = position.description
        }
    }
}
