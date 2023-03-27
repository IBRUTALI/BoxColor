package com.example.boxcolor.model.boxes.room

import android.graphics.Color
import com.example.boxcolor.model.AuthException
import com.example.boxcolor.model.accounts.AccountsRepository
import com.example.boxcolor.model.boxes.BoxesRepository
import com.example.boxcolor.model.boxes.entities.Box
import com.example.boxcolor.model.boxes.entities.BoxAndSettings
import com.example.boxcolor.model.boxes.room.entities.AccountBoxSettingsDbEntity
import com.example.boxcolor.model.boxes.room.entities.BoxAndSettingsTuple
import com.example.boxcolor.model.boxes.room.entities.SettingsTuple
import com.example.boxcolor.model.room.wrapSQLiteException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

class RoomBoxesRepository(
    private val accountsRepository: AccountsRepository,
    private val boxesDao: BoxesDao,
    private val ioDispatcher: CoroutineDispatcher
) : BoxesRepository {

    override suspend fun getBoxesAndSettings(onlyActive: Boolean): Flow<List<BoxAndSettings>> {
        return accountsRepository.getAccount()
            .flatMapLatest { account ->
                if (account == null) return@flatMapLatest flowOf(emptyList())
                queryBoxesAndSettings(account.id)
            }
            .mapLatest { boxAndSettings ->
                if (onlyActive) {
                    boxAndSettings.filter { it.isActive }
                } else {
                    boxAndSettings
                }
            }
    }

    override suspend fun activateBox(box: Box) = wrapSQLiteException(ioDispatcher) {
        setActiveFlagForBox(box, true)
    }

    override suspend fun deactivateBox(box: Box) = wrapSQLiteException(ioDispatcher) {
        setActiveFlagForBox(box, false)
    }

    private fun queryBoxesAndSettings(accountId: Long): Flow<List<BoxAndSettings>> {
        return boxesDao.getBoxesAndSettings(accountId)
            .map { entities ->
                entities.map {
                    val boxEntity = it.boxesDbEntity
                    val settingEntity = it.settingsDbEntity
                    BoxAndSettings(
                        box = boxEntity.toBox(),
                        isActive = settingEntity.settings.isActive
                    )
                }
            }
    }

    private suspend fun setActiveFlagForBox(box: Box, isActive: Boolean) {
        val account = accountsRepository.getAccount().first() ?: throw AuthException()
        boxesDao.setActiveFlagForBox(
            AccountBoxSettingsDbEntity(
                accountId = account.id,
                boxId = box.id,
                settings = SettingsTuple(isActive = isActive)
            )
        )
    }
}