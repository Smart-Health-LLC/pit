package presentation.icon

import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp


private var _ArrowNextSmallSvgrepoCom: ImageVector? = null

public val ArrowRightIcon: ImageVector
    get() {
        if (_ArrowNextSmallSvgrepoCom != null) {
            return _ArrowNextSmallSvgrepoCom!!
        }
        _ArrowNextSmallSvgrepoCom = ImageVector.Builder(
            name = "ArrowNextSmallSvgrepoCom",
            defaultWidth = 800.dp,
            defaultHeight = 800.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF0F0F0F)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(9.71069f, 18.2929f)
                curveTo(10.1012f, 18.6834f, 10.7344f, 18.6834f, 11.1249f, 18.2929f)
                lineTo(16.0123f, 13.4006f)
                curveTo(16.7927f, 12.6195f, 16.7924f, 11.3537f, 16.0117f, 10.5729f)
                lineTo(11.1213f, 5.68254f)
                curveTo(10.7308f, 5.292f, 10.0976f, 5.292f, 9.7071f, 5.6825f)
                curveTo(9.3165f, 6.0731f, 9.3165f, 6.7062f, 9.7071f, 7.0968f)
                lineTo(13.8927f, 11.2824f)
                curveTo(14.2833f, 11.6729f, 14.2833f, 12.3061f, 13.8927f, 12.6966f)
                lineTo(9.71069f, 16.8787f)
                curveTo(9.3202f, 17.2692f, 9.3202f, 17.9023f, 9.7107f, 18.2929f)
                close()
            }
        }.build()
        return _ArrowNextSmallSvgrepoCom!!
    }

