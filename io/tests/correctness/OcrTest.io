doRelativeFile("../../io/Ocr.io")

OcrTest := UnitTest clone do(

    test_parses_one_record_with_888888888 := method(
        assertEquals(list("888888888    "),
                Ocr parse(list(" _   _   _   _   _   _   _   _   _  ",
                               "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                               "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                               "                                    ")))
    )

    test_parses_two_records_with_888888888 := method(
        assertEquals(list("888888888    ",
                          "888888888    "),
                Ocr parse(list(" _   _   _   _   _   _   _   _   _  ",
                               "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                               "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                               "                                    ",
                               " _   _   _   _   _   _   _   _   _  ",
                               "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                               "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                               "                                    ")))
    )

    test_parses_one_record_with_123456790 := method(
        assertEquals(list("123456790    "),
                Ocr parse(list("     _   _       _   _   _   _   _  ",
                               "  |  _|  _| |_| |_  |_    | |_| | | ",
                               "  | |_   _|   |  _| |_|   |  _| |_| ",
                               "                                    ")))
    )

    test_parses_two_records := method(
        assertEquals(list("123456790    ",
                          "947874222    "),
                Ocr parse(list("     _   _       _   _   _   _   _  ",
                               "  |  _|  _| |_| |_  |_    | |_| | | ",
                               "  | |_   _|   |  _| |_|   |  _| |_| ",
                               "                                    ",
                               " _       _   _   _       _   _   _  ",
                               "|_| |_|   | |_|   | |_|  _|  _|  _| ",
                               " _|   |   | |_|   |   | |_  |_  |_  ",
                               "                                    ")))
    )

    test_parses_illegal_digit := method(
        assertEquals(list("12??56790 ILL"),
                Ocr parse(list("     _   _       _   _   _   _   _  ",
                               "  |  _| |_|  _| |_  |_    | |_| | | ",
                               "  | |_   _    |  _| |_|   |  _| |_| ",
                               "                                    ")))
    )

   skip_test_checksum_fail_returns_ERR := method(
        assertEquals(list("664371495 ERR"),
                Ocr parse(list(" _   _       _   _           _   _  ",
                               "|_  |_  |_|  _|   |   | |_| |_| |_  ",
                               "|_| |_|   |  _|   |   |   |  _|  _| ",
                               "                                    ")))
   )
)
