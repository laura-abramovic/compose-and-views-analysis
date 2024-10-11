package com.abramoviclaura.viewsui.screen.bouncingball

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.abramoviclaura.shared.screen.bouncingball.BouncingBallValues
import com.abramoviclaura.views_app.databinding.FragmentBouncingBallBinding
import kotlin.random.Random
import com.abramoviclaura.shared.R as SharedR

private const val TRANSLATION_Y_ANIMATOR_NAME = "translationY"

class BouncingBallFragment : Fragment() {

    private var _binding: FragmentBouncingBallBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBouncingBallBinding.inflate(inflater, container, false)
        generateBallImages()

        return binding.root
    }

    private fun generateBallImages() {
        val windowHeight = requireActivity().windowManager.defaultDisplay.height
        val windowWidth = requireActivity().windowManager.defaultDisplay.width
        val imageSize = resources.getDimension(SharedR.dimen.bouncing_ball_size).toInt()

        repeat(BouncingBallValues.BALL_COUNT) {
            val imageView = generateBallImage(size = imageSize, windowHeight = windowHeight, windowWidth = windowWidth)
            binding.root.addView(imageView)
        }
    }

    private fun generateBallImage(size: Int, windowHeight: Int, windowWidth: Int): ImageView = ImageView(requireContext()).apply {
        layoutParams = FrameLayout.LayoutParams(size, size).apply {
            gravity = Gravity.BOTTOM
            translationX = Random.nextInt(0, windowWidth - size).toFloat()
        }

        setImageResource(SharedR.drawable.ic_basketball)
        setColorFilter(BouncingBallValues.getRandomColor())
        setUpBouncingAnimation(height = Random.nextInt(windowHeight / 2, windowHeight - size))
    }

    private fun ImageView.setUpBouncingAnimation(height: Int) {
        val bounceAnimator = ObjectAnimator.ofFloat(this, TRANSLATION_Y_ANIMATOR_NAME, -height.toFloat(), 0f).apply {
            startDelay = BouncingBallValues.getRandomStartDelay().toLong()
            duration = BouncingBallValues.getRandomDuration().toLong()
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
            interpolator = BounceInterpolator()
        }

        bounceAnimator.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
