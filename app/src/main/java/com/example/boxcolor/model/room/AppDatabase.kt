package com.example.boxcolor.model.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.boxcolor.model.accounts.room.AccountsDao
import com.example.boxcolor.model.accounts.room.entities.AccountDbEntity
import com.example.boxcolor.model.boxes.room.BoxesDao
import com.example.boxcolor.model.boxes.room.entities.AccountBoxSettingsDbEntity
import com.example.boxcolor.model.boxes.room.entities.BoxDbEntity
import com.example.boxcolor.model.boxes.room.views.SettingsDbView

@Database(
    version = 4,
    entities = [
        AccountDbEntity::class,
        BoxDbEntity::class,
        AccountBoxSettingsDbEntity::class
    ],
    views = [
        SettingsDbView::class
    ],
    autoMigrations = [
        AutoMigration(
            from = 2,
            to = 3,
            spec = AutoMigrationSpec1To2::class
        )
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getAccountsDao(): AccountsDao

    abstract fun getBoxesDao(): BoxesDao

}