package presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.lyricist.strings

@Composable
fun UsernameTextField(
    modifier: Modifier = Modifier,
    value: String = "",
) {
    CustomTextField(
        value = value,
        label = strings.username,
        modifier = modifier,
        onValueChange = {},
        leadingIcon = { Icon(Icons.Outlined.Person, "User") },
    )
}
