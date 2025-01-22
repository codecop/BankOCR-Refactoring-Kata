Ocr := Object clone do(

    numerals := list(
       list(" _  ",
            "| | ",
            "|_| ",
            "    "),
       list("    ",
            "  | ",
            "  | ",
            "    "),
       list(" _  ",
            " _| ",
            "|_  ",
            "    "),
       list(" _  ",
            " _| ",
            " _| ",
            "    "),
       list("    ",
            "|_| ",
            "  | ",
            "    "),
       list(" _  ",
            "|_  ",
            " _| ",
            "    "),
       list(" _  ",
            "|_  ",
            "|_| ",
            "    "),
       list(" _  ",
            "  | ",
            "  | ",
            "    "),
       list(" _  ",
            "|_| ",
            "|_| ",
            "    "),
       list(" _  ",
            "|_| ",
            " _| ",
            "    "))

    parse := method(lines,
        result := list()
        for(i, 0, lines size - 1, 4,
            work := "             " asList
            for(pos, 0, 8,
                work atPut(pos, "?")
                got1 := false
                for(numeral, 0, 9,
                    ok := true
                    for(row, 0, 3,
                        for(col, 0, 3,
                            if (numerals at(numeral) at(row) at(col) != lines at(i+row) at(4*pos+col),
                                ok := false
                            )
                        )
                    )
                    if (ok,
                        work atPut(pos, (numeral + "0" at(0)) asCharacter )
                        got1 := true
                        break
                    )
                )
                if (got1 not,
                    work atPut(10, "I")
                    work atPut(11, "L")
                    work atPut(12, "L")
                )
            )
            result append(work join)
        )
        result
    )

)
