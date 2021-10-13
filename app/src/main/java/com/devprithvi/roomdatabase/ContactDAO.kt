package com.devprithvi.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*

//Step 2 :: CRUD Operation by using DAO Class...
//we will execute on background threads...
//if we execute on main threads ...there will exception...
///by help of coroutines we can execute on  //Suspend ::Background Threads..


@Dao
interface ContactDAO {

    @Insert
    suspend fun insertContact(contact: Contact)


    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)


    //there is no need to define suspend here ..bcz Livedata is compatible with Room Data base ...
    //it automatically run on the background threads..
    @Query("SELECT * FROM contact")
    fun getContact(): LiveData<List<Contact>>

}