package at.weise.cpr

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import at.weise.cpr.data.CprModel
import at.weise.cpr.databinding.ActivityAlsBinding
import java.util.*
import kotlin.concurrent.fixedRateTimer

class AlsActivity : FragmentActivity() {

    private lateinit var binding: ActivityAlsBinding
    private lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* bind */
        binding = DataBindingUtil.setContentView(this, R.layout.activity_als)
        binding.cprModel = CprModel()
        /* start the counter */
        startTimer()
        /* render view */
        setContentView(R.layout.activity_als);
    }

    private fun startTimer() {
        timer = fixedRateTimer(name = "CPR-Timer", initialDelay = 0, period = 1000) {
        }
    }

    private fun formatTime(totalSeconds: Int): String {
        val minutes = Math.floorDiv(totalSeconds, 60)
        val seconds = totalSeconds - minutes * 60
        return "CPR " + String.format("%02d", minutes) + ":" + String.format("%02d", seconds)
    }

    private fun stopTimer() {
        timer.cancel()
    }
}