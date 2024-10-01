package com.abramoviclaura.viewsui.screen.bouncingball

import android.animation.AnimatorInflater
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abramoviclaura.views_app.R
import com.abramoviclaura.views_app.databinding.FragmentBouncingBallBinding

class BouncingBallFragment : Fragment() {

    private var _binding: FragmentBouncingBallBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBouncingBallBinding.inflate(inflater, container, false)
        setUpBouncingAnimation()

        return binding.root
    }

    private fun setUpBouncingAnimation() {
        val bounceAnimator = AnimatorInflater.loadAnimator(context, R.animator.anim_bounce)
        bounceAnimator.setTarget(binding.ball)
        bounceAnimator.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
