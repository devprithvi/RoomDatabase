package com.devprithvi.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


/*
*Room DataBase
*::Abstraction Over Sqlite
*::Less Boilerplate code..
*::Compile time Verification Of Sql Queries..
*::Multiple Libraries Support
*
*5 Things >>>>>
*>Entities(Tables)
*>DAO(DATA ACCESS OBJECT) to access the database
*	>Interface Containg MEthod to access database - CRUD Operation
*	>We can define multiple DAPs
*>DATABASE
*>TYPE CONVERTER
*>MIGRATION>> later changes on the data base after lauching the app...
>>
 */
class MainActivity : AppCompatActivity() {
    lateinit var database: ContactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = ContactDatabase.getDatabase(this)

        //val database2 = ContactDatabase.getDatabase(this)
        //this is not a good Practice...
        //for database we use singleton pattern...
        /**
         * database =
        Room.databaseBuilder(applicati onContext, ContactDatabase::class.java, "contactDB")
        .build()
         */

        //Coroutine call ::: It will run on the background Threads...
        GlobalScope.launch {
            database.contactDao().insertContact(Contact(0, "John", "99898989", Date(),1))
        }


    }

    fun getData(view: View) {
        database.contactDao().getContact().observe(this, Observer {
            Log.d("devprithvi", it.toString())
        })
    }
}