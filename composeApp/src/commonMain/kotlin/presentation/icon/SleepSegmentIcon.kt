package presentation.icon

import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp


private var _TimeAtackSvgrepoCom: ImageVector? = null

public val SleepSegmentIcon: ImageVector
    get() {
        if (_TimeAtackSvgrepoCom != null) {
            return _TimeAtackSvgrepoCom!!
        }
        _TimeAtackSvgrepoCom = ImageVector.Builder(
            name = "TimeAtackSvgrepoCom",
            defaultWidth = 800.dp,
            defaultHeight = 800.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF222222)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(12f, 21f)
                curveTo(16.9706f, 21f, 21f, 16.9706f, 21f, 12f)
                curveTo(21f, 7.0294f, 16.9706f, 3f, 12f, 3f)
                curveTo(7.0294f, 3f, 3f, 7.0294f, 3f, 12f)
                curveTo(3f, 16.9706f, 7.0294f, 21f, 12f, 21f)
                close()
                moveTo(12.0796f, 5.06765f)
                curveTo(12f, 5.1418f, 12f, 5.2612f, 12f, 5.5f)
                verticalLineTo(11.7113f)
                curveTo(12f, 11.8522f, 12f, 11.9226f, 11.9665f, 11.9807f)
                curveTo(11.933f, 12.0387f, 11.872f, 12.0739f, 11.75f, 12.1443f)
                lineTo(6.37083f, 15.25f)
                curveTo(6.164f, 15.3694f, 6.0606f, 15.4291f, 6.0362f, 15.5351f)
                curveTo(6.0118f, 15.6412f, 6.0756f, 15.7354f, 6.2031f, 15.9237f)
                curveTo(6.7958f, 16.7994f, 7.5805f, 17.5313f, 8.5f, 18.0622f)
                curveTo(9.5641f, 18.6766f, 10.7712f, 19f, 12f, 19f)
                curveTo(13.2288f, 19f, 14.4359f, 18.6766f, 15.5f, 18.0622f)
                curveTo(16.5641f, 17.4478f, 17.4478f, 16.5641f, 18.0622f, 15.5f)
                curveTo(18.6766f, 14.4359f, 19f, 13.2288f, 19f, 12f)
                curveTo(19f, 10.7712f, 18.6766f, 9.5641f, 18.0622f, 8.5f)
                curveTo(17.4478f, 7.4359f, 16.5641f, 6.5522f, 15.5f, 5.9378f)
                curveTo(14.5805f, 5.407f, 13.5543f, 5.0933f, 12.4996f, 5.0179f)
                curveTo(12.2727f, 5.0016f, 12.1593f, 4.9935f, 12.0796f, 5.0677f)
                close()
            }
        }.build()
        return _TimeAtackSvgrepoCom!!
    }

