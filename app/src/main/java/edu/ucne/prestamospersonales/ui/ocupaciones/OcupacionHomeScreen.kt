package edu.ucne.prestamospersonales.ui.ocupaciones
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
import edu.ucne.prestamospersonales.data.local.models.Ocupacion
import edu.ucne.prestamospersonales.ui.components.Item


@Composable
fun OcupacionHomeScreen(
    navController: NavController,
    viewModel: OcupacionHomeViewModel = hiltViewModel()
){
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            OcupacionHomeTopBar()
        },
        floatingActionButton = {
            OcupacionHomeFab(
                onFacClicked = { navController.navigate(Screen.EditOcupacion.route) }
            )
        },
        content = { innerPadding ->
            OcupacionHomeContent(
                modifier = Modifier.padding(innerPadding),
                onDeleteOcupacion = { viewModel.onEvent(OcupacionHomeEvent.DeleteOcupacion(it)) },
                onEditOcupacion = {
                    navController.navigate(
                        route = Screen.EditOcupacion.passId(it)
                    )
                },
                ocupaciones = state.ocupaciones
            )
        }
    )
}

@Composable
fun OcupacionHomeContent(
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
                Item(
                    ocupacion = ocupacion ,
                    onEditOcupacion = {onEditOcupacion(ocupacion.ocupacionId)},
                    onDeleteOcupacion = { onDeleteOcupacion(ocupacion)}
                )
            }
        }
    }
}



@Composable
fun OcupacionHomeFab(
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
fun OcupacionHomeTopBar(
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
