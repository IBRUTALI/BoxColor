package com.example.boxcolor.model.boxes.room.views

import androidx.room.Embedded
import androidx.room.Relation
import com.example.boxcolor.model.accounts.room.entities.AccountDbEntity
import com.example.boxcolor.model.boxes.room.entities.BoxDbEntity

data class SettingWithEntitiesTuple(
    @Embedded val settingsDbEntity: SettingsDbView,
    @Relation(
        parentColumn = "account_id",
        entityColumn = "id"
    )
    val accountDbEntity: AccountDbEntity,
    @Relation(
        parentColumn = "box_id",
        entityColumn = "id"
    )
    val boxesDbEntity: BoxDbEntity
)

