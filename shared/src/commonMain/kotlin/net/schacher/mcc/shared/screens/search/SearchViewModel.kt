package net.schacher.mcc.shared.screens.search

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.schacher.mcc.shared.model.Card
import net.schacher.mcc.shared.repositories.CardRepository

class SearchViewModel(private val cardRepository: CardRepository) : ViewModel() {

    private val _state = MutableStateFlow(UiState())

    val state = _state.asStateFlow()

    fun onSearch(query: String?) {
        if (query.isNullOrEmpty()) {
            _state.update {
                UiState()
            }
            return
        }

        _state.update {
            it.copy(loading = true)
        }

        viewModelScope.launch(Dispatchers.Default) {
            val filteredCards = cardRepository.cards
                .filter { it.name.lowercase().contains(query.lowercase()) }
                .distinctBy { it.name }

            _state.update {
                it.copy(
                    result = filteredCards,
                    loading = false
                )
            }
        }
    }
}

data class UiState(
    val loading: Boolean = false,
    val result: List<Card> = emptyList()
)