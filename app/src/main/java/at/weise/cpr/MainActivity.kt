package at.weise.cpr

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.View

class MainActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enables Always-on
        setAmbientEnabled()
    }

    // Start CPR
    fun startCpr(view: View) {
        val intent = Intent(this, AlsActivity::class.java)
        startActivity(intent)
    }
}