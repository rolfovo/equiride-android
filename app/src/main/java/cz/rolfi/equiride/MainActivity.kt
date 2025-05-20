package cz.rolfi.equiride

import android.os.Bundle
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.views.MapView
import org.osmdroid.config.Configuration

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
    var selectedHorse by remember { mutableStateOf<Horse?>(null) }
    var rideStarted by remember { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            if (selectedHorse == null) {
                Text("Vyber koně:")
                HorseList(onSelect = { selectedHorse = it })
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Kůň: ${selectedHorse?.name}", style = MaterialTheme.typography.h6)
                    Button(onClick = { selectedHorse = null }) {
                        Text("Změnit")
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = { rideStarted = !rideStarted },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (rideStarted) MaterialTheme.colors.error else MaterialTheme.colors.primary
                    )
                ) {
                    Text(if (rideStarted) "Ukončit jízdu" else "Start jízdy")
                }
                Spacer(modifier = Modifier.height(12.dp))
                MapViewComposable()
                // Zde budeš přidávat statistiky, trasu atd.
            }
        }
    }
}

@Composable
fun HorseList(onSelect: (Horse) -> Unit) {
    val horses = HorsesRepository.getHorses()
    Column {
        horses.forEach { horse ->
            Button(
                onClick = { onSelect(horse) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(horse.name)
            }
        }
    }
}

@Composable
fun MapViewComposable() {
    AndroidView(factory = { context ->
        MapView(context).apply {
            setMultiTouchControls(true)
        }
    }, modifier = Modifier
        .fillMaxWidth()
        .height(320.dp)
    )
}
