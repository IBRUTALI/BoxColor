package com.example.boxcolor

import com.example.boxcolor.model.accounts.AccountsRepository
import com.example.boxcolor.model.accounts.InMemoryAccountsRepository
import com.example.boxcolor.model.boxes.BoxesRepository
import com.example.boxcolor.model.boxes.InMemoryBoxesRepository

object Repositories {

    val accountsRepository: AccountsRepository = InMemoryAccountsRepository()

    val boxesRepository: BoxesRepository = InMemoryBoxesRepository(accountsRepository)

}