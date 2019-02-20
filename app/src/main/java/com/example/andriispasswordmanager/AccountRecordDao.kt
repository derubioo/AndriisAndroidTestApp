package com.example.andriispasswordmanager

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Update
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Query

@Dao
public interface AccountRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccount(accountRecord: AccountRecord)

    @Update
    fun updateAccount(accountRecord: AccountRecord)

    @Delete
    fun deleteAccount(accountRecord: AccountRecord)

    @Query("SELECT * FROM AccountRecord WHERE username == :name")
    fun getAccountByName(name: String): List<AccountRecord>

    @Query("SELECT * FROM AccountRecord")
    fun getAccounts(): List<AccountRecord>
}