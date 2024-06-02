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
 *
 * But little bit sucks because not monospace :(
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


@Composable
fun FiraCode(): FontFamily {
    val firaCodeRegular = Font(
        resource = Res.font.FiraCode_Regular,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    )

    val firaCodeMedium = Font(
        resource = Res.font.FiraCode_Medium,
        weight = FontWeight.Medium,
        style = FontStyle.Normal
    )

    val firaCodeSemiBold = Font(
        resource = Res.font.FiraCode_SemiBold,
        weight = FontWeight.SemiBold,
        style = FontStyle.Normal
    )

    val firaCodeBold = Font(
        resource = Res.font.FiraCode_Bold,
        weight = FontWeight.Bold,
        style = FontStyle.Normal
    )

    val firaCodeLight = Font(
        resource = Res.font.FiraCode_Light,
        weight = FontWeight.Light,
        style = FontStyle.Normal
    )

    return FontFamily(
        firaCodeLight,
        firaCodeRegular,
        firaCodeMedium,
        firaCodeSemiBold,
        firaCodeBold
    )
}

