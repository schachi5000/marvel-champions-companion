import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import net.schacher.mcc.shared.database.DatabaseDao
import net.schacher.mcc.shared.database.DatabaseDriverFactory

actual fun getPlatformName(): String = "Android"

@Composable fun MainView() {
    App(DatabaseDao(DatabaseDriverFactory(LocalContext.current)))

}