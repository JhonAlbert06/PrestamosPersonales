package edu.ucne.prestamospersonales.ui.persona

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
import edu.ucne.prestamospersonales.data.models.Persona
import edu.ucne.prestamospersonales.ui.components.ItemPersona

@Composable
fun PersonaHomeScreen(
    navController: NavController,
    viewModel: PersonaHomeViewModel = hiltViewModel()
){
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            PersonaHomeTopBar()
        },
        floatingActionButton = {
            PersonaHomeFab(
                onFacClicked = { navController.navigate(Screen.EditPersona.route) }
            )
        },
        content = { innerPadding ->
            PersonaHomeContent(
                modifier = Modifier.padding(innerPadding),
                onDeletePersona = { viewModel.onEvent(PersonaHomeEvent.DeletePersona(it)) },
                onEditPersona = {
                    navController.navigate(
                        route = Screen.EditPersona.passId(it)
                    )
                },
                personas = state.personas
            )
        }
    )
}

@Composable
fun PersonaHomeContent(
    modifier: Modifier = Modifier,
    onDeletePersona: (persona: Persona) -> Unit,
    onEditPersona: (id: Int?) -> Unit,
    personas: List<Persona> = emptyList()
) {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = modifier,
    ) {
        LazyColumn{
            items(personas){ persona ->
                ItemPersona(
                    persona = persona ,
                    onEditPersona = {onEditPersona(persona.personaId)},
                    onDeletePersona = { onDeletePersona(persona)}
                )
            }
        }
    }
}

@Composable
fun PersonaHomeFab(
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
            contentDescription = stringResource(id = R.string.addPersona))
    }
}

@Composable
fun PersonaHomeTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.Personas),
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        backgroundColor = MaterialTheme.colors.surface
    )
}