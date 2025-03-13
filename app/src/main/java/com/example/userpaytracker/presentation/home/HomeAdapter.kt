package com.example.userpaytracker.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.example.userpaytracker.R
import com.example.userpaytracker.databinding.ListItemUsersBinding
import com.example.userpaytracker.domain.model.User
import com.example.userpaytracker.presentation.core.utils.dpToPx

class HomeAdapter(
    private val context: Context,
    private val onClickListener: OnClickListener,
) : ListAdapter<User, HomeAdapter.ViewHolder>(DIFF_CALLBACK) {
    inner class ViewHolder(
        val binding: ListItemUsersBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: User) {
            binding.run {
                leadingImage.load(item.picture) {
                    crossfade(true)
                    placeholder(R.drawable.bg_placeholder)
                    error(R.drawable.baseline_person_24)
                }
                if (item.paymentCompleted == true) {
                    leadingImage.strokeWidth = dpToPx(2f, context).toFloat()
                }
                leadingImage.transitionName = item.id.toString()

                headlineText.text = item.name
                supportingText.text = item.email
                paymentText.text = "₹${item.paymentAmount?.toInt()}"

                binding.root.setOnClickListener {
                    val extras = FragmentNavigatorExtras(leadingImage to item.id.toString())
                    onClickListener.onClick(item, extras)
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
                ): Boolean = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: User,
                    newItem: User,
                ): Boolean = oldItem == newItem
            }
    }

    class OnClickListener(
        val clickListener: (User, FragmentNavigator.Extras) -> Unit,
    ) {
        fun onClick(
            user: User,
            extras: FragmentNavigator.Extras,
        ) = clickListener(user, extras)
    }
}
