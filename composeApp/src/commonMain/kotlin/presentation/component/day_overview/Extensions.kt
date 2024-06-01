package presentation.component.day_overview

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.unit.Density
import domain.model.Segment
import domain.model.SegmentReport
import java.time.LocalDate

/**
 * Data provider from child segment ui component to parent Layout component
 */
class UiSegmentsParentModifier(
    private val segmentReport: SegmentReport,
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?) = segmentReport
}

/**
 * Custom modifier that is used to provide data from child segment us to parent Layout component
 */
fun Modifier.provideSegmentUiDataToParent(segmentReport: SegmentReport) =
    this.then(UiSegmentsParentModifier(segmentReport))


fun Segment.toSegmentReport() = SegmentReport(
    start = start,
    end = end,
    // todo remove that
    wakeUpEaseLevel = 0,
    fallAsleepEaseLevel = 0,
    day = LocalDate.of(2021, 1, 2)
)