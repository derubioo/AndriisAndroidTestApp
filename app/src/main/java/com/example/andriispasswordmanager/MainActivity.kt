package com.example.andriispasswordmanager

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    lateinit var childCoroutinesJob: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + childCoroutinesJob

    private lateinit var accountsViewModel: AccountsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: Adapter
    private lateinit var viewManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) = runBlocking {
        super.onCreate(savedInstanceState)
        childCoroutinesJob = Job()

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        accountsViewModel = ViewModelProviders.of(this@MainActivity).get(AccountsViewModel::class.java)

        fab.setOnClickListener {
            startActivityForResult(
                Intent(applicationContext, AddPasswordActivity::class.java),
                ADD_RECORD_REQUEST_CODE)
        }

        viewManager = LinearLayoutManager(this@MainActivity)
        viewAdapter = Adapter(this@MainActivity)
        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            // Add spacing at the bottom of list items.
            //addItemDecoration(SpacingDecorator(Utils.dpToPx(applicationContext, R.dimen.accounts_list_items_spacing)))
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            //setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = viewManager
            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }

        accountsViewModel.allAccounts.observe(this@MainActivity, Observer { accounts ->
                accounts?.let {
                    viewAdapter.setDataset(it)
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_RECORD_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.let { intent ->
                val accountRecord = intent.getSerializableExtra(AddPasswordActivity.ACCOUNT_RECORD) as AccountRecord
                accountsViewModel.insert(accountRecord)

                Snackbar.make(findViewById(R.id.activity_main),
                    getString(R.string.snackbar_text_saved),
                    Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        childCoroutinesJob.cancel()
    }

    companion object {
        const val ADD_RECORD_REQUEST_CODE = 100
    }
}
