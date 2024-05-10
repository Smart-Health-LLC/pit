package presentation.component

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator

@Composable
fun MainNavigationRail(
    modifier: Modifier = Modifier,
    tabNavigator: TabNavigator,
    navRailItems: List<Tab>,
) {
    NavigationRail(
        modifier = modifier.fillMaxHeight().alpha(0.95F),
        containerColor = MaterialTheme.colorScheme.surface,
        header = {
            /*Icon(
                modifier = Modifier.size(42.dp),
                imageVector = Icons.Default.AccountBox,
                // painter = painterResource("n_logo.png"),
                contentDescription = "Logo",
            )*/
        },
        contentColor = MaterialTheme.colorScheme.onSurface,
    ) {
        navRailItems.forEach { item ->
            val isSelected = tabNavigator.current == item
            NavigationRailItem(
                modifier = if (item.options.index.toInt() == 4) {
//                    Modifier.weight(1f)
                    Modifier.padding(vertical = 12.dp)
                } else {
                    Modifier.padding(vertical = 12.dp)
                },
                icon = {
                    item.options.icon?.let {
                        Icon(
                            painter = if (isSelected) {
                                FilledIcon(item)
                            } else {
                                it
                            },
                            contentDescription = item.options.title,
                        )
                    }
                },
                label = { Text(text = item.options.title) },
                alwaysShowLabel = true,
                selected = tabNavigator.current == item,
                onClick = { tabNavigator.current = item },
            )
        }
    }
}
