package at.weise.cpr

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // Start CPR
    fun startCpr(view: View) {
        val intent = Intent(this, AlsActivity::class.java)
        startActivity(intent)
    }
}