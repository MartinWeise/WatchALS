package at.weise.cpr

import android.os.Bundle
import android.support.wearable.activity.WearableActivity

class AlsActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_als)
    }
}