package edu.ucne.prestamospersonales.feature_personas.presentation.homePersona

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.prestamospersonales.data.model.Persona
import edu.ucne.prestamospersonales.feature_personas.domain.repository.PersonaRepository
import edu.ucne.prestamospersonales.feature_personas.domain.repository.PersonaRepositoryImpl
import edu.ucne.prestamospersonales.feature_personas.domain.uses_cases.Delete
import edu.ucne.prestamospersonales.feature_personas.domain.uses_cases.GetAll
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: PersonaRepositoryImpl
): ViewModel() {
    var personasList: List<Persona> = emptyList()

    init{
        repository.getAll().onEach { personas ->
            personasList = personas
        }.launchIn(viewModelScope)
    }

    fun delete(persona: Persona) {
        viewModelScope.launch {
            repository.delete(persona)
        }
    }

}