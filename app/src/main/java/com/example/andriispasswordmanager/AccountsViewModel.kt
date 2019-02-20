package com.example.andriispasswordmanager

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AccountsViewModel(application: Application) : AndroidViewModel(application) {
    private val accountRecordDao: AccountRecordDao = AppDatabase.getAppDatabase(application).accountRecordDao()
    private val repository : AccountsRepository
    val allAccounts: LiveData<List<AccountRecord>>
    private val parentJob: Job
    private val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + parentJob
    private val coroutineScope: CoroutineScope

    init {
        repository = AccountsRepository(accountRecordDao)
        allAccounts = repository.allAccounts
        parentJob = Job()
        coroutineScope = CoroutineScope(coroutineContext)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    fun insert(accountRecord: AccountRecord) = coroutineScope.launch(Dispatchers.IO) {
        repository.insert(accountRecord)
    }
}