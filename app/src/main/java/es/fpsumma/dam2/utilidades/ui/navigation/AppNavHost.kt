package es.fpsumma.dam2.utilidades.ui.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import es.fpsumma.dam2.utilidades.ui.screens.asignaturas.ListadoAsignaturasScreen
import es.fpsumma.dam2.utilidades.ui.screens.home.HomeScreen
import es.fpsumma.dam2.utilidades.ui.screens.tareas.ListadoTareasScreen
import es.fpsumma.dam2.utilidades.ui.viewmodel.AsignaturasViewModel
import es.fpsumma.dam2.utilidades.ui.viewmodel.TareasViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AppNavHost(navController: NavHostController, tareasViewModel: TareasViewModel, asignaturasViewModel: AsignaturasViewModel) {
    NavHost(navController = navController, startDestination = Routes.LISTADO_ASIGNATURAS) {
        composable(Routes.HOME) { HomeScreen(navController)}
        composable(Routes.LISTADO_TAREAS) { ListadoTareasScreen(navController, vm = tareasViewModel) }
        composable(Routes.LISTADO_ASIGNATURAS) { ListadoAsignaturasScreen(navController, vm = asignaturasViewModel) }
    }
}