package com.example.andriispasswordmanager

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity
data class AccountRecord(@PrimaryKey(autoGenerate = true)
                         val id: Int? = null,
                         val destination: String,
                         val username: String,
                         val password: String) : Serializable