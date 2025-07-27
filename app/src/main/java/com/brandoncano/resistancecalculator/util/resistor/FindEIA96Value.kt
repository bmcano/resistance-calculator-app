package com.brandoncano.resistancecalculator.util.resistor

/**
 * Job: gets the proper EIA-96 base value or NaN if it doesn't exist
 *
 * Note: for reference how values are determined
 *  formula - E_{i} = 10^{i/96} x 10^{n},
 *  i - position in the series (0-95),
 *  n - represents the decade (multiplier)
 */
object FindEIA96Value {

    private val table = mapOf(
        "01" to 100.0, "02" to 102.0, "03" to 105.0, "04" to 107.0, "05" to 110.0, "06" to 113.0,
        "07" to 115.0, "08" to 118.0, "09" to 121.0, "10" to 124.0, "11" to 127.0, "12" to 130.0,
        "13" to 133.0, "14" to 137.0, "15" to 140.0, "16" to 143.0, "17" to 147.0, "18" to 150.0,
        "19" to 154.0, "20" to 158.0, "21" to 162.0, "22" to 165.0, "23" to 169.0, "24" to 174.0,
        "25" to 178.0, "26" to 182.0, "27" to 187.0, "28" to 191.0, "29" to 196.0, "30" to 200.0,
        "31" to 205.0, "32" to 210.0, "33" to 215.0, "34" to 221.0, "35" to 226.0, "36" to 232.0,
        "37" to 237.0, "38" to 243.0, "39" to 249.0, "40" to 255.0, "41" to 261.0, "42" to 267.0,
        "43" to 274.0, "44" to 280.0, "45" to 287.0, "46" to 294.0, "47" to 301.0, "48" to 309.0,
        "49" to 316.0, "50" to 324.0, "51" to 332.0, "52" to 340.0, "53" to 348.0, "54" to 357.0,
        "55" to 365.0, "56" to 374.0, "57" to 383.0, "58" to 392.0, "59" to 402.0, "60" to 412.0,
        "61" to 422.0, "62" to 432.0, "63" to 442.0, "64" to 453.0, "65" to 464.0, "66" to 475.0,
        "67" to 487.0, "68" to 499.0, "69" to 511.0, "70" to 523.0, "71" to 536.0, "72" to 549.0,
        "73" to 562.0, "74" to 576.0, "75" to 590.0, "76" to 604.0, "77" to 619.0, "78" to 634.0,
        "79" to 649.0, "80" to 665.0, "81" to 681.0, "82" to 698.0, "83" to 715.0, "84" to 732.0,
        "85" to 750.0, "86" to 768.0, "87" to 787.0, "88" to 806.0, "89" to 825.0, "90" to 845.0,
        "91" to 866.0, "92" to 887.0, "93" to 909.0, "94" to 931.0, "95" to 953.0, "96" to 976.0
    )

    fun execute(value: String): Double {
        return table[value] ?: Double.NaN
    }
}