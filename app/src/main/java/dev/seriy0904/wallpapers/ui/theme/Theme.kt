package dev.seriy0904.wallpapers.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = LightGray,
    primaryVariant = DarkGray,
    secondary = Teal200,
    surface = Color.Black,
    onBackground = Color.Black,
    onSecondary = Color.White,
    onPrimary = Color.White,
)

private val LightColorPalette = lightColors(
    primary = DarkGray,
    primaryVariant = LightGray,
    secondary = Teal200,
    surface = Color.White,
    onBackground = Color.White,
    onPrimary = Color.Black,
    onSecondary = Color.Black,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun WallpapersTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    val colors = if (darkTheme) {
        systemUiController.setSystemBarsColor(
            color = Color.Black
        )
        DarkColorPalette
    } else {
        systemUiController.setSystemBarsColor(
            color = Color.White
        )
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}