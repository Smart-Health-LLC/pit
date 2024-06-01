package presentation.component.day_overview

import androidx.compose.ui.graphics.Color
import domain.model.Segment

data class SegmentUiData(
    val segment: Segment,
    val color: Color,
    val label: String,
    val type: SegmentType
)

enum class SegmentType {
    BASE,
    REAL
}

