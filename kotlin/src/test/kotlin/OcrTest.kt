import Ocr.parse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OcrTest {
    @Test
    fun `parses one record with 888888888`() {
        assertEquals(
            listOf("888888888    "),
            parse(
                " _   _   _   _   _   _   _   _   _  ",
                "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                "                                    "
            )
        )
    }

    @Test
    fun `parses two records with 888888888`() {
        assertEquals(
            listOf(
                "888888888    ",
                "888888888    "
            ),
            parse(
                " _   _   _   _   _   _   _   _   _  ",
                "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                "                                    ",
                " _   _   _   _   _   _   _   _   _  ",
                "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                "                                    "
            )
        )
    }

    @Test
    fun `parses one record with 123456790`() {
        assertEquals(
            listOf("123456790    "),
            parse(
                "     _   _       _   _   _   _   _  ",
                "  |  _|  _| |_| |_  |_    | |_| | | ",
                "  | |_   _|   |  _| |_|   |  _| |_| ",
                "                                    "
            )
        )
    }

    @Test
    fun `parses two records`() {
        assertEquals(
            listOf(
                "123456790    ",
                "947874222    "
            ),
            parse(
                "     _   _       _   _   _   _   _  ",
                "  |  _|  _| |_| |_  |_    | |_| | | ",
                "  | |_   _|   |  _| |_|   |  _| |_| ",
                "                                    ",
                " _       _   _   _       _   _   _  ",
                "|_| |_|   | |_|   | |_|  _|  _|  _| ",
                " _|   |   | |_|   |   | |_  |_  |_  ",
                "                                    "
            )
        )
    }

    @Test
    fun `parses illegal digit`() {
        assertEquals(
            listOf("12??56790 ILL"),
            parse(
                "     _   _       _   _   _   _   _  ",
                "  |  _| |_|  _| |_  |_    | |_| | | ",
                "  | |_   _    |  _| |_|   |  _| |_| ",
                "                                    "
            )
        )
    }

//    @Test
//    fun `checksum fail returns ERR`() {
//        assertEquals(
//            listOf("664371495 ERR"),
//            parse(
//                " _   _       _   _           _   _  ",
//                "|_  |_  |_|  _|   |   | |_| |_| |_  ",
//                "|_| |_|   |  _|   |   |   |  _|  _| ",
//                "                                    "
//            )
//        )
//    }
}
