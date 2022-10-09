package edu.ucne.prestamospersonales.ui.prestamo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.prestamospersonales.R
import edu.ucne.prestamospersonales.Screen
import edu.ucne.prestamospersonales.data.local.models.Persona
import edu.ucne.prestamospersonales.data.local.models.Prestamo
import edu.ucne.prestamospersonales.ui.components.ItemPrestamo

@Composable
fun PrestamoHomeScreen(
    navController: NavController,
    viewModel: PrestamoHomeViewModel = hiltViewModel()
){
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            PrestamoHomeTopBar()
        },
        floatingActionButton = {
            PrestamoHomeFab(
                onFacClicked = { navController.navigate(Screen.EditPrestamo.route) }
            )
        },
        content = { innerPadding ->
            PrestamoHomeContent(
                modifier = Modifier.padding(innerPadding),
                onDeletePrestamo = { viewModel.onEvent(PrestamoHomeEvent.DeletePrestamo(it)) },
                onEditPrestamo = {
                    navController.navigate(
                        route = Screen.EditPrestamo.passId(it)
                    )
                },
                prestamos = state.prestamos
            )
        }
    )
}

@Composable
fun PrestamoHomeContent(
    modifier: Modifier = Modifier,
    onDeletePrestamo: (prestamo : Prestamo) -> Unit,
    onEditPrestamo: (id: Int?) -> Unit,
    prestamos: List<Prestamo> = emptyList()
) {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = modifier,
    ) {
        LazyColumn{
            items(prestamos){ prestamo ->
                ItemPrestamo(
                    prestamo = prestamo ,
                    onEditPrestamo = {onEditPrestamo(prestamo.prestamoId)},
                    onDeletePrestamo = { onDeletePrestamo(prestamo)}
                )
            }
        }
    }
}

@Composable
fun PrestamoHomeFab(
    modifier: Modifier = Modifier,
    onFacClicked: () -> Unit = {}
) {
    FloatingActionButton(
        onClick = onFacClicked,
        modifier= modifier
            .height(52.dp)
            .widthIn(min = 52.dp),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = stringResource(id = R.string.addPrestamo))
    }
}

@Composable
fun PrestamoHomeTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.Prestamos),
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        backgroundColor = MaterialTheme.colors.surface
    )
}