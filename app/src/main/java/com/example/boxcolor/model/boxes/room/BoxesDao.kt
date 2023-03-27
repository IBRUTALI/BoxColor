package com.example.boxcolor.model.boxes.room

import androidx.room.*
import com.example.boxcolor.model.boxes.room.entities.AccountBoxSettingsDbEntity
import com.example.boxcolor.model.boxes.room.entities.BoxAndSettingsTuple
import com.example.boxcolor.model.boxes.room.entities.BoxDbEntity
import com.example.boxcolor.model.boxes.room.views.SettingWithEntitiesTuple
import com.example.boxcolor.model.boxes.room.views.SettingsDbView
import kotlinx.coroutines.flow.Flow

@Dao
interface BoxesDao {
    @Transaction
    @Query("SELECT * FROM settings_view WHERE account_id = :accountId")
    fun getBoxesAndSettings(accountId: Long): Flow<List<SettingWithEntitiesTuple>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setActiveFlagForBox(accountBoxSettingsDbEntity: AccountBoxSettingsDbEntity)

}