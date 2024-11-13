package com.nazwa.internalstorageexample
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nazwa.internalstorageexample.databinding.ActivitySecoundBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecoundBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecoundBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
    override fun onResume() {
        super.onResume()
        loadData()
    }
    private fun loadData() {
        Thread {
            val input = openFileInput(MainActivity.FILE_NAME)
            input.use {
                var buffer = StringBuilder()
                var bytesRead = input.read()
                while (bytesRead != -1) {
                    buffer.append(bytesRead.toChar())
                    bytesRead = input.read()
                }
                runOnUiThread {
                    binding.tvOutputText.text = buffer.toString()
                }
            }
        }.start()
    }
}