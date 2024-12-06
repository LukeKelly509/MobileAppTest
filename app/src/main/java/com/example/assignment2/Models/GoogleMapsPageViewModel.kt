package com.example.assignment2.Models

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

class GoogleMapsPageViewModel {
    @Composable
    fun googleMap() {
        val cameraPos = rememberCameraPositionState {
            position = CameraPosition(LatLng(52.6753966, -8.6492355), 17f, 0f, 0f)
        }
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraPos,
        ) {
            Marker(
                state = MarkerState(position = LatLng(52.6753966, -8.6492355)),
                title = "TUS College",
                snippet = "TUS College Location"
            )
        }
    }
}