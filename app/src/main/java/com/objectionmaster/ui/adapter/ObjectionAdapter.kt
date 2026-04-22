package com.objectionmaster.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.objectionmaster.databinding.ObjectionListItemBinding
import com.objectionmaster.model.Objection

class ObjectionAdapter(
    private val onItemClick: (Objection) -> Unit,
    private val onPlayAudio: (Objection) -> Unit
) : ListAdapter<Objection, ObjectionAdapter.ObjectionViewHolder>(ObjectionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectionViewHolder {
        val binding = ObjectionListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ObjectionViewHolder(binding, onItemClick, onPlayAudio)
    }

    override fun onBindViewHolder(holder: ObjectionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ObjectionViewHolder(
        private val binding: ObjectionListItemBinding,
        private val onItemClick: (Objection) -> Unit,
        private val onPlayAudio: (Objection) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(objection: Objection) {
            binding.apply {
                objectionTitle.text = objection.title
                objectionSubtitle.text = objection.response

                root.setOnClickListener {
                    onItemClick(objection)
                }

                playAudioBtn.setOnClickListener {
                    onPlayAudio(objection)
                }
            }
        }
    }

    class ObjectionDiffCallback : DiffUtil.ItemCallback<Objection>() {
        override fun areItemsTheSame(oldItem: Objection, newItem: Objection): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Objection, newItem: Objection): Boolean {
            return oldItem == newItem
        }
    }
}
