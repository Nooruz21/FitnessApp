package com.example.fitnessapp.presentation.ui.fragment.board

import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentBoardBinding
import com.example.fitnessapp.presentation.base.BaseFragment
import com.example.fitnessapp.presentation.model.OnBoard
import com.example.fitnessapp.presentation.ui.adapter.BoardAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoardFragment : BaseFragment<BoardViewModel, FragmentBoardBinding>(R.layout.fragment_board) {
    override val viewModel: BoardViewModel by viewModels()
    override val binding by viewBinding(FragmentBoardBinding::bind)

    private lateinit var adapter: BoardAdapter
    override fun initialize() {
        setupAdapter()
        setupIndicator()
        setupCurrentIndicator(0)
    }

    override fun setupListeners() {
        binding.fabNext.setOnClickListener {
            if (binding.viewPager.currentItem + 1 < adapter.itemCount) {
                binding.viewPager.currentItem += 1
            } else {
                findNavController().navigate(R.id.navigation_home)
            }
        }

        binding.textSkip.setOnClickListener {
            findNavController().navigate(R.id.navigation_home)
        }

        binding.btnStart.setOnClickListener {
            findNavController().navigate(R.id.navigation_home)
        }
    }

    private fun setupCurrentIndicator(position: Int) {
        val childCount = binding.indicator.childCount
        for (i in 0 until childCount) {
            val imageView = binding.indicator.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }

    private fun setupIndicator() {
        val indicator = arrayOfNulls<ImageView>(adapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicator.indices) {
            indicator[i] = ImageView(context)
            indicator[i].let {
                it?.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(), R.drawable.indicator_inactive
                    )
                )
                it?.layoutParams = layoutParams
                binding.indicator.addView(it)
            }
        }
    }

    private fun setupAdapter() {
        adapter = BoardAdapter(
            listOf(
                OnBoard(
                    image = R.drawable.ic_borad1,
                    title = "Track Your Goal",
                    description = "Don't worry if you have trouble determining your goals, We can help you determine your goals and track your goals"
                ),
                OnBoard(
                    image = R.drawable.ic_borad1,
                    title = "Get Burn",
                    description = "Let’s keep burning, to achive yours goals, it hurts only temporarily, if you give up now you will be in pain forever"
                ),
                OnBoard(
                    image = R.drawable.ic_borad1,
                    title = "Eat Well",
                    description = "Let's start a healthy lifestyle with us, we can determine your diet every day. healthy eating is fun"
                ),
                OnBoard(
                    image = R.drawable.ic_borad1,
                    title = "Improve Sleep  Quality",
                    description = "Improve the quality of your sleep with us, good quality sleep can bring a good mood in the morning"
                )
            )
        )

        binding.viewPager.adapter = adapter
        binding.viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setupCurrentIndicator(position)
                }
            })
        (binding.viewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
    }

}