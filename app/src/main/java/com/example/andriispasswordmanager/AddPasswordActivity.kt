package com.example.andriispasswordmanager

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.content.Intent
import android.text.TextUtils
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class AddPasswordActivity: AppCompatActivity(), CoroutineScope {
    lateinit var childCoroutinesJob: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + childCoroutinesJob

    private lateinit var editTextDestination: EditText
    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_password_activity)
        childCoroutinesJob = Job()

        editTextDestination = findViewById(R.id.editTextDestination)
        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)

        findViewById<Button>(R.id.button_save).setOnClickListener {
            generateData()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        childCoroutinesJob.cancel()
    }

    private fun generateData() = runBlocking {
        if (!TextUtils.isEmpty(editTextDestination.text.toString()) &&
            !TextUtils.isEmpty(editTextUsername.text.toString()) &&
            !TextUtils.isEmpty(editTextPassword.text.toString()))
        {
            val destination = editTextDestination.text.toString()
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()
            val accountRecord = AccountRecord(
                destination = destination,
                username = username,
                password = password)

            val replyIntent = Intent()
            replyIntent.putExtra(ACCOUNT_RECORD, accountRecord)
            setResult(Activity.RESULT_OK, replyIntent)
        }
        else {
            setResult(Activity.RESULT_CANCELED)
        }

        finish()
    }

    companion object {
        const val ACCOUNT_RECORD = "AccountRecord"
    }
}