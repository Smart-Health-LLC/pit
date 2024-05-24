package presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.*
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import pit.composeapp.generated.resources.*

/**
 * Typography related theme stuff
 * https://m3.material.io/styles/typography/overview
 */

@Composable
@OptIn(ExperimentalResourceApi::class)
fun gilroy(): FontFamily {
    val gilroyRegular = Font(
        resource = Res.font.gilroy_regular,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    )

    val gilroyBold = Font(
        resource = Res.font.gilroy_bold,
        weight = FontWeight.Bold,
        style = FontStyle.Normal
    )


    val gilroySemiBold = Font(
        resource = Res.font.gilroy_semibold,
        weight = FontWeight.SemiBold,
        style = FontStyle.Normal
    )

    return FontFamily(
        gilroyRegular,
        gilroySemiBold,
        gilroyBold
    )
}