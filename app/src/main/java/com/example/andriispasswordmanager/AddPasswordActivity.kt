package com.example.andriispasswordmanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import android.content.Intent
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View


class AddPasswordActivity: AppCompatActivity() {
    private val tag = "AddPasswordActivity"

    private lateinit var editTextDestination: EditText
    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_password_activity)

        editTextDestination = findViewById(R.id.editTextDestination)
        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)

        findViewById<Button>(R.id.button_save).setOnClickListener { view ->
            saveData(view)
        }
    }

    private fun saveData(view: View) = runBlocking {
        val destination = editTextDestination.text?.toString() ?: let {
            editTextDestination.error = getString(R.string.required_warning)
            editTextDestination.requestFocus()
            return@runBlocking
        }
        val username = editTextUsername.text?.toString() ?: let {
            editTextUsername.error = getString(R.string.required_warning)
            editTextUsername.requestFocus()
            return@runBlocking
        }
        val password = editTextPassword.text?.toString() ?: let {
            editTextPassword.error = getString(R.string.required_warning)
            editTextPassword.requestFocus()
            return@runBlocking
        }

        val accountRecord = AccountRecord(
            destination = destination,
            username = username,
            password = password)

        launch {
            AppDatabase.INSTANCE?.accountRecordDao()?.insertAccount(accountRecord)
            Log.i(tag, "Saved data")
        }

        // TODO: Bug. Snackbar is not showing up.
        Snackbar.make(view, getString(R.string.snackbar_text_saved), Snackbar.LENGTH_LONG).show()
        finish()
        startActivity(Intent(applicationContext, MainActivity::class.java))
    }
}