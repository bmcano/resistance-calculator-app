package com.brandoncano.resistancecalculator.data

// TODO - Research various types of LEDs and their values
// voltage is in Volts (V)
// current is in mA
enum class LedType(val ledName: String, val voltage: Double, val current: Int, val display: String) {

    CUSTOM("Custom", 0.0, 0, "Custom"),

    INFRARED_940("Infrared: 940nm", 1.5, 50, "Infrared: 940nm; Vf = ; If = "),
    INFRARED_880("Infrared: 880nm", 1.7, 50, "Infrared: 880nm; Vf = ; If = "),

    RED_STANDARD("Red: Standard", 1.7, 10, "Red: Standard; Vf = ; If = "),
    RED_LOW_CURRENT("Red: Low current", 1.7, 2, "Red: Low current; Vf = ; If = "),
    RED_BRIGHT("Red: Bright", 2.0, 10, "Red: Bright; Vf = ; If = "),
    RED_SUPER_BRIGHT("Red: Super bright", 1.9, 20, "Red: Super bright; Vf = ; If = "),

    ORANGE_STANDARD("Orange: Standard", 2.1, 10, "Orange: Standard; Vf = ; If = "),
    YELLOW_STANDARD("Yellow: Standard", 2.1, 10, "Yellow: Standard; Vf = ; If = "),
    GREEN_STANDARD("Green: Standard", 2.2, 10, "Green: Standard; Vf = ; If = "),

    BLUE_GREEN("Blue green", 3.2, 20, "Blue green; Vf = ; If = "),
    SUPER_BLUE("Super blue", 3.6, 20, "Super blue; Vf = ; If = "),
    BLUE_HIGH_INTENSITY("Blue: High intensity", 4.5, 20, "Blue: High intensity; Vf = ; If = "),

    WHITE_COOL("White: Cool", 3.6, 20, "White: Cool: Vf = ; If = "),
    ;

    companion object {
        fun getDropDownList(): List<String> {
            return LedType.entries.map { it.display }
        }

        fun fromDisplay(display: String): LedType {
            return entries.firstOrNull { it.display == display } ?: CUSTOM
        }
    }
}
