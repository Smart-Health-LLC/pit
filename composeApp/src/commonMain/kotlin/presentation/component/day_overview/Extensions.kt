package presentation.component.day_overview

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.unit.Density
import domain.model.Segment

/**
 * Data provider from child segment ui component to parent Layout component
 */
class UiSegmentsParentModifier(
    private val segmentUiData: SegmentUiData,
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?) = segmentUiData
}

/**
 * Custom modifier that is used to provide data from child segment us to parent Layout component
 */
fun Modifier.provideSegmentUiDataToParent(segmentUiData: SegmentUiData) =
    this.then(UiSegmentsParentModifier(segmentUiData))


fun Segment.toSegmentUiData() = SegmentUiData(
    segment = Segment(start, end),
    label = "${start.format(timeFormatter)} - ${end.format(timeFormatter)}",
    color = Color.Gray,
    type = SegmentType.BASE
)