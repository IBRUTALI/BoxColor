package com.example.boxcolor.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boxcolor.model.accounts.AccountsRepository
import com.example.boxcolor.utils.MutableLiveEvent
import com.example.boxcolor.utils.publishEvent
import com.example.boxcolor.utils.share
import kotlinx.coroutines.launch

class SplashViewModel(
    private val accountsRepository: AccountsRepository
) : ViewModel() {

    private val _launchMainScreenEvent = MutableLiveEvent<Boolean>()
    val launchMainScreenEvent = _launchMainScreenEvent.share()

    init {
        viewModelScope.launch {
            _launchMainScreenEvent.publishEvent(accountsRepository.isSignedIn())
        }
    }
}