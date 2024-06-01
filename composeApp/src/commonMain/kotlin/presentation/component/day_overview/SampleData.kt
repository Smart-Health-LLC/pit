package presentation.component.day_overview

import androidx.compose.ui.graphics.Color
import domain.model.Segment
import java.time.LocalTime


val sampleSegmentsUiData = listOf(
    SegmentUiData(
        label = "Google I/O Keynote",
        color = Color(0xFFAFBBF2),
        segment = Segment(
            start = LocalTime.of(21, 45),
            end = LocalTime.of(23, 20),
        ),
        type = SegmentType.REAL
    ),
    SegmentUiData(
        label = "Developer Keynote",
        color = Color(0xFFAFBBF2),
        segment = Segment(
            start = LocalTime.of(5, 50),
            end = LocalTime.of(7, 30),
        ),
        type = SegmentType.REAL
    ),
    SegmentUiData(
        label = "What's new in Android",
        color = Color(0xFF1B998B),
        segment = Segment(
            start = LocalTime.of(14, 15),
            end = LocalTime.of(14, 38),
        ),
        type = SegmentType.REAL
    ),
    SegmentUiData(
        label = "What's new in Machine Learning",
        color = Color(0xFFF4BFDB),
        segment = Segment(
            start = LocalTime.of(20, 0),
            end = LocalTime.of(20, 45),
        ),
        type = SegmentType.REAL
    ),
)



