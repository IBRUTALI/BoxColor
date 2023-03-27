package com.example.boxcolor

import android.content.Context
import androidx.room.Room
import com.example.boxcolor.model.accounts.AccountsRepository
import com.example.boxcolor.model.accounts.room.RoomAccountsRepository
import com.example.boxcolor.model.boxes.BoxesRepository
import com.example.boxcolor.model.boxes.room.RoomBoxesRepository
import com.example.boxcolor.model.room.AppDatabase
import com.example.boxcolor.model.settings.AppSettings
import com.example.boxcolor.model.settings.SharedPreferencesAppSettings
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object Repositories {

    private lateinit var applicationContext: Context

    private val database: AppDatabase by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db")
            .createFromAsset("initial_database.db")
            .build()
    }

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    private val appSettings: AppSettings by lazy {
        SharedPreferencesAppSettings(applicationContext)
    }

    val accountsRepository: AccountsRepository by lazy {
        RoomAccountsRepository(database.getAccountsDao(), appSettings, ioDispatcher)
    }

    val boxesRepository: BoxesRepository by lazy {
        RoomBoxesRepository(accountsRepository, database.getBoxesDao(), ioDispatcher)
    }

    fun init(context: Context) {
        applicationContext = context
    }
}