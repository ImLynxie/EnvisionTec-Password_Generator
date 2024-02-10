import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import decrypt.Decryption
import io.kanro.compose.jetbrains.expui.control.Label
import io.kanro.compose.jetbrains.expui.control.PrimaryButton
import io.kanro.compose.jetbrains.expui.control.TextField
import io.kanro.compose.jetbrains.expui.theme.DarkTheme
import io.kanro.compose.jetbrains.expui.window.JBWindow

@Composable
@Preview
fun App() {
    var envisionPassword by remember { mutableStateOf("") }
    var decryptedPassword by remember { mutableStateOf("") }

    val componentWidth = 150.dp

    val decryption = Decryption()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column (horizontalAlignment = Alignment.End) {
                Row (horizontalArrangement = Arrangement.Center) {
                    Label("EnvisionTec Maintenance Code:")
                    Spacer(modifier = Modifier.width(10.dp))
                    TextField(
                        value = envisionPassword,
                        onValueChange = { envisionPassword = it },
                        modifier = Modifier.width(componentWidth)
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row (horizontalArrangement = Arrangement.Center) {

                    Label("Decrypted Maintenance Code:")
                    Spacer(modifier = Modifier.width(10.dp))
                    TextField(
                        value = decryptedPassword,
                        onValueChange = { },
                        readOnly = true,
                        modifier = Modifier.width(componentWidth)
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row {

                PrimaryButton(
                    onClick = {
                        decryptedPassword = decryption.generateEncryptedCode(envisionPassword)
                    },
                    modifier = Modifier.width(componentWidth)
                ) {
                    Label("Generate")
                }
            }
        }
    }
}

fun main() {

    application {
        JBWindow(
            title = "EnvisionTec Password Generator",
            showTitle = true,
            theme = DarkTheme,
            state = rememberWindowState(size = DpSize(450.dp, 200.dp)),
            icon = null,
            resizable = false,
            onCloseRequest = ::exitApplication
        ) {
            App()
        }
    }
}
