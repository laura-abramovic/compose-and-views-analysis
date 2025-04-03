package com.abramoviclaura.viewsui.screen.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.abramoviclaura.shared.screen.LogTag
import com.abramoviclaura.shared.screen.list.ListItemCategory
import com.abramoviclaura.shared.screen.list.ListItemModel
import com.abramoviclaura.shared.screen.list.toStringRes
import com.abramoviclaura.shared.screen.logMillis
import com.abramoviclaura.views_app.databinding.ItemListCardBinding
import com.abramoviclaura.views_app.databinding.ItemListCardCategoryBinding
import com.abramoviclaura.shared.R as SharedR

class ListItemAdapter(
    private val items: List<ListItemModel>,
    private val onItemClick: (Int) -> Unit,
) : RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder>() {

    class ListItemViewHolder(private val binding: ItemListCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListItemModel, onItemClick: (Int) -> Unit) {
            binding.apply {
                root.setOnClickListener {
                    logMillis(LogTag.LIST_DETAILS_NAVIGATION_TIME) {
                        onItemClick(item.id)
                    }
                }

                root.id = item.id
                image.load(item.imageUrl)
                image.clipToOutline = true

                title.text = item.title
                subtitle.text = item.subtitle

                val bookmarkRes = if (item.bookmarked) SharedR.drawable.ic_bookmark_filled else SharedR.drawable.ic_bookmark
                buttonBookmark.setImageResource(bookmarkRes)
                buttonBookmark.setOnClickListener { /* bookmark action */ }

                addCategories(item.categories, categoryContainer, binding.root)
            }
        }

        private fun addCategories(categories: List<ListItemCategory>, container: LinearLayout, parent: ViewGroup) {
            container.removeAllViews()
            categories.forEachIndexed { index, category ->
                val inflater = LayoutInflater.from(parent.context)
                val categoryBinding = ItemListCardCategoryBinding.inflate(inflater, parent, false)

                categoryBinding.root.id = category.ordinal
                if (container.containsItem(categoryBinding.root.id).not()) {
                    addCategoryView(
                        binding = categoryBinding,
                        category = category,
                        container = container,
                        parent = parent,
                        addMargin = index != categories.lastIndex
                    )
                }
            }
        }

        private fun LinearLayout.containsItem(itemId: Int) = children.any { it.id == itemId }

        private fun addCategoryView(
            binding: ItemListCardCategoryBinding,
            category: ListItemCategory,
            container: LinearLayout,
            parent: ViewGroup,
            addMargin: Boolean,
        ) {
            binding.root.text = parent.resources.getText(category.toStringRes()).toString().uppercase()

            val marginEnd = if (addMargin) {
                parent.resources.getDimensionPixelSize(SharedR.dimen.common_spacing_s)
            } else {
                0
            }

            val params = binding.root.layoutParams as ConstraintLayout.LayoutParams
            params.setMargins(0, 0, marginEnd, 0)
            binding.root.layoutParams = params

            container.addView(binding.root)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListCardBinding.inflate(inflater, parent, false)
        return ListItemViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(
            item = items[position],
            onItemClick = onItemClick
        )
    }
}
