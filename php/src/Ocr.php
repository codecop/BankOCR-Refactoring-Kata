<?php

namespace Ocr;

class Ocr {

    private const NUMERALS = [
        [" _  ",
         "| | ",
         "|_| ",
         "    "],
        ["    ",
         "  | ",
         "  | ",
         "    "],
        [" _  ",
         " _| ",
         "|_  ",
         "    "],
        [" _  ",
         " _| ",
         " _| ",
         "    "],
        ["    ",
         "|_| ",
         "  | ",
         "    "],
        [" _  ",
         "|_  ",
         " _| ",
         "    "],
        [" _  ",
         "|_  ",
         "|_| ",
         "    "],
        [" _  ",
         "  | ",
         "  | ",
         "    "],
        [" _  ",
         "|_| ",
         "|_| ",
         "    "],
        [" _  ",
         "|_| ",
         " _| ",
         "    "]
    ];

    public static function parse(...$lines): array {
        $result = [];
        for ($i = 0; $i < count($lines); $i += 4) {
            $work = "             ";
            for ($pos = 0; $pos < 9; ++$pos) {
                $work[$pos] = '?';
                $got1 = false;
                for ($numeral = 0; $numeral <= 9; ++$numeral) {
                    $ok = true;
                    for ($row = 0; $row < 4; ++$row) {
                        for ($col = 0; $col < 4; ++$col) {
                            if (self::NUMERALS[$numeral][$row][$col] !== $lines[$i + $row][4 * $pos + $col])
                                $ok = false;
                        }
                    }
                    if ($ok) {
                        $work[$pos] = chr($numeral + ord('0'));
                        $got1 = true;
                        break;
                    }
                }
                if (!$got1) {
                    $work[10] = 'I';
                    $work[11] = $work[12] = 'L';
                }
            }
            $result[] = $work;
        }
        return $result;
    }
}
