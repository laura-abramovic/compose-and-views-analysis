package com.abramoviclaura.viewsui.screen.list

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abramoviclaura.shared.screen.list.ListDataProvider
import com.abramoviclaura.views_app.R
import com.abramoviclaura.views_app.databinding.FragmentListBinding
import com.abramoviclaura.viewsui.screen.details.DetailsFragment
import com.abramoviclaura.shared.R as SharedR

class ListFragment : Fragment() {

    companion object {
        const val TAG = "ListFragment"
    }

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = ListDataProvider.listItems()

        binding.itemsList.apply {
            layoutManager = LinearLayoutManager(activity)

            adapter = ListItemAdapter(
                items = items,
                onItemClick = {
                    val bundle = Bundle().apply { putInt(DetailsFragment.ARGUMENT_ID, it) }
                    findNavController().navigate(R.id.details_fragment, bundle)
                }
            )

            val space = resources.getDimensionPixelSize(SharedR.dimen.common_spacing_m)
            addItemDecoration(SpacingItemDecoration(space))
        }.also {
            activity?.reportFullyDrawn()
        }
    }

    class SpacingItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.bottom = space
        }
    }
}
