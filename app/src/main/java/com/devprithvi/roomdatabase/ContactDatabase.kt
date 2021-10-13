package com.devprithvi.roomdatabase

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.*

/*
Step 3:: Database class..
 */

@Database(entities = [Contact::class], version = 2)
@TypeConverters(Converters::class)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDAO


    //Companion Object...? we are using singleton.Pattern .here ,.and there
    //is also posssibility ...at the same time two threads are trying to create this ..
    //hre we use locking mechanism
    companion object {


        //For the MIgration:::::::
        val migration_1_2  = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE contact ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1) ")
            }
        }

        //@Volatile...kuchh bhi write hita hai Instance p to sare threads ko pta chal jaega..
        @Volatile
        private var INSTANCE: ContactDatabase? = null

        fun getDatabase(context: Context): ContactDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(
                            context.applicationContext,
                            ContactDatabase::class.java,
                            "contactDB"
                        )
                            .addMigrations(migration_1_2)
                            .build()
                }
            }


            return INSTANCE!! //non nullable
        }
    }

}