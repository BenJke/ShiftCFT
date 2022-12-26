package ve.develop.shiftcft

import android.app.Application

class ShiftApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Repository.initialize(this)
    }

}