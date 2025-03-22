package com.abramoviclaura.viewsui.screen.greetings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abramoviclaura.views_app.databinding.FragmentGreetingsBinding
import com.abramoviclaura.shared.R as SharedR

class GreetingsFragment : Fragment() {

    companion object {
        const val TAG = "GreetingsFragment"
    }

    private lateinit var binding: FragmentGreetingsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentGreetingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            updateTitle("")

            greetingsButton.setOnClickListener {
                updateTitle(editText.text.toString())
            }
        }
    }

    private fun FragmentGreetingsBinding.updateTitle(name: String) {
        greetingsTitle.text = getString(
            SharedR.string.hello_user,
            name.ifBlank { getString(SharedR.string.user) }
        )
    }
}
