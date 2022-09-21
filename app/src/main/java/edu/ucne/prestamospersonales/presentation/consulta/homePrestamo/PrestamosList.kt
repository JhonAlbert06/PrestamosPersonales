package edu.ucne.prestamospersonales.presentation.consulta.homePrestamo

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
import edu.ucne.prestamospersonales.data.models.Prestamo
import edu.ucne.prestamospersonales.presentation.consulta.homePrestamo.components.PrestamoItem


@Composable
fun PrestamosList(
    navController: NavController,
    viewModel: HomePrestamosViewModel = hiltViewModel()
){

    Scaffold(

        topBar = {
            HomeTopBar()
        },


        floatingActionButton = {
            HomeFab(
                onFacClicked = { navController.navigate(Screen.EditPrestamo.route) }
            )
        },

        content = { innerPadding ->
            HomeContent(
                modifier = Modifier.padding(innerPadding),
                onDeletePrestamo = {viewModel.delete(it)},
                onEditPrestamo = {
                    navController.navigate(
                        route = Screen.EditPrestamo.passId(it)
                    )
                },
                prestamos = viewModel.uiState.value.prestamos,
            )
        }
    )
}


@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    onDeletePrestamo:(prestamos: Prestamo) -> Unit,
    onEditPrestamo: (id: Int?) -> Unit,
    prestamos: List<Prestamo> = emptyList(),
) {

    Surface(
        color = MaterialTheme.colors.surface,
        modifier = modifier,
    ) {
        LazyColumn{
            items(prestamos){ prestamo ->
                PrestamoItem(
                    prestamo = prestamo,
                    onEditPrestamo = {onEditPrestamo},
                    onDeletePrestamo = {onDeletePrestamo})
            }
        }
    }
}


@Composable
fun HomeFab(
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
fun HomeTopBar(
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
