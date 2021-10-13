package com.devprithvi.roomdatabase

import androidx.room.TypeConverter
import java.util.*

class Converters {

    //Conv date to Long
    @TypeConverter
    fun fromDateToLong(value: Date): Long {
        return value.time

    }

    //Conv Long to Date..
    @TypeConverter
    fun fromLongToDate(value: Long): Date {
        return Date(value)
    }
}