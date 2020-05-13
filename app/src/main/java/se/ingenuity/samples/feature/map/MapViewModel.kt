package se.ingenuity.samples.feature.map

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import se.ingenuity.samples.feature.common.vo.EventWrapper

class MapViewModel : ViewModel() {
    private val event = MutableLiveData<MapEvent>()
    val wrappedEvent = event.map(::EventWrapper)

    @MainThread
    fun endRide(confirmed: Boolean) {
        if (confirmed) {
            // End ride
        } else {
            event.value = MapEvent.EndConfirmation
        }
    }
}
