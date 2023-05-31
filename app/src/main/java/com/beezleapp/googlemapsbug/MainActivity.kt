package com.beezleapp.googlemapsbug

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beezleapp.googlemapsbug.ui.theme.GoogleMapsBugTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.compose.*
import com.google.maps.android.compose.clustering.Clustering

val defaultLocation = LatLng(45.440140, 1.503809)

class MainActivity : ComponentActivity() {
    @OptIn(MapsComposeExperimentalApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoogleMapsBugTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GoogleMap(cameraPositionState = CameraPositionState(
                        position = CameraPosition.fromLatLngZoom(
                            defaultLocation, 18f
                        )
                    )) {
                        Marker(state = MarkerState(defaultLocation))
                        Clustering(items = listOf(ClusterItemCustom())) {
                            Box(
                                modifier = Modifier
                                    .requiredHeight(100.dp)
                                    .requiredWidth(100.dp)
                                    .background(
                                        Color.Blue.copy(alpha = 0.5f)
                                    )
                            )
                        }
                    }
                }
            }
        }
    }
}

class ClusterItemCustom : ClusterItem {
    override fun getPosition() = defaultLocation
    override fun getTitle(): String? {
        return null
    }

    override fun getSnippet(): String? {
        return null
    }
}