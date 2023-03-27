package com.example.boxcolor.screens.main.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.boxcolor.R
import com.example.boxcolor.Repositories
import com.example.boxcolor.databinding.FragmentTabsBinding
import com.example.boxcolor.utils.viewModelCreator

class TabsFragment: Fragment() {
    private var mBinding: FragmentTabsBinding? = null
    private val binding get() = mBinding!!

    private val viewModel by viewModelCreator { TabsViewModel(Repositories.accountsRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHost = childFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
        val navController = navHost.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

        observeAdminTab()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentTabsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun observeAdminTab() {
        viewModel.showAdminTab.observe(viewLifecycleOwner) { showAdminTab ->
            binding.bottomNavigationView.menu.findItem(R.id.admin).isVisible = showAdminTab
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}