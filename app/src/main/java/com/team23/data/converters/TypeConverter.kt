package com.team23.data.converters

import androidx.room.TypeConverter
import java.util.*


class TypeConverter {
    /**
     * converting a date to string in order to persist the date in database
     */
    @TypeConverter
    fun dateToString(date: Date?) = date?.convertToString(SQLITE_DATE_FORMAT)

    /**
     * converting a string to date in order to retrieve the date from the database
     */
    @TypeConverter
    fun dateFromString(stringDate: String?) = stringDate?.convertToDate(SQLITE_DATE_FORMAT)
}