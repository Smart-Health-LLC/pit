package presentation.ui.auth.register

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import presentation.component.Spacer_12dp
import presentation.component.Spacer_24dp
import presentation.icon.ArrowBackIcon

class TermsOfServiceScreen : Screen {
    @Composable
    override fun Content() {
        TermsOfServiceContent()
    }
}

@Composable
fun TermsOfServiceContent() {
    val localNavigator = LocalNavigator.currentOrThrow

    Scaffold(topBar =
    {
        IconButton(onClick = {}) {
            Icon(
                imageVector = ArrowBackIcon,
                contentDescription = "back",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    localNavigator.pop()
                }
            )
        }
    }) {
        Column(modifier = Modifier.padding(it).verticalScroll(rememberScrollState())) {

            Spacer_12dp()
            Text("Terms of service", style = MaterialTheme.typography.headlineLarge)
            Spacer_24dp()
            Text(
                text = """
                    Until recently, the prevailing view assumed lorem ipsum was born as a nonsense text. “It's not Latin, though it looks like it, and it actually says nothing,” Before & After magazine answered a curious reader, “Its ‘words’ loosely approximate the frequency with which letters occur in English, which is why at a glance it looks pretty real.”

                    As Cicero would put it, “Um, not so fast.”

                    The placeholder text, beginning with the line “Lorem ipsum dolor sit amet, consectetur adipiscing elit”, looks like Latin because in its youth, centuries ago, it was Latin.

                    Richard McClintock, a Latin scholar from Hampden-Sydney College, is credited with discovering the source behind the ubiquitous filler text. In seeing a sample of lorem ipsum, his interest was piqued by consectetur—a genuine, albeit rare, Latin word. Consulting a Latin dictionary led McClintock to a passage from De Finibus Bonorum et Malorum (“On the Extremes of Good and Evil”), a first-century B.C. text from the Roman philosopher Cicero.

                    In particular, the garbled words of lorem ipsum bear an unmistakable resemblance to sections 1.10.32–33 of Cicero's work, with the most notable passage excerpted below:

                    “Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.”
                    A 1914 English translation by Harris Rackham reads:

                    “Nor is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but occasionally circumstances occur in which toil and pain can procure him some great pleasure.”
                    McClintock's eye for detail certainly helped narrow the whereabouts of lorem ipsum's origin, however, the “how and when” still remain something of a mystery, with competing theories and timelines.
                """.trimIndent()
            )
        }

    }
}