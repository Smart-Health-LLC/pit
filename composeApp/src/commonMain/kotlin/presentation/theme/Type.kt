package presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.*
import org.jetbrains.compose.resources.Font
import pit.composeapp.generated.resources.*

/**
 * Typography related theme stuff
 * https://m3.material.io/styles/typography/overview
 */

/**
 * Inter better to show time as its ":" properly aligned (vertically centered)
 */
@Composable
fun Inter(): FontFamily {
    val interRegular = Font(
        resource = Res.font.Inter_Regular,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    )

    val interBold = Font(
        resource = Res.font.Inter_Bold,
        weight = FontWeight.Bold,
        style = FontStyle.Normal
    )


    val interSemiBold = Font(
        resource = Res.font.Inter_SemiBold,
        weight = FontWeight.SemiBold,
        style = FontStyle.Normal
    )

    return FontFamily(
        interRegular,
        interSemiBold,
        interBold
    )
}