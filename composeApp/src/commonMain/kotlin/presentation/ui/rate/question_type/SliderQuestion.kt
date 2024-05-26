package presentation.ui.rate.question_type

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import presentation.ui.rate.QuestionWrapper

@Composable
fun SliderQuestion(
    questionTitleText: String,
    value: Float?,
    onValueChange: (Int) -> Unit,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 5,
    startTextResource: String,
    neutralTextResource: String,
    endTextResource: String,
) {
    var sliderPosition by remember {
        mutableStateOf(value ?: ((valueRange.endInclusive - valueRange.start) / 2))
    }
    QuestionWrapper(
        questionTitleText = questionTitleText,
    ) {
        Row {
            Slider(
                value = sliderPosition,
                onValueChange = {
                    sliderPosition = it
                    onValueChange(it.toInt())
                },
                valueRange = valueRange,
                steps = steps,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
        }
        Row {
            Text(
                text = startTextResource,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.8f)
            )
            Text(
                text = neutralTextResource,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.8f)
            )
            Text(
                text = endTextResource,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.8f)
            )
        }
    }
}
