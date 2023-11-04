package net.schacher.mcc.shared.screens.featured

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.schacher.mcc.shared.design.compose.Deck
import net.schacher.mcc.shared.model.Deck
import net.schacher.mcc.shared.screens.featured.ListItem.DeckItem
import net.schacher.mcc.shared.screens.featured.ListItem.HeaderItem

@Composable
fun FeaturedScreen(
    featuredViewModel: FeaturedViewModel,
    onDeckClick: (Deck) -> Unit
) {
    val state by featuredViewModel.state.collectAsState()

    val entries = mutableListOf<ListItem>()
    state.decks.forEach { (date, decks) ->
        entries.add(HeaderItem("${date.dayOfMonth}. ${date.month}"))
        decks.forEach { deck ->
            entries.add(DeckItem(deck))
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 16.dp)
    ) {
        LazyColumn {
            items(entries.size) { index ->
                if (index == 0) {
                    Spacer(Modifier.statusBarsPadding().height(16.dp))
                }

                when (val entry = entries[index]) {
                    is HeaderItem -> {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                modifier = Modifier.padding(8.dp)
                                    .background(
                                        MaterialTheme.colors.primary,
                                        RoundedCornerShape(16.dp)
                                    )
                                    .padding(8.dp),
                                text = entry.header,
                                color = MaterialTheme.colors.onPrimary
                            )
                        }
                    }

                    is DeckItem -> {
                        Deck(entry.deck) {
                            onDeckClick(entry.deck)
                        }
                    }
                }


                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

private sealed interface ListItem {
    data class DeckItem(val deck: Deck) : ListItem
    data class HeaderItem(val header: String) : ListItem
}