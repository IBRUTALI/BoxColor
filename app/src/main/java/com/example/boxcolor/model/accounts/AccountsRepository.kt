package com.example.boxcolor.model.accounts

import com.example.boxcolor.model.accounts.entities.Account
import com.example.boxcolor.model.accounts.entities.AccountFullData
import com.example.boxcolor.model.accounts.entities.SignUpData
import kotlinx.coroutines.flow.Flow

interface AccountsRepository {

    /**
     * Whether user is signed-in or not.
     */
    suspend fun isSignedIn(): Boolean

    /**
     * Try to sign-in with the email and password.
     * @throws [EmptyFieldException], [AuthException]
     */
    suspend fun signIn(email: String, password: CharArray)

    /**
     * Create a new account.
     * @throws [EmptyFieldException], [PasswordMismatchException], [AccountAlreadyExistsException]
     */
    suspend fun signUp(signUpData: SignUpData)

    /**
     * Sign-out from the app.
     */
    suspend fun logout()

    /**
     * Get the account info of the current signed-in user.
     */
    suspend fun getAccount(): Flow<Account?>

    /**
     * Change the username of the current signed-in user.
     * @throws [EmptyFieldException], [AuthException]
     */
    suspend fun updateAccountUsername(newUsername: String)

    suspend fun getAllData(): Flow<List<AccountFullData>>

}