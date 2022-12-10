package com.malinskiy.marathon.config.vendor.ios

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * screenshot [--type=<type>] [--display=<display>] [--mask=<policy>] <file or url>
 *             Saves a screenshot as a PNG to the specified file or url(use "-" for stdout).
 *             --type       Can be "png", "tiff", "bmp", "gif", "jpeg". Default is png.
 *
 *             --display    iOS: supports "internal" or "external". Default is "internal".
 *                          tvOS: supports only "external"
 *                          watchOS: supports only "internal"
 *
 *                          You may also specify a port by UUID
 *             --mask       For non-rectangular displays, handle the mask by policy:
 *                          ignored: The mask is ignored and the unmasked framebuffer is saved.
 *                          alpha: The mask is used as premultiplied alpha.
 *                          black: The mask is rendered black.
 */
data class ScreenshotConfiguration(
    @JsonProperty("enabled") val enabled: Boolean = true,
    @JsonProperty("type") val type: Type = Type.GIF,
    @JsonProperty("display") val display: Display = Display.INTERNAL,
    @JsonProperty("mask") val mask: Mask = Mask.BLACK,
)

enum class Type(val value: String) {
    @JsonProperty("png") PNG("png"),
    @JsonProperty("tiff") TIFF(("tiff")),
    @JsonProperty("bmp") BMP("bmp"),
    @JsonProperty("gif") GIF("gif"),
    @JsonProperty("jpeg") JPEG("jpeg"),
}
