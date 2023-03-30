package com.example.boxcolor.model.accounts.room

import androidx.room.*
import com.example.boxcolor.model.accounts.room.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountsDao {

    @Query("SELECT id, hash, salt FROM accounts WHERE email = :email")
    suspend fun findByEmail(email: String): AccountSignInTuple?

    @Update(entity = AccountDbEntity::class)
    suspend fun updateUsername(account: AccountUpdateUsernameTuple)

    @Insert(entity = AccountDbEntity::class)
    suspend fun createAccount(accountDbEntity: AccountDbEntity)

    @Query("SELECT * FROM accounts WHERE id = :accountId")
    fun getById(accountId: Long): Flow<AccountDbEntity?>

    @Transaction
    @Query("SELECT * FROM accounts WHERE accounts.id = :accountId")
    fun getAccountAndEditedBoxes(accountId: Long): AccountAndEditedBoxesTuple

    @Transaction
    @Query("SELECT * FROM accounts")
    fun getAllData(): Flow<List<AccountAndAllSettingsTuple>>

}