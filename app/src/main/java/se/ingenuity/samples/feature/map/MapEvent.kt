package se.ingenuity.samples.feature.map

sealed class MapEvent {
    object EndConfirmation : MapEvent()
}