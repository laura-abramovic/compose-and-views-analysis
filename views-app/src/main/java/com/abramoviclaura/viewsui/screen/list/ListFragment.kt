package com.abramoviclaura.viewsui.screen.list

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abramoviclaura.shared.screen.list.ListDataProvider
import com.abramoviclaura.views_app.databinding.FragmentListBinding
import com.abramoviclaura.shared.R as SharedR

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = ListDataProvider.listItems()

        binding.itemsList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ListItemAdapter(items)

            val space = resources.getDimensionPixelSize(SharedR.dimen.common_spacing_m)
            addItemDecoration(SpacingItemDecoration(space))
        }
    }

    class SpacingItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.bottom = space
        }
    }
}
