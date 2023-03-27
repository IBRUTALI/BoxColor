package com.example.boxcolor.model.accounts.entities

import com.example.boxcolor.model.boxes.entities.BoxAndSettings

data class AccountFullData(
    val account: Account,
    val boxesAndSettings: List<BoxAndSettings>
)
