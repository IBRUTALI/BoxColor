package com.example.boxcolor.screens.main.tabs.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.boxcolor.R
import com.example.boxcolor.Repositories
import com.example.boxcolor.databinding.FragmentBoxBinding
import com.example.boxcolor.utils.observeEvent
import com.example.boxcolor.utils.viewModelCreator
import com.example.boxcolor.views.DashboardItemView

class BoxFragment : Fragment() {
    private var mBinding: FragmentBoxBinding? = null
    private val binding get() = mBinding!!
    private val viewModel by viewModelCreator {
        BoxViewModel(
            getBoxId(),
            Repositories.boxesRepository
        )
    }
    private val args by navArgs<BoxFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root.setBackgroundColor(DashboardItemView.getBackgroundColor(getColorValue()))
        binding.boxTextView.text = getString(R.string.this_is_box, getColorName())

        binding.goBackButton.setOnClickListener { onGoBackButtonPressed() }

        listenShouldExitEvent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentBoxBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun onGoBackButtonPressed() {
        findNavController().popBackStack()
    }

    private fun listenShouldExitEvent() =
        viewModel.shouldExitEvent.observeEvent(viewLifecycleOwner) { shouldExit ->
            if (shouldExit) {
                findNavController().popBackStack()
            }
        }

    private fun getBoxId(): Long = args.boxId
    private fun getColorValue(): Int = args.colorValue
    private fun getColorName(): String = args.colorName

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}