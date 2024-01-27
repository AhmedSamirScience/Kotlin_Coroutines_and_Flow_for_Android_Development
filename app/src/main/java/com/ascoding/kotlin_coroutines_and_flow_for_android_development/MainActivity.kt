package com.ascoding.kotlin_coroutines_and_flow_for_android_development

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import java.math.BigInteger

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runBlocking {


        Log.d("WhatIsFlow", "--------------------------------------------------------------------------------------------------------------------------")

        //region factorial function that return result in one element
        Log.i("WhatIsFlow", "Factorial function returns one element: ")

        val result = calculateFactorialOf(5)
        Log.v("WhatIsFlow", "Result $result")
        //endregion

        Log.d("WhatIsFlow", "--------------------------------------------------------------------------------------------------------------------------")

        //region factorial function that return result in a list of elements (no flow - synchronous - Blocking Main UI Thread)
        Log.i("WhatIsFlow", "Factorial function returns list: (no flow - synchronous - Blocking Main UI Thread)")

        val startTimeCalculateFactorialOfBuildList = System.currentTimeMillis()
        calculateFactorialOfBuildList(5).forEach{
            printWithTimePassed(it, startTimeCalculateFactorialOfBuildList)
        }
        Log.v("WhatIsFlow", "Ready for work")
        //endregion

        Log.d("WhatIsFlow", "--------------------------------------------------------------------------------------------------------------------------")

        //region factorial function that return result in a list of elements ( data flow or streaming data - synchronous - Blocking Main UI Thread)
        Log.i("WhatIsFlow", "Factorial function returns list: ( data flow or streaming data - synchronous - Blocking Main UI Thread)")

        val startTimeCalculateFactorialOfSequence = System.currentTimeMillis()
        calculateFactorialOfSequence(5).forEach{
            printWithTimePassed(it, startTimeCalculateFactorialOfSequence)
        }
        Log.v("WhatIsFlow", "Ready for work")
        //endregion


        Log.d("WhatIsFlow", "--------------------------------------------------------------------------------------------------------------------------")

        //region factorial function that return result in a list of elements ( data flow or streaming data - asynchronous - Non Blocking Main *UI* Thread)
        Log.i("WhatIsFlow", "Factorial function returns list: ( data flow or streaming data - asynchronous - Non Blocking Main *UI* Thread)")


        val startTimeCalculateFactorialOfFlow = System.currentTimeMillis()
        launch{
            calculateFactorialOfFlow(5).collect(){
                printWithTimePassed(it, startTimeCalculateFactorialOfFlow)
            }
            Log.d("WhatIsFlow", "--------------------------------------------------------------------------------------------------------------------------")
        }
        Log.v("WhatIsFlow", "Ready for work")
        //endregion


        }

    }

    //region factorial function that return result in one element
    private fun calculateFactorialOf(number : Int) : BigInteger
    {
        var factorial = BigInteger.ONE
        for (i in 1..number)
        {
            Thread.sleep(1000)
            factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
        }
        return factorial
    }
    //endregion

    //region factorial function that return result in a list of elements (no flow - synchronous - Blocking Main UI Thread)
    private fun calculateFactorialOfBuildList(number : Int) : List<BigInteger> = buildList{
        var factorial = BigInteger.ONE
        for (i in 1..number)
        {
            Thread.sleep(1000)
            factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
            add(factorial)
        }
     }
    //endregion

    //region Factorial function that return result in a list of elements: ( data flow or streaming data - synchronous - Blocking Main *UI* Thread)
    private fun calculateFactorialOfSequence(number : Int) : Sequence<BigInteger> = sequence{
        var factorial = BigInteger.ONE
        for (i in 1..number)
        {
            Thread.sleep(1000)
            factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
            yield(factorial)
        }
    }
    //endregion

    //region Factorial function that return result in a list of elements: ( data flow or streaming data - asynchronous - Non Blocking Main *UI* Thread)
    private fun calculateFactorialOfFlow(number : Int) : Flow<BigInteger> = flow{
        var factorial = BigInteger.ONE
        for (i in 1..number)
        {
            Thread.sleep(1000)
            factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
            emit(factorial)
        }
    }
    //endregion

}