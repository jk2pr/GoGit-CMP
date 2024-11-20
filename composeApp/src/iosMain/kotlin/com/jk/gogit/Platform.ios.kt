package com.jk.gogit

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.CoreGraphics.CGBitmapContextCreate
import platform.CoreGraphics.CGColorSpaceCreateDeviceRGB
import platform.CoreGraphics.CGContextDrawImage
import platform.CoreGraphics.CGImageAlphaInfo
import platform.CoreGraphics.CGImageGetHeight
import platform.CoreGraphics.CGImageGetWidth
import platform.CoreGraphics.CGRectMake
import platform.UIKit.UIDevice
import platform.UIKit.UIImage

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

@Composable
actual fun getIconResource(): Painter {
     val uiImage = UIImage.imageNamed("your_icon") ?: return BitmapPainter(ImageBitmap(1, 1)) // Fallback empty bitmap

    // Convert UIImage to ImageBitmap and wrap it in BitmapPainter
    return BitmapPainter(uiImage.toImageBitmap()!!)
}
@OptIn(ExperimentalForeignApi::class)
fun UIImage.toImageBitmap(): ImageBitmap? {
    val cgImage = this.CGImage ?: return null
    val width = CGImageGetWidth(cgImage).toInt()  // Get the width using CGImageGetWidth
    val height = CGImageGetHeight(cgImage).toInt()
    val colorSpace = CGColorSpaceCreateDeviceRGB()
    val bytesPerPixel = 4
    val bytesPerRow = bytesPerPixel * width
    val bitsPerComponent = 8

    // Allocate buffer for pixel data
    val pixelData = ByteArray(bytesPerRow * height)

    pixelData.usePinned { buffer ->
        val context = CGBitmapContextCreate(
            buffer.addressOf(0),
            width.toULong(),
            height.toULong(),
            bitsPerComponent.toULong(),
            bytesPerRow.toULong(),
            colorSpace,
            CGImageAlphaInfo.kCGImageAlphaPremultipliedLast.value or 12288u
        )

        // Draw the image into the context
        CGContextDrawImage(context, CGRectMake(0.0, 0.0, width.toDouble(), height.toDouble()), cgImage)
    }

    // Convert the pixel buffer to ImageBitmap
    return org.jetbrains.skia.Image.makeRaster(
        org.jetbrains.skia.ImageInfo.makeN32Premul(width, height),
        pixelData, bytesPerRow
    ).toComposeImageBitmap()
}