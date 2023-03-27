package com.example.boxcolor.model.boxes.room.views

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.Embedded
import com.example.boxcolor.model.boxes.room.entities.SettingsTuple

@DatabaseView(
    viewName = "settings_view",
    value = "SELECT " +
            "accounts.id as account_id, " +
            "boxes.id as box_id, " +
            "boxes.color_name, " +
            "boxes.color_value," +
            "ifnull(accounts_boxes_settings.is_active, 1) as is_active " +
            "FROM accounts " +
            "JOIN boxes " +
            "LEFT JOIN accounts_boxes_settings " +
            "   ON accounts_boxes_settings.account_id = accounts.id " +
            "       And accounts_boxes_settings.box_id = boxes.id"
)
data class SettingsDbView(
    @ColumnInfo(name = "account_id") val accountId: Long,
    @ColumnInfo(name = "box_id") val boxId: Long,
    @Embedded val settings: SettingsTuple
)