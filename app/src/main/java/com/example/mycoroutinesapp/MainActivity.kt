package com.example.mycoroutinesapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.mycoroutinesapp.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow
import kotlin.coroutines.coroutineContext


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    //val customScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playWithFlow()
    }


    private fun playWithFlow() {


        val mFlow = flow<Int> {
            for (i in 1..8) {
                emit(i)
                delay(2000)
            }
        }
        lifecycleScope.launch {
            mFlow.buffer().collect{
                delay(2000)
                Log.d(TAG, "Collect($it)")
            }
        }
    }


    companion object {
        private const val TAG = "Tamer"
    }

//    lateinit var job1: Job
//    lateinit var job2: Job
//    lateinit var job3: Job
//    lateinit var job4: Job
//    lateinit var job5: Job
//
//
//    private fun playWithCoroutine() {
//
//        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
//            Log.d(TAG, throwable.message.toString())
//        }
//
//        job1 = lifecycleScope.launch(exceptionHandler) {
//            delay(2000)
//            job2 = launch {
//                delay(2000)
//                Log.d(TAG, "I am job2")
//                job4 = launch {
//                    delay(2000)
//                    Log.d(TAG, "I am job4")
//                }
//                job5 = launch {
//                    delay(2000)
//                    Log.d(TAG, "I am job5")
//                }
//            }
//            job3 = launch {
//                delay(2000)
//                val result = 5 / 0
//                Log.d(TAG, "I am job3")
//            }
//            Log.d(TAG, "I am job1")
//        }
//    }


//    suspend fun repeatLogs1(): String {
//        delay(5000)
//        return "response 1"
//
//
////        delay(2500)
////        withContext(Dispatchers.Main) {
////            startActivity(Intent(this@MainActivity, SecondActivity::class.java))
////            finish()
////        }
//
//    }

//    suspend fun repeatLogs2(): String {
//        delay(3000)
//        return "response 2"
//    }


//    suspend fun fakeApiRequest() {
//        delay(2500)
//        Log.d(TAG, "fakeApiRequest")
//        Log.d(TAG, Thread.currentThread().name)
//        withContext(Dispatchers.Main) {
//            binding.txtView.text = "fake request"
//            Log.d(TAG, Thread.currentThread().name)
//        }
//    }


//
//    override fun onDestroy() {
//        super.onDestroy()
//        customScope.cancel()
//    }
}