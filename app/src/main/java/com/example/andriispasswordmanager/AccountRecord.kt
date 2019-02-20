package com.example.andriispasswordmanager

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class AccountRecord(@PrimaryKey(autoGenerate = true)
                         val id: Int = -1,
                         val name: String,
                         val password: String)