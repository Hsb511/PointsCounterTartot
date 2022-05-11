package com.team23.data.converters

import java.text.SimpleDateFormat
import java.util.*

const val SQLITE_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss"

fun Date.convertToString(format: String): String =
    SimpleDateFormat(format, Locale.FRENCH).format(this)

fun String.convertToDate(format: String): Date =
    SimpleDateFormat(format, Locale.FRENCH).parse(this)!!