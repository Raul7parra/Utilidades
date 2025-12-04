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
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.LISTADO_ASIGNATURAS) {
        composable(Routes.HOME) { HomeScreen(navController)}
        composable(Routes.LISTADO_TAREAS) {
            val tareasVm: TareasViewModel = viewModel()
            ListadoTareasScreen(navController, tareasVm) }
        composable(Routes.LISTADO_ASIGNATURAS) {
            val asignaturasVm: AsignaturasViewModel = viewModel()
            ListadoAsignaturasScreen(navController, vm = asignaturasVm)
        }
    }
}