package com.example.boxcolor.screens.main.tabs.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boxcolor.model.accounts.AccountsRepository
import com.example.boxcolor.model.accounts.entities.Account
import com.example.boxcolor.utils.MutableLiveEvent
import com.example.boxcolor.utils.publishEvent
import com.example.boxcolor.utils.share
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val accountsRepository: AccountsRepository
) : ViewModel() {

    private val _account = MutableLiveData<Account>()
    val account = _account.share()

    private val _restartFromLoginEvent = MutableLiveEvent<Unit>()
    val restartWithSignInEvent = _restartFromLoginEvent.share()

    init {
        viewModelScope.launch {
            accountsRepository.getAccount().collect {
                _account.value = it
            }
        }
    }

     fun logout() {
         viewModelScope.launch {
             accountsRepository.logout()
             restartAppFromLoginScreen()
         }
    }

    private fun restartAppFromLoginScreen() {
        _restartFromLoginEvent.publishEvent()
    }

}