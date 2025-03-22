package com.abramoviclaura.viewsui.screen.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.load
import com.abramoviclaura.shared.screen.details.DetailsValues
import com.abramoviclaura.shared.screen.list.ListDataProvider
import com.abramoviclaura.views_app.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    companion object {
        const val TAG = "DetailsFragment"
        const val ARGUMENT_ID = "argument_id"
    }

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt(ARGUMENT_ID) ?: 0
        val item = ListDataProvider.getItem(id)

        binding.apply {
            title.text = item.title
            subtitle.text = item.subtitle
            text.text = DetailsValues.getLoremIpsumText()
            image.load(item.imageUrl)
            backIcon.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}
