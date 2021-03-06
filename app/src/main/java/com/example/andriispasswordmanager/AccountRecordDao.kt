package com.example.andriispasswordmanager

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Update
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Query

@Dao
interface AccountRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(accountRecord: AccountRecord)

    @Update
    fun update(accountRecord: AccountRecord)

    @Delete
    fun delete(accountRecord: AccountRecord)

    @Query("SELECT * FROM AccountRecord WHERE username == :name")
    fun getAccountByName(name: String): List<AccountRecord>

    @Query("SELECT * FROM AccountRecord")
    fun getAccounts(): LiveData<List<AccountRecord>>
}