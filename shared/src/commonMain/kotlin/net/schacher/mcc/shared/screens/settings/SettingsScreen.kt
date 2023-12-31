package net.schacher.mcc.shared.screens.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import net.schacher.mcc.shared.design.compose.OptionsEntry
import net.schacher.mcc.shared.design.compose.OptionsGroup
import org.koin.compose.koinInject

@Composable
fun SettingsScreen(settingsViewModel: SettingsViewModel = koinInject()) {
    val state by settingsViewModel.state.collectAsState()

    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1000
            }
        )
    )

    Column(
        modifier = Modifier.fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        OptionsGroup("Datenbank") {
            OptionsEntry(label = "Sync with MarvelCDB",
                imageVector = Icons.Rounded.Refresh,
                onClick = {
                    settingsViewModel.onSyncClick()
                })

            OptionsEntry(
                label = "Alle Einträge löschen",
                imageVector = Icons.Rounded.Delete,
                onClick = {
                    settingsViewModel.onWipeDatabaseClick()
                })
        }

        Spacer(Modifier.size(16.dp))

        OptionsGroup("Einträge") {
            AnimatedVisibility(state.syncInProgress) {
                OptionsEntry(
                    label = "Aktualisieren Datenbank",
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.Refresh,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .rotate(angle)
                                .size(18.dp)
                        )
                    }
                )
            }

            OptionsEntry(
                label = "${state.cardCount} Karten",
                iconResource = "ic_cards.xml"
            )

            OptionsEntry(
                label = "${state.deckCount} Decks",
                iconResource = "ic_deck.xml"
            )
        }

        Spacer(Modifier.size(16.dp))

        OptionsGroup("Debug") {
            OptionsEntry(
                label = "Import My Public Decks",
                imageVector = Icons.Rounded.Add,
                onClick = {
                    settingsViewModel.addPublicDecksById(
                        listOf(
                            "511044",
                            "533658",
                            "533636",
                            "511177",
                            "519524",
                            "511180",
                            "511046",
                            "516826",
                            "516215",
                            "516215",
                            "516216",
                            "511045"
                        )
                    )
                }
            )
        }
    }
}