package com.example.boxcolor

import android.content.Context
import androidx.room.Room
import com.example.boxcolor.model.accounts.AccountsRepository
import com.example.boxcolor.model.accounts.room.RoomAccountsRepository
import com.example.boxcolor.model.boxes.BoxesRepository
import com.example.boxcolor.model.boxes.room.RoomBoxesRepository
import com.example.boxcolor.model.room.AppDatabase
import com.example.boxcolor.model.room.MIGRATION_2_3
import com.example.boxcolor.model.settings.AppSettings
import com.example.boxcolor.model.settings.SharedPreferencesAppSettings
import com.example.boxcolor.utils.security.DefaultSecurityUtilsImpl
import com.example.boxcolor.utils.security.SecurityUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object Repositories {

    private lateinit var applicationContext: Context

    // -- stuffs

    val securityUtils: SecurityUtils by lazy { DefaultSecurityUtilsImpl() }

    private val database: AppDatabase by lazy<AppDatabase> {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db")
            .addMigrations(MIGRATION_2_3)
            .createFromAsset("initial_database.db")
            .build()
    }

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    private val appSettings: AppSettings by lazy {
        SharedPreferencesAppSettings(applicationContext)
    }

    // --- repositories

    val accountsRepository: AccountsRepository by lazy {
        RoomAccountsRepository(database.getAccountsDao(), appSettings, ioDispatcher, securityUtils)
    }

    val boxesRepository: BoxesRepository by lazy {
        RoomBoxesRepository(accountsRepository, database.getBoxesDao(), ioDispatcher)
    }

    /**
     * Call this method in all application components that may be created at app startup/restoring
     * (e.g. in onCreate of activities and services)
     */
    fun init(context: Context) {
        applicationContext = context
    }
}