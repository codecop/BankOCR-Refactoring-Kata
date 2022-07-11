object Ocr {
    private val NUMERALS = arrayOf(
        arrayOf(
            " _  ".toCharArray(),
            "| | ".toCharArray(),
            "|_| ".toCharArray(),
            "    ".toCharArray()
        ), arrayOf(
            "    ".toCharArray(),
            "  | ".toCharArray(),
            "  | ".toCharArray(),
            "    ".toCharArray()
        ), arrayOf(
            " _  ".toCharArray(),
            " _| ".toCharArray(),
            "|_  ".toCharArray(),
            "    ".toCharArray()
        ), arrayOf(
            " _  ".toCharArray(),
            " _| ".toCharArray(),
            " _| ".toCharArray(),
            "    ".toCharArray()
        ), arrayOf(
            "    ".toCharArray(),
            "|_| ".toCharArray(),
            "  | ".toCharArray(),
            "    ".toCharArray()
        ), arrayOf(
            " _  ".toCharArray(),
            "|_  ".toCharArray(),
            " _| ".toCharArray(),
            "    ".toCharArray()
        ), arrayOf(
            " _  ".toCharArray(),
            "|_  ".toCharArray(),
            "|_| ".toCharArray(),
            "    ".toCharArray()
        ), arrayOf(
            " _  ".toCharArray(),
            "  | ".toCharArray(),
            "  | ".toCharArray(),
            "    ".toCharArray()
        ), arrayOf(
            " _  ".toCharArray(),
            "|_| ".toCharArray(),
            "|_| ".toCharArray(),
            "    ".toCharArray()
        ), arrayOf(
            " _  ".toCharArray(),
            "|_| ".toCharArray(),
            " _| ".toCharArray(),
            "    ".toCharArray()
        )
    )

    fun parse(vararg lines: String): List<String> {
        val result: MutableList<String> = ArrayList()
        var i = 0
        while (i < lines.size) {
            val work = "             ".toCharArray()
            (0..8).forEach { pos ->
                work[pos] = '?'
                var got1 = false
                for (numeral in 0..9) {
                    var ok = true
                    (0..3).forEach { row ->
                        repeat((0..3)
                            .asSequence()
                            .filter { NUMERALS[numeral][row][it] != lines[i + row][4 * pos + it] }
                            .count()
                        ) { ok = false }
                    }
                    if (ok) {
                        work[pos] = (numeral + '0'.code).toChar()
                        got1 = true
                        break
                    }
                }
                if (!got1) {
                    work[10] = 'I'
                    work[12] = 'L'
                    work[11] = work[12]
                }
            }
            result.add(String(work))
            i += 4
        }
        return result
    }
}
