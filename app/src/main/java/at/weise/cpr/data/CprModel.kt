package at.weise.cpr.data

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import at.weise.cpr.BR

class CprModel : BaseObservable() {

    private var time = 0

    @Bindable
    fun getTime(): Int {
        return time
    }

    fun tick() {
        time++
        notifyChange()
    }

}