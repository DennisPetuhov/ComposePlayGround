import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun BottomShadowColumn(content: @Composable () -> Unit) {
    val density = LocalDensity.current
    val bottomShadowShape = GenericShape { size, _ ->
        with(density) {
            moveTo(0f, size.height)
            lineTo(size.width, size.height)
            lineTo(size.width, size.height + 15.dp.toPx())
            lineTo(0f, size.height + 15.dp.toPx())
            close()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                10.dp,
                bottomShadowShape,
                clip = false,
                spotColor = MaterialTheme.colorScheme.onBackground,
                ambientColor = MaterialTheme.colorScheme.onBackground
            )
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomShadowColumn() {
    BottomShadowColumn {
        Text("Hello, World!")
    }
}