package presentation.component.day_overview

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import domain.model.Segment
import domain.model.SegmentReport
import kotlinx.coroutines.delay
import presentation.theme.Inter
import presentation.timeFormatter
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import kotlin.math.roundToInt


/**
 *  Single ui component representing record of real life day time segment
 *  (that is SegmentReport actually)
 */
@Composable
fun RealSegment(
    segmentReport: SegmentReport,
//    onSegmentClick: (segmentReport: SegmentReport) -> Unit,
    color: Color = MaterialTheme.colorScheme.primary,
    modifier: Modifier = Modifier,
) {
    var showText by remember {
        mutableStateOf(true)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 2.dp)
            .background(color, shape = RoundedCornerShape(4.dp))
            .padding(4.dp)
            .onSizeChanged {
                showText = it.height >= 100
            }
            .clickable {
//                onSegmentClick(segmentReport)
            }
    ) {
        val textColor = getContrastColor(color)
        if (showText) {
            Text(
                text = "${segmentReport.start.format(timeFormatter)} - ${
                    segmentReport.end.format(
                        timeFormatter
                    )
                }",
                color = textColor
            )

            if (segmentReport.note != null) {
                val maxSymbols = 80
                Text(
                    text = if (segmentReport.note.length > maxSymbols) segmentReport.note.take(
                        maxSymbols
                    ) + "…" else segmentReport.note,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )

            }
        }
    }
}


/**
 *  Single ui component representing base schedule segment (ideal time)
 */
@Composable
fun BaseSegment(
    segment: Segment,
//    onSegmentClick: (report: SegmentReport) -> Unit,
    color: Color = MaterialTheme.colorScheme.secondary,
    modifier: Modifier = Modifier,
) {
    var showText by remember {
        mutableStateOf(true)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 2.dp)
            .background(color, shape = RoundedCornerShape(4.dp))
            .padding(4.dp)
            .onSizeChanged {
                showText = it.height >= 50
            }
            .clickable {
//                onSegmentClick(segment.toSegmentReport())
            }
    ) {
        if (showText) {
            Text(
                text = "${segment.start.format(timeFormatter)} - ${
                    segment.end.format(
                        timeFormatter
                    )
                }",
                color = getContrastColor(color)
            )
        }
    }
}


// todo | create more flexible system base of N columns instead of hard coded real and base segments
// todo | because in future anyway needed to add calendar integration
/**
 * The component representing a day. This component is the main reason of my brain pain right
 * now and the shiny thing of the app
 */
@Composable
fun DayOverview(
    modifier: Modifier = Modifier,

    // Tap interaction with segments
    onSegmentClick: () -> Unit = {},
    onBaseSegmentClick: () -> Unit = {},

    // Today indicator
    todayIndicatorColor: Color = MaterialTheme.colorScheme.onSurface,
    showTodayIndicator: Boolean = true,

    columnPadding: Dp = 4.dp,
    gridColor: Color = MaterialTheme.colorScheme.outlineVariant,

    // Base schedule segments
    showBaseSchedule: Boolean = true,
    baseSegments: List<Segment>,
    baseSegmentComponent: @Composable (segment: Segment) -> Unit = {
        BaseSegment(segment = it)
    },

    // Real segments
    realSegments: List<SegmentReport>,
    realSegmentsColumnWidthInPercents: Float = if (showBaseSchedule) 80f else 100f,
    realSegmentComponent: @Composable (segmentReport: SegmentReport) -> Unit = {
        RealSegment(segmentReport = it)
    },

    // Hour indicators
    hourIndicatorComponent: @Composable (hour: LocalTime) -> Unit = {
        Text(
            text = it.format(timeFormatter),
            fontFamily = Inter(),
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 7.dp)
        )
    },
    initialHourHeight: Dp = 64.dp,
) {
    var hourHeight by remember {
        mutableStateOf(initialHourHeight)
    }
    val maxHourHeight = 128.dp
    val minHourHeight = 32.dp
    var hourColumnWidth by remember {
        mutableStateOf(0)
    }


    var currentTime by remember { mutableStateOf(LocalTime.now()) }
    if (showTodayIndicator) {
        val updateIntervalMinutes = 3L
        LaunchedEffect(updateIntervalMinutes) {
            while (true) {
                delay(updateIntervalMinutes * 60 * 1000) // Convert minutes to milliseconds
                currentTime = currentTime.plusMinutes(updateIntervalMinutes)
            }
        }
    }


    // ###################### DEFINE CONTENT ######################

    // ############## Hour labels
    var startTime = LocalTime.of(1, 0)
    val hourLabels = @Composable {
        repeat(23) { i ->
            hourIndicatorComponent(startTime.plusHours(i.toLong()))
        }
    }

    // ############## Real life segments
    // todo extract calculation to parent provider class
    val sortedSegmentsUiData = realSegments.sortedBy { it.start }

    if (realSegments.isNotEmpty()) {
        if (sortedSegmentsUiData.last().end < sortedSegmentsUiData.last().start) {
            sortedSegmentsUiData.last().end = LocalTime.MAX
        }
    }

    val realSegmentsComposablesList = @Composable {
        sortedSegmentsUiData.forEach { segmentUiData ->
            Box(
                modifier = Modifier
                    .provideSegmentUiDataToParent(segmentUiData)
                    .padding(start = 4.dp) // constant space between hour labels for now indicator circle
                    .padding(end = columnPadding / 2)
            ) {
                realSegmentComponent(segmentUiData)
            }
        }
    }


    // ############## Base Schedule segments

    val sortedBaseSegments = baseSegments.sortedBy { it.start }
    // that is fix situation when one segment belongs to this and next day simultaneously
    // todo in future check if the time edge equal to LocalTime.MAX

    if (baseSegments.isNotEmpty()) {
        if (sortedBaseSegments.last().end < sortedBaseSegments.last().start) {
            sortedBaseSegments.last().end = LocalTime.MAX
        }
    }

    val baseSegmentsComposablesList = @Composable {
        sortedBaseSegments.forEach { baseSegment ->
            Box(
                modifier = Modifier
                    .provideSegmentUiDataToParent(baseSegment.toSegmentReport())
                    .padding(start = columnPadding / 2)
            ) {
                baseSegmentComponent(baseSegment)
            }
        }
    }

    var horizontalGridLineStartX by remember { mutableFloatStateOf(0f) }
    var verticalGridLineOffsetX by remember { mutableFloatStateOf(0f) }


    val transformState = rememberTransformableState { zoomChange, _, _ ->
        val newHourHeight = hourHeight * zoomChange
        if (newHourHeight < maxHourHeight && newHourHeight > minHourHeight) {
            hourHeight = newHourHeight
        }
    }
    // maybe use SubcomposeLayout idk
    Layout(
        contents =
        listOf(
            hourLabels,
            realSegmentsComposablesList,
            baseSegmentsComposablesList,
        ),
        modifier = modifier
            .transformable(state = transformState)
            .verticalScroll(rememberScrollState())

            // Draw background grid
            .drawBehind {
                repeat(23) {
                    drawLine(
                        gridColor,
                        start = Offset(horizontalGridLineStartX, (it + 1) * hourHeight.toPx()),
                        end = Offset(size.width, (it + 1) * hourHeight.toPx()),
                        strokeWidth = 1.dp.toPx()
                    )
                }

                if (showBaseSchedule) {
                    drawLine(
                        gridColor,
                        start = Offset(verticalGridLineOffsetX, 0f),
                        end = Offset(verticalGridLineOffsetX, size.height),
                        strokeWidth = 1.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f), 0f)
                    )
                }
            }
            .drawWithContent {
                drawContent()

                if (showTodayIndicator) {
                    val minutesToNow =
                        ChronoUnit.MINUTES
                            .between(LocalTime.MIN, currentTime)
                            .toInt()
                    val nowIndicatorOffsetY = minutesToNow * (hourHeight / 60)


                    drawCircle(
                        center = Offset(hourColumnWidth.toFloat(), nowIndicatorOffsetY.toPx()),
                        color = todayIndicatorColor,
                        radius = 5.dp.toPx()
                    )

                    drawLine(
                        todayIndicatorColor,
                        start = Offset(horizontalGridLineStartX, nowIndicatorOffsetY.toPx()),
                        end = Offset(size.width, nowIndicatorOffsetY.toPx()),
                        strokeWidth = 1.dp.toPx(),
                    )
                }
            }
    ) { (hourLabelsMeasurables, realSegmentsMeasurables, baseSegmentsMeasurables),
        constraints ->

        val hourPlaceables = hourLabelsMeasurables.map { it.measure(constraints) }
        hourColumnWidth = (hourPlaceables.maxBy { it.width }).width

        val layoutHeight = hourHeight.roundToPx() * 24
        val realSegmentsColumnWidth =
            if (showBaseSchedule)
                (constraints.maxWidth * realSegmentsColumnWidthInPercents / 100 - hourColumnWidth).roundToInt()
            else constraints.maxWidth
        val baseSegmentsColumnWidth =
            if (showBaseSchedule) constraints.maxWidth - (constraints.maxWidth * realSegmentsColumnWidthInPercents / 100).roundToInt()
            else 0

        horizontalGridLineStartX = hourColumnWidth.toFloat()
        if (showBaseSchedule) {
            verticalGridLineOffsetX =
                (hourColumnWidth + realSegmentsColumnWidth).toFloat()
        }


        // ###################### MEASURE CONTENT ######################


        fun getSegmentPlaceable(
            segmentMeasurables: List<Measurable>,
            columnWidth: Int
        ): List<Pair<Placeable, SegmentReport>> {
            return segmentMeasurables.map { measurable ->
                val segmentReport = measurable.parentData as SegmentReport

                val segmentDurationMinutes =
                    ChronoUnit.MINUTES.between(
                        segmentReport.start,
                        segmentReport.end
                    )

                val segmentHeight =
                    ((segmentDurationMinutes / 60f) * hourHeight.toPx()).toInt()

                val segmentPlaceable = measurable.measure(
                    constraints.copy(
                        minHeight = segmentHeight,
                        maxHeight = segmentHeight,
                        maxWidth = columnWidth,
                        minWidth = columnWidth
                    )
                )

                Pair(segmentPlaceable, segmentReport)
            }
        }

        val baseSegmentsPlaceables =
            getSegmentPlaceable(baseSegmentsMeasurables, baseSegmentsColumnWidth)


        val realSegmentsPlaceables =
            getSegmentPlaceable(realSegmentsMeasurables, realSegmentsColumnWidth)


        // ###################### PLACE CONTENT ######################

        layout(constraints.maxWidth, layoutHeight) {


            val realSegmentsColumnOffsetX = hourColumnWidth
            val baseSegmentsColumnOffsetX =
                (constraints.maxWidth * realSegmentsColumnWidthInPercents / 100).toInt()

            // Place hour labels
            startTime = LocalTime.of(1, 0)
            hourPlaceables.forEachIndexed { i, placeable ->
                val segmentOffsetMinutes =
                    ChronoUnit.MINUTES.between(LocalTime.MIN, startTime.plusHours(i.toLong()))

                val segmentOffsetY =
                    ((segmentOffsetMinutes / 60f) * hourHeight.toPx() - placeable.height / 2).roundToInt()

                placeable.place(
                    0,
                    segmentOffsetY
                )
            }

            /**
             * Places segments column
             */
            fun placeSegments(segments: List<Pair<Placeable, SegmentReport>>, columnOffsetX: Int) {
                segments
                    .forEach { (placeable, segmentUiData) ->
                        val segmentOffsetMinutes =
                            ChronoUnit.MINUTES.between(LocalTime.MIN, segmentUiData.start)

                        val segmentOffsetY =
                            ((segmentOffsetMinutes / 60f) * hourHeight.toPx()).roundToInt()

                        placeable.place(
                            columnOffsetX,
                            segmentOffsetY
                        )
                    }
            }


            placeSegments(
                baseSegmentsPlaceables,
                baseSegmentsColumnOffsetX
            )

            placeSegments(
                realSegmentsPlaceables,
                realSegmentsColumnOffsetX
            )
        }
    }
}