package net.schacher.mcc.design.preview

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import net.schacher.mcc.design.ThemedPreviews
import net.schacher.mcc.shared.design.compose.BottomSheetContainer
import net.schacher.mcc.shared.design.compose.CardInfo
import net.schacher.mcc.shared.design.compose.OptionsEntry
import net.schacher.mcc.shared.design.compose.OptionsGroup
import net.schacher.mcc.shared.design.compose.ShimmerBox
import net.schacher.mcc.shared.design.theme.DeckShape
import net.schacher.mcc.shared.design.theme.MccTheme
import net.schacher.mcc.shared.model.Aspect
import net.schacher.mcc.shared.model.Card
import net.schacher.mcc.shared.model.CardType
import net.schacher.mcc.shared.model.Faction


@ThemedPreviews
@Composable
fun OptionsGroupPreview() {
    MccTheme {
        OptionsGroup("Title") {
            OptionsEntry(label = "Entry", icon = { }) {
            }
        }
    }
}

@ThemedPreviews
@Composable
fun DefaultBottomSheetPreview() {
    MccTheme {
        BottomSheetContainer {}
    }
}

@ThemedPreviews
@Composable
fun ShimmerBoxPreview() {
    MccTheme {
        ShimmerBox(
            Modifier
                .size(48.dp)
                .clip(DeckShape),
        )
    }
}

@ThemedPreviews
@Composable
fun CardInfoPreview() {
    MccTheme {
        CardInfo(
            card = previewCard
        )
    }
}

private val previewCard = Card(
    code = "27078",
    name = "Preview Card",
    packName = "Preview Pack",
    packCode = "preview",
    text = "Spinnensinn — <b>Unterbrechung</b>: Sobald der [[Schurke]] einen [Angriff] gegen dich einleitet, ziehe 1 Karte.",
    boostText = "Teile dir diese Karte als verdeckte Begegnungskarte zu.",
    attackText = "<b>Erzwungene Reaktion</b>: Nachdem Assassine der Gilde einen Charakter angegriffen und besiegt hat, platziere 1 Bedrohung auf dem Hauptplan.",
    quote = "„Ich habe das Gefühl, dass wir nicht allein sind.“",
    faction = Faction.HERO,
    cost = 3,
    traits = "Brute.",
    type = CardType.HERO,
    aspect = Aspect.LEADERSHIP,
    position = 1
)