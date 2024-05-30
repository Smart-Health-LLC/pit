package presentation.ui.no_internet

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import pit.composeapp.generated.resources.*
import presentation.component.Spacer_12dp
import presentation.component.Spacer_32dp

class NoInternetScreen : Screen {
    @Composable
    override fun Content() {
        NoInternetScreenContent()
    }
}

@Composable
fun NoInternetScreenContent() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .padding(bottom = 10.dp)
    ) {

        val some = rememberInfiniteTransition(label = "")
        val name by some.animateFloat(
            initialValue = 0f,
            targetValue = 53f,
            animationSpec = infiniteRepeatable(
                animation = tween(2700, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )

        val image: DrawableResource = when (name.toInt()) {
            0 -> Res.drawable.frame_00
            1 -> Res.drawable.frame_01
            2 -> Res.drawable.frame_02
            3 -> Res.drawable.frame_03
            4 -> Res.drawable.frame_04
            5 -> Res.drawable.frame_05
            6 -> Res.drawable.frame_06
            7 -> Res.drawable.frame_07
            8 -> Res.drawable.frame_08
            9 -> Res.drawable.frame_09
            10 -> Res.drawable.frame_10
            11 -> Res.drawable.frame_11
            12 -> Res.drawable.frame_12
            13 -> Res.drawable.frame_13
            14 -> Res.drawable.frame_14
            15 -> Res.drawable.frame_15
            16 -> Res.drawable.frame_16
            17 -> Res.drawable.frame_17
            18 -> Res.drawable.frame_18
            19 -> Res.drawable.frame_19
            20 -> Res.drawable.frame_20
            21 -> Res.drawable.frame_21
            22 -> Res.drawable.frame_22
            23 -> Res.drawable.frame_23
            24 -> Res.drawable.frame_24
            25 -> Res.drawable.frame_25
            26 -> Res.drawable.frame_26
            27 -> Res.drawable.frame_27
            28 -> Res.drawable.frame_28
            29 -> Res.drawable.frame_29
            30 -> Res.drawable.frame_30
            31 -> Res.drawable.frame_31
            32 -> Res.drawable.frame_32
            33 -> Res.drawable.frame_33
            34 -> Res.drawable.frame_34
            35 -> Res.drawable.frame_35
            36 -> Res.drawable.frame_36
            37 -> Res.drawable.frame_37
            38 -> Res.drawable.frame_38
            39 -> Res.drawable.frame_39
            40 -> Res.drawable.frame_40
            41 -> Res.drawable.frame_41
            42 -> Res.drawable.frame_42
            43 -> Res.drawable.frame_43
            44 -> Res.drawable.frame_44
            45 -> Res.drawable.frame_45
            46 -> Res.drawable.frame_46
            47 -> Res.drawable.frame_47
            48 -> Res.drawable.frame_48
            49 -> Res.drawable.frame_49
            50 -> Res.drawable.frame_50
            51 -> Res.drawable.frame_51
            52 -> Res.drawable.frame_52
            53 -> Res.drawable.frame_53
            else -> Res.drawable.frame_00
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    modifier = Modifier.size(270.dp).clip(RoundedCornerShape(10.dp)),
                )
                Spacer_32dp()
                Text(
                    "No internet connection",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer_12dp()
                Text(
                    "That sucks, because for now, there is no way to use the app offline. Yeah...",
                    textAlign = TextAlign.Center
                )
            }

            Button(modifier = Modifier.align(Alignment.BottomCenter), onClick = {

            }) {
                Text("Refresh")
            }
        }
    }
}