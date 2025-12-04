package es.fpsumma.dam2.utilidades.ui.screens.asignaturas

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import es.fpsumma.dam2.utilidades.ui.viewmodel.AsignaturasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListadoAsignaturasScreen(
    navController: NavController,
    vm: AsignaturasViewModel,
    modifier: Modifier= Modifier)
{

    val asignaturasList by vm.asignaturas.collectAsState()

    var asignatura by rememberSaveable { mutableStateOf("") }
    var trimestre by rememberSaveable { mutableStateOf("") }
    var nota by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Listado de asignaturas") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxWidth().padding(innerPadding).padding(20.dp)) {
            Text("Asignatura")
            OutlinedTextField(
                value = asignatura,
                onValueChange = {asignatura = it},
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.size(10.dp))

            Row {
                FilterChip(
                    selected = trimestre == "1ºT",
                    onClick = {trimestre = "1ºT"},
                    label = { Text("1ºT") },
                    modifier = Modifier.padding(end = 5.dp)
                )

                FilterChip(
                    selected = trimestre == "2ºT",
                    onClick = {trimestre = "2ºT"},
                    label = { Text("2ºT") },
                    modifier = Modifier.padding(end = 5.dp)
                )

                FilterChip(
                    selected = trimestre == "3ºT",
                    onClick = {trimestre = "3ºT"},
                    label = { Text("3ºT") },
                )
            }

            Spacer(modifier = Modifier.size(10.dp))

            Text("Nota")

            OutlinedTextField(
                value = nota,
                onValueChange = {nota = it},
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.size(10.dp))

            Button(
                onClick = {
                    //Creamos esta variable porque la nota esta en Int y la tenemos que pasar a String
                    val notaInt = nota.toInt()
                    vm.addAsignatura(asignatura = asignatura, trimestre = trimestre, nota = notaInt)}
            ) {
                Text("Guardar")
            }

            LazyColumn(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                items(asignaturasList) { asignatura ->
                    Card(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                        Column(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(asignatura.asignatura)
                            Text("Trimestre: "+ asignatura.trimestre)
                            Text("Nota: "+ asignatura.nota)
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Eliminar asignatura"
                            )
                        }
                    }
                }
            }

        }
    }
}