package com.example.userpaytracker.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import coil3.request.placeholder
import com.example.userpaytracker.R
import com.example.userpaytracker.databinding.ListItemUsersBinding
import com.example.userpaytracker.domain.model.User

class HomeAdapter(
    private val navigateToPaymentDetails: (User) -> Unit,
) : ListAdapter<User, HomeAdapter.ViewHolder>(DIFF_CALLBACK) {
    inner class ViewHolder(
        val binding: ListItemUsersBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: User) {
            binding.run {
                leadingImage.load(item.picture) {
                    crossfade(true)
                    placeholder(R.drawable.bg_placeholder)
                }
                headlineText.text = item.name
                supportingText.text = "2500" // TODO

                binding.root.setOnClickListener {
                    navigateToPaymentDetails(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding =
            ListItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        holder.bindTo(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<User>() {
                override fun areItemsTheSame(
                    oldItem: User,
                    newItem: User,
                ): Boolean = oldItem.name == newItem.name

                override fun areContentsTheSame(
                    oldItem: User,
                    newItem: User,
                ): Boolean = oldItem == newItem
            }
    }
}
