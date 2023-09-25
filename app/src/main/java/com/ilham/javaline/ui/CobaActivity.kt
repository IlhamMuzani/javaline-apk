package com.ilham.javaline.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ilham.javaline.R
import android.os.Handler
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_coba.*

class CobaActivity : AppCompatActivity() {

    private var totalSeconds = 0
    private var isTimerRunning = false
    private lateinit var handler: Handler
    private lateinit var timerTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coba)

        timerTextView = findViewById(R.id.timerTextView)
        handler = Handler()

        // Mulai timer ketika activity dibuat
        startTimer()

        stop.setOnClickListener {
            stopTimer()
        }
    }

    private fun startTimer() {
        isTimerRunning = true
        handler.post(object : Runnable {
            override fun run() {
                if (isTimerRunning) {
                    totalSeconds++
                    val hours = totalSeconds / 3600
                    val minutes = (totalSeconds % 3600) / 60
                    val seconds = totalSeconds % 60
                    val timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds)
                    timerTextView.text = timeString
                    handler.postDelayed(this, 1000) // Update setiap 1 detik
                }
            }
        })
    }

    // Method ini dipanggil ketika Anda ingin menghentikan timer
    private fun stopTimer() {
        isTimerRunning = false
    }

    override fun onPause() {
        super.onPause()
        // Hentikan timer saat activity di-pause
        stopTimer()
    }
}