package cz.rolfi.equiride

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.osmdroid.views.MapView
import org.osmdroid.config.Configuration
import android.content.Context
import androidx.compose.ui.viewinterop.AndroidView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(applicationContext, getSharedPreferences("osmdroid", Context.MODE_PRIVATE))
        setContent {
            EquiRideApp()
        }
    }
}

@Composable
fun EquiRideApp() {
    var selectedHorse by remember { mutableStateOf<String?>(null) }
    var rideStarted by remember { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            if (selectedHorse == null) {
                Text("Vyber koně:")
                HorseList(onSelect = { selectedHorse = it })
            } else {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Kůň: $selectedHorse", style = MaterialTheme.typography.h6)
                    Button(onClick = { selectedHorse = null }) {
                        Text("Změnit")
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = { rideStarted = !rideStarted }) {
                    Text(if (rideStarted) "Ukončit jízdu" else "Start jízdy")
                }
                Spacer(modifier = Modifier.height(12.dp))
                MapViewComposable()
                // Statistiky, trasa apod. můžeš rozšířit zde
            }
        }
    }
}

@Composable
fun HorseList(onSelect: (String) -> Unit) {
    val horses = listOf("Punťa", "Hnědák", "Blesk")
    Column {
        horses.forEach { horse
