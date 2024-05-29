package presentation.icon

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp


private var _Undersleep: ImageVector? = null

public val UndersleepIcon: ImageVector
    get() {
        if (_Undersleep != null) {
            return _Undersleep!!
        }
        _Undersleep = ImageVector.Builder(
            name = "Undersleep",
            defaultWidth = 800.dp,
            defaultHeight = 800.dp,
            viewportWidth = 297f,
            viewportHeight = 297f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(0f, 96.482f)
                verticalLineToRelative(103.783f)
                curveToRelative(0f, 5.775f, 4.683f, 10.458f, 10.458f, 10.458f)
                reflectiveCurveToRelative(10.458f, -4.683f, 10.458f, -10.458f)
                verticalLineToRelative(-54.49f)
                curveToRelative(3.291f, 5.67f, 7.022f, 11.543f, 11.229f, 17.431f)
                curveToRelative(30.267f, 42.374f, 70.502f, 64.772f, 116.354f, 64.772f)
                curveToRelative(5.775f, 0f, 10.458f, -4.683f, 10.458f, -10.457f)
                curveToRelative(0f, -5.775f, -4.683f, -10.458f, -10.458f, -10.458f)
                curveToRelative(-39.187f, 0f, -72.493f, -18.686f, -98.992f, -55.535f)
                curveToRelative(-2.634f, -3.662f, -5.065f, -7.334f, -7.309f, -10.953f)
                curveToRelative(32.541f, 23.315f, 69.266f, 35.638f, 106.303f, 35.638f)
                curveToRelative(37.502f, 0f, 74.683f, -12.625f, 107.524f, -36.511f)
                curveToRelative(24.714f, -17.974f, 38.316f, -35.938f, 38.883f, -36.693f)
                curveToRelative(2.017f, -2.688f, 2.618f, -6.184f, 1.616f, -9.393f)
                curveToRelative(-1.002f, -3.209f, -3.486f, -5.74f, -6.676f, -6.804f)
                curveToRelative(-2.179f, -0.726f, -54.374f, -17.791f, -141.348f, -17.791f)
                curveToRelative(-86.974f, 0f, -139.171f, 17.065f, -141.35f, 17.791f)
                curveToRelative(-3.19f, 1.063f, -5.674f, 3.595f, -6.676f, 6.804f)
                curveTo(0.208f, 94.475f, 0.043f, 95.604f, 0f, 96.482f)
                close()
                moveTo(148.501f, 150.068f)
                curveToRelative(-31.876f, 0f, -57.865f, -25.602f, -58.531f, -57.32f)
                curveToRelative(12.797f, -1.258f, 27.03f, -2.211f, 42.588f, -2.607f)
                curveToRelative(-0.842f, 2.032f, -1.312f, 4.257f, -1.312f, 6.594f)
                curveToRelative(0f, 9.53f, 7.725f, 17.255f, 17.255f, 17.255f)
                curveToRelative(9.53f, 0f, 17.256f, -7.725f, 17.256f, -17.255f)
                curveToRelative(0f, -2.336f, -0.471f, -4.561f, -1.311f, -6.592f)
                curveToRelative(15.542f, 0.398f, 29.778f, 1.356f, 42.586f, 2.62f)
                curveTo(206.358f, 124.475f, 180.372f, 150.068f, 148.501f, 150.068f)
                close()
                moveTo(29.554f, 102.43f)
                curveToRelative(9.354f, -2.194f, 22.698f, -4.896f, 39.563f, -7.226f)
                curveToRelative(0.828f, 17.989f, 7.668f, 34.429f, 18.547f, 47.369f)
                curveToRelative(-11.567f, -5.001f, -22.86f, -11.464f, -33.829f, -19.382f)
                curveTo(43.597f, 115.802f, 35.422f, 108.352f, 29.554f, 102.43f)
                close()
                moveTo(243.167f, 123.191f)
                curveToRelative(-10.969f, 7.918f, -22.261f, 14.381f, -33.828f, 19.382f)
                curveToRelative(10.875f, -12.935f, 17.714f, -29.368f, 18.547f, -47.349f)
                curveToRelative(16.837f, 2.33f, 30.186f, 5.029f, 39.548f, 7.22f)
                curveTo(261.565f, 108.365f, 253.394f, 115.809f, 243.167f, 123.191f)
                close()
            }
        }.build()
        return _Undersleep!!
    }

