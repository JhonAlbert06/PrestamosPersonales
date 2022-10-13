package edu.ucne.prestamospersonales.ui.articulos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.prestamospersonales.Screen
import edu.ucne.prestamospersonales.data.remote.dto.ArticulosResponseDto
import edu.ucne.prestamospersonales.ui.components.ArticuloItem
import edu.ucne.prestamospersonales.ui.components.Toast

@Composable
fun ArticuloHomeScreen(
    navController: NavController,
    viewModel: ArticuloHomeViewModel = hiltViewModel()
){

    Scaffold(

        topBar = {
            ArticuloHomeTopBar()
        },


        floatingActionButton = {
            ArticuloHomeFab(
                onFacClicked = { navController.navigate(Screen.EditPrestamo.route) }
            )
        },



        content = { innerPadding ->
            val uiState by viewModel.uiState.collectAsState()
            ArticuloHomeContent(
                viewModel = viewModel,
                modifier = Modifier.padding(innerPadding),
                onDeleteArticulo = {viewModel.onEvent(ArticuloHomeEvent.DeleteArticulo(it))},
                onEditArticulo = {
                    /*
                    navController.navigate(
                        route = Screen.EditPrestamo.passId(it)
                    )
                    */
                },
                articulos = uiState.articulos ,
            )
        }
    )
}


@Composable
fun ArticuloHomeContent(
    viewModel: ArticuloHomeViewModel,
    modifier: Modifier = Modifier,
    onDeleteArticulo:(articulo: ArticulosResponseDto) -> Unit,
    onEditArticulo: (id: Int?) -> Unit,
    articulos: List<ArticulosResponseDto> = emptyList(),
) {

    Surface(
        color = MaterialTheme.colors.surface,
        modifier = modifier,
    ) {

        val context = LocalContext.current

        if (viewModel.uiState.value.articulos.isEmpty()) {
            Toast(
                context = context,
                mgs = viewModel.uiState.value.error,
                isLoadig = viewModel.uiState.value.isLoading
            )
        }
        LazyColumn{
            items(articulos){ articulo ->
                ArticuloItem(
                    articulo = articulo,
                    onEditArticulo = {onEditArticulo},
                    onDeleteArticulo = {onDeleteArticulo})
            }
        }
    }
}



@Composable
fun ArticuloHomeFab(
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
            contentDescription = null)
    }
}

@Composable
fun ArticuloHomeTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = "Articulos",
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        backgroundColor = MaterialTheme.colors.surface
    )
}

