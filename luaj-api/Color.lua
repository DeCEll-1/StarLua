--- * The Color class is used to encapsulate colors in the default
--- * sRGB color space or colors in arbitrary color spaces identified by a
--- * ColorSpace.  Every color has an implicit alpha value of 1.0 or
--- * an explicit one provided in the constructor.  The alpha value
--- * defines the transparency of a color and can be represented by
--- * a float value in the range 0.0&nbsp;-&nbsp;1.0 or 0&nbsp;-&nbsp;255.
--- * An alpha value of 1.0 or 255 means that the color is completely
--- * opaque and an alpha value of 0 or 0.0 means that the color is
--- * completely transparent.
--- * When constructing a Color with an explicit alpha or
--- * getting the color/alpha components of a Color, the color
--- * components are never premultiplied by the alpha component.
--- *
--- * The default color space for the Java 2D(tm) API is sRGB, a proposed
--- * standard RGB color space.  For further information on sRGB,
--- * see
--- * http://www.w3.org/pub/WWW/Graphics/Color/sRGB.html
--- * .
--- * @version     10 Feb 1997
--- * @author      Sami Shaio
--- * @author      Arthur van Hoff
--- * @see         ColorSpace
--- * @see         AlphaComposite
---@class Color
local Color = {}
--- * The color white.  In the default sRGB space.
---@return Color
function Color.white() return {} end

--- * The color white.  In the default sRGB space.
--- * @since 1.4
---@return Color
function Color.WHITE() return {} end

--- * The color light gray.  In the default sRGB space.
---@return Color
function Color.lightGray() return {} end

--- * The color light gray.  In the default sRGB space.
--- * @since 1.4
---@return Color
function Color.LIGHT_GRAY() return {} end

--- * The color gray.  In the default sRGB space.
---@return Color
function Color.gray() return {} end

--- * The color dark gray.  In the default sRGB space.
---@return Color
function Color.darkGray() return {} end

--- * The color dark gray.  In the default sRGB space.
--- * @since 1.4
---@return Color
function Color.DARK_GRAY() return {} end

--- * The color black.  In the default sRGB space.
---@return Color
function Color.black() return {} end

--- * The color red.  In the default sRGB space.
---@return Color
function Color.red() return {} end

--- * The color pink.  In the default sRGB space.
---@return Color
function Color.pink() return {} end

--- * The color pink.  In the default sRGB space.
--- * @since 1.4
---@return Color
function Color.PINK() return {} end

--- * The color orange.  In the default sRGB space.
---@return Color
function Color.orange() return {} end

--- * The color yellow.  In the default sRGB space.
---@return Color
function Color.yellow() return {} end

--- * The color green.  In the default sRGB space.
---@return Color
function Color.green() return {} end

--- * The color magenta.  In the default sRGB space.
---@return Color
function Color.magenta() return {} end

--- * The color cyan.  In the default sRGB space.
---@return Color
function Color.cyan() return {} end

--- * The color blue.  In the default sRGB space.
---@return Color
function Color.blue() return {} end

--- * Creates an opaque sRGB color with the specified red, green,
--- * and blue values in the range (0 - 255).
--- * The actual color used in rendering depends
--- * on finding the best match given the color space
--- * available for a given output device.
--- * Alpha is defaulted to 255.
--- * @throws IllegalArgumentException if r, g
--- *        or b are outside of the range
--- *        0 to 255, inclusive
--- * @param r the red component
--- * @param g the green component
--- * @param b the blue component
--- * @see #getRed
--- * @see #getGreen
--- * @see #getBlue
--- * @see #getRGB
---@param r integer
---@param g integer
---@param b integer
---@return Color
function Color.new(r, g, b) return {} end

--- * Creates an sRGB color with the specified red, green, blue, and alpha
--- * values in the range (0 - 255).
--- * @throws IllegalArgumentException if r, g,
--- *        b or a are outside of the range
--- *        0 to 255, inclusive
--- * @param r the red component
--- * @param g the green component
--- * @param b the blue component
--- * @param a the alpha component
--- * @see #getRed
--- * @see #getGreen
--- * @see #getBlue
--- * @see #getAlpha
--- * @see #getRGB
---@param r integer
---@param g integer
---@param b integer
---@param a integer
---@return Color
function Color.new(r, g, b, a) return {} end

--- * Creates an opaque sRGB color with the specified combined RGB value
--- * consisting of the red component in bits 16-23, the green component
--- * in bits 8-15, and the blue component in bits 0-7.  The actual color
--- * used in rendering depends on finding the best match given the
--- * color space available for a particular output device.  Alpha is
--- * defaulted to 255.
--- * @param rgb the combined RGB components
--- * @see java.awt.image.ColorModel#getRGBdefault
--- * @see #getRed
--- * @see #getGreen
--- * @see #getBlue
--- * @see #getRGB
---@param rgb integer
---@return Color
function Color.new(rgb) return {} end

--- * Creates an sRGB color with the specified combined RGBA value consisting
--- * of the alpha component in bits 24-31, the red component in bits 16-23,
--- * the green component in bits 8-15, and the blue component in bits 0-7.
--- * If the hasalpha argument is false, alpha
--- * is defaulted to 255.
--- * @param rgba the combined RGBA components
--- * @param hasalpha true if the alpha bits are valid;
--- *        false otherwise
--- * @see java.awt.image.ColorModel#getRGBdefault
--- * @see #getRed
--- * @see #getGreen
--- * @see #getBlue
--- * @see #getAlpha
--- * @see #getRGB
---@param rgba integer
---@param hasalpha boolean
---@return Color
function Color.new(rgba, hasalpha) return {} end

--- * Creates an opaque sRGB color with the specified red, green, and blue
--- * values in the range (0.0 - 1.0).  Alpha is defaulted to 1.0.  The
--- * actual color used in rendering depends on finding the best
--- * match given the color space available for a particular output
--- * device.
--- * @throws IllegalArgumentException if r, g
--- *        or b are outside of the range
--- *        0.0 to 1.0, inclusive
--- * @param r the red component
--- * @param g the green component
--- * @param b the blue component
--- * @see #getRed
--- * @see #getGreen
--- * @see #getBlue
--- * @see #getRGB
---@param r number
---@param g number
---@param b number
---@return Color
function Color.new(r, g, b) return {} end

--- * Creates an sRGB color with the specified red, green, blue, and
--- * alpha values in the range (0.0 - 1.0).  The actual color
--- * used in rendering depends on finding the best match given the
--- * color space available for a particular output device.
--- * @throws IllegalArgumentException if r, g
--- *        b or a are outside of the range
--- *        0.0 to 1.0, inclusive
--- * @param r the red component
--- * @param g the green component
--- * @param b the blue component
--- * @param a the alpha component
--- * @see #getRed
--- * @see #getGreen
--- * @see #getBlue
--- * @see #getAlpha
--- * @see #getRGB
---@param r number
---@param g number
---@param b number
---@param a number
---@return Color
function Color.new(r, g, b, a) return {} end

--- * Creates a color in the specified ColorSpace
--- * with the color components specified in the float
--- * array and the specified alpha.  The number of components is
--- * determined by the type of the ColorSpace.  For
--- * example, RGB requires 3 components, but CMYK requires 4
--- * components.
--- * @param cspace the ColorSpace to be used to
--- *                  interpret the components
--- * @param components an arbitrary number of color components
--- *                      that is compatible with the ColorSpace
--- * @param alpha alpha value
--- * @throws IllegalArgumentException if any of the values in the
--- *         components array or alpha is
--- *         outside of the range 0.0 to 1.0
--- * @see #getComponents
--- * @see #getColorComponents
---@param cspace ColorSpace
---@param components number[]
---@param alpha number
---@return Color
function Color.new(cspace, components, alpha) return {} end

--- * Returns the red component in the range 0-255 in the default sRGB
--- * space.
--- * @return the red component.
--- * @see #getRGB
---@return int
function Color:getRed() return {} end

--- * Returns the green component in the range 0-255 in the default sRGB
--- * space.
--- * @return the green component.
--- * @see #getRGB
---@return int
function Color:getGreen() return {} end

--- * Returns the blue component in the range 0-255 in the default sRGB
--- * space.
--- * @return the blue component.
--- * @see #getRGB
---@return int
function Color:getBlue() return {} end

--- * Returns the alpha component in the range 0-255.
--- * @return the alpha component.
--- * @see #getRGB
---@return int
function Color:getAlpha() return {} end

--- * Returns the RGB value representing the color in the default sRGB
--- * ColorModel.
--- * (Bits 24-31 are alpha, 16-23 are red, 8-15 are green, 0-7 are
--- * blue).
--- * @return the RGB value of the color in the default sRGB
--- *         ColorModel.
--- * @see java.awt.image.ColorModel#getRGBdefault
--- * @see #getRed
--- * @see #getGreen
--- * @see #getBlue
--- * @since 1.0
---@return int
function Color:getRGB() return {} end

--- * Returns the ColorSpace of this Color.
--- * @return this Color object's ColorSpace.
---@return ColorSpace
function Color:getColorSpace() return {} end

--- * Returns the transparency mode for this Color.  This is
--- * required to implement the Paint interface.
--- * @return this Color object's transparency mode.
--- * @see Paint
--- * @see Transparency
--- * @see #createContext
---@return int
function Color:getTransparency() return {} end

_G.Color = Color
