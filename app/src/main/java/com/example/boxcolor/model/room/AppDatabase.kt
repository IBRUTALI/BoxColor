package com.example.boxcolor.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.boxcolor.model.accounts.room.AccountsDao
import com.example.boxcolor.model.accounts.room.entities.AccountDbEntity
import com.example.boxcolor.model.boxes.room.BoxesDao
import com.example.boxcolor.model.boxes.room.entities.AccountBoxSettingsDbEntity
import com.example.boxcolor.model.boxes.room.entities.BoxDbEntity

@Database(
    version = 1,
    entities = [
        AccountDbEntity::class,
        BoxDbEntity::class,
        AccountBoxSettingsDbEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getAccountsDao(): AccountsDao

    abstract fun getBoxesDao(): BoxesDao

}