package design.compose

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun GameCard(
    modifier: Modifier = Modifier,
    cardName: String? = null
) {
    println("Loading card $cardName")
    Card(
        modifier = modifier.fillMaxSize(),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        KamelImage(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            resource = asyncPainterResource("https://de.marvelcdb.com/bundles/cards/$cardName.png"),
            contentDescription = cardName,
            animationSpec = tween(),
            onLoading = {
                Box(modifier = Modifier.fillMaxSize().background(Color.Gray))
            }
        )
    }
}