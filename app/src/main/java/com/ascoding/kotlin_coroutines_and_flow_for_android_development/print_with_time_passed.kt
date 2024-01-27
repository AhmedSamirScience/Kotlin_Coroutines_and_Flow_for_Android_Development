package com.ascoding.kotlin_coroutines_and_flow_for_android_development

import android.util.Log

fun printWithTimePassed(message: Any?, startTime: Long) {
    val timePassed = System.currentTimeMillis() - startTime
    print("${timePassed}ms: ")
    println(message)
    Log.v("WhatIsFlow","${timePassed}ms: $message")
}

var lastPrintTime = System.currentTimeMillis()
fun printWithLastPrintTime(message: Any?) {
    val timePassed = System.currentTimeMillis() - lastPrintTime
    print("$timePassed: ")
    println(message)
    lastPrintTime = System.currentTimeMillis()
}

fun getStartTime(timeUntilFirstFlowEmission: Long) = System.currentTimeMillis() + timeUntilFirstFlowEmission