package at.weise.cpr

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.text.Html
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import java.util.*
import kotlin.concurrent.fixedRateTimer

class AlsActivity : FragmentActivity() {

    private var ticks = 0
    private var analyses = 0
    private var shocks = 0
    private var adrenalin = 0
    private var amiodaron = 0
    private lateinit var timer: Timer
    private lateinit var cprTime: TextView
    private lateinit var cprCycles: TextView
    private lateinit var cprAls: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_als);
        /* make timer available */
        cprTime = findViewById(R.id.timer)
        cprCycles = findViewById(R.id.cycles)
        cprAls = findViewById(R.id.als)
        /* better labels */
        findViewById<Button>(R.id.amiodaron).setText(Html.fromHtml("Amio.<sup>150mg</sup>"))
        findViewById<Button>(R.id.adrenalin).setText(Html.fromHtml("Adr.<sup>1mg</sup>"))
        /* start the counter */
        startTimer()
    }

    fun shock(view: View) {
        analyses++
        shocks++
        showStatistic()
        showALS()
    }

    fun noShock(view: View) {
        analyses++
        showStatistic()
        showALS()
    }

    fun adrenalinApplied(view: View) {
        adrenalin++
        showALS()
    }

    fun amiodaronApplied(view: View) {
        amiodaron++
        showALS()
    }

    private fun showStatistic() {
        cprCycles.visibility = View.VISIBLE
        var textShocks = "Schock"
        if (shocks > 1) {
            textShocks += "s"
        }
        runOnUiThread {
            cprCycles.text = "" + shocks + " " + textShocks + " (+" + (analyses - shocks) + ")"
        }
    }

    private fun showALS() {
        /* text */
        if (amiodaron > 0 || adrenalin > 0) {
            cprAls.visibility = View.VISIBLE
        }
        /* buttons */
        if (shocks >= 3 && amiodaron != 3) {
            findViewById<Button>(R.id.amiodaron).visibility = View.VISIBLE
        }
        if (amiodaron == 3) {
            findViewById<Button>(R.id.amiodaron).visibility = View.INVISIBLE
        }
        if (analyses > 0 && adrenalin != 10) {
            findViewById<Button>(R.id.adrenalin).visibility = View.VISIBLE
        }
        if (adrenalin == 10) {
            findViewById<Button>(R.id.adrenalin).visibility = View.INVISIBLE
        }
        runOnUiThread {
            cprAls.text = "" + adrenalin + "x Adr + " + amiodaron + "x Amio"
        }
    }

    private fun startTimer() {
        timer = fixedRateTimer(name = "CPR-Timer", initialDelay = 0, period = 1000) {
            ticks++
            runOnUiThread {
                cprTime.text = formatTime(ticks)
            }
        }
    }

    private fun formatTime(totalSeconds: Int): String {
        val minutes = Math.floorDiv(totalSeconds, 60)
        val seconds = totalSeconds - minutes * 60
        return "REA " + String.format("%02d", minutes) + ":" + String.format("%02d", seconds)
    }

    private fun stopTimer() {
        timer.cancel()
    }
}