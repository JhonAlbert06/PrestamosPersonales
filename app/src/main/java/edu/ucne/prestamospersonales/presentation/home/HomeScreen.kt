package edu.ucne.prestamospersonales.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.prestamospersonales.R
import edu.ucne.prestamospersonales.domain.model.Ocupacion
import edu.ucne.prestamospersonales.presentation.Screen
import edu.ucne.prestamospersonales.presentation.home.components.OcupacionItem

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
){
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            HomeTopBar()
        },
        floatingActionButton = {
            HomeFab(
                onFacClicked = { navController.navigate(Screen.Edit.route) }
            )
        },
        content = { innerPadding ->
            HomeContent(
                modifier = Modifier.padding(innerPadding),
                onDeleteOcupacion = { viewModel.onEvent(HomeEvent.DeleteOcupacion(it)) },
                onEditOcupacion = {
                    navController.navigate(
                        route = Screen.Edit.passId(it)
                    )
                },
                ocupaciones = state.ocupaciones
            )
        }
    )
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    onDeleteOcupacion: (ocupacion: Ocupacion) -> Unit,
    onEditOcupacion: (id: Int?) -> Unit,
    ocupaciones: List<Ocupacion> = emptyList()
) {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = modifier,
    ) {
        LazyColumn{
            items(ocupaciones){ ocupacion ->
                OcupacionItem(
                    ocupacion = ocupacion ,
                    onEditOcupacion = {onEditOcupacion(ocupacion.OcupacionId)},
                    onDeleteOcupacion = { onDeleteOcupacion(ocupacion)}
                )
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
            contentDescription = stringResource(id = R.string.addOcupacion))
    }
}

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.Ocupaciones),
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        backgroundColor = MaterialTheme.colors.surface
    )
}
