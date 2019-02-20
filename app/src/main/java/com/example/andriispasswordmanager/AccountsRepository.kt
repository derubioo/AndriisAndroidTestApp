package com.example.andriispasswordmanager

import android.support.annotation.WorkerThread

class AccountsRepository(private val accountRecordDao: AccountRecordDao) {
    val allAccounts = accountRecordDao.getAccounts()

    @WorkerThread
    suspend fun insert(accountRecord: AccountRecord) {
        accountRecordDao.insert(accountRecord)
    }
}