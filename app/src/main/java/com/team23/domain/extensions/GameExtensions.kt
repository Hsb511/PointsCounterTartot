package com.team23.domain.extensions

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import com.team23.domain.models.Game

const val SMALL_DATE_FORMAT = "dd/MM/yy"

@SuppressLint("SimpleDateFormat")
fun Game.getSmallDate(): String = SimpleDateFormat(SMALL_DATE_FORMAT).format(this.startDate)