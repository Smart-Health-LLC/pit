package presentation.ui.rate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.component.Spacer_16dp
import presentation.theme.slightlyDeEmphasizedAlpha
import presentation.theme.stronglyDeEmphasizedAlpha


/**
 * A scrollable container with the question's title, direction, and dynamic content.
 *
 * @param questionTitleText String resource to use for the question's title
 * @param questionDescriptionText String resource to use for the question's directions; the direction
 * UI will be omitted if null is passed
 * @param content Composable to display after the title and option directions
 */
@Composable
fun QuestionWrapper(
    questionTitleText: String,
    questionDescriptionText: String? = null,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
    ) {
        CustomQuestionTitle(questionTitleText)

        questionDescriptionText?.let {
            Spacer_16dp()
            QuestionDescription(it)
        }
        Spacer_16dp()

        content()
    }
}

@Composable
private fun CustomQuestionTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = slightlyDeEmphasizedAlpha),
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.inverseOnSurface,
                shape = MaterialTheme.shapes.small
            )
            .padding(vertical = 24.dp, horizontal = 16.dp)
    )
}

@Composable
private fun QuestionDescription(
    descriptionText: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = descriptionText,
        color = MaterialTheme.colorScheme.onSurface
            .copy(alpha = stronglyDeEmphasizedAlpha),
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    )
}
