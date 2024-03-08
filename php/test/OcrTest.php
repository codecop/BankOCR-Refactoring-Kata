<?php

namespace Tests;

use PHPUnit\Framework\TestCase;
use Ocr\Ocr;

class OcrTest extends TestCase {

    /**
     * @test
     */
    public function parses_one_record_with_888888888() {
        $expected = ["888888888    "];
        $actual = Ocr::parse(" _   _   _   _   _   _   _   _   _  ",
                             "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                             "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                             "                                    ");
        $this->assertEquals($expected, $actual);
    }

    /**
     * @test
     */
    public function parses_two_records_with_888888888() {
        $expected = ["888888888    ", 
                     "888888888    "];
        $actual = Ocr::parse(" _   _   _   _   _   _   _   _   _  ",
                             "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                             "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                             "                                    ",
                             " _   _   _   _   _   _   _   _   _  ",
                             "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                             "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                             "                                    ");
        $this->assertEquals($expected, $actual);
    }

    /**
     * @test
     */
    public function parses_one_record_with_123456790() {
        $expected = ["123456790    "];
        $actual = Ocr::parse("     _   _       _   _   _   _   _  ",
                             "  |  _|  _| |_| |_  |_    | |_| | | ",
                             "  | |_   _|   |  _| |_|   |  _| |_| ",
                             "                                    ");
        $this->assertEquals($expected, $actual);
    }

    /**
     * @test
     */
    public function parses_two_records() {
        $expected = ["123456790    ", 
                     "947874222    "];
        $actual = Ocr::parse("     _   _       _   _   _   _   _  ",
                             "  |  _|  _| |_| |_  |_    | |_| | | ",
                             "  | |_   _|   |  _| |_|   |  _| |_| ",
                             "                                    ",
                             " _       _   _   _       _   _   _  ",
                             "|_| |_|   | |_|   | |_|  _|  _|  _| ",
                             " _|   |   | |_|   |   | |_  |_  |_  ",
                             "                                    ");
        $this->assertEquals($expected, $actual);
    }

    /**
     * @test
     */
    public function parses_illegal_digit() {
        $expected = ["12??56790 ILL"];
        $actual = Ocr::parse("     _   _       _   _   _   _   _  ",
                             "  |  _| |_|  _| |_  |_    | |_| | | ",
                             "  | |_   _    |  _| |_|   |  _| |_| ",
                             "                                    ");
        $this->assertEquals($expected, $actual);
    }

    // /**
    //  * @test
    //  */
    // public function checksum_fail_returns_ERR() {
    //     $expected = ["664371495 ERR"];
    //     $actual = Ocr::parse(" _   _       _   _           _   _  ",
    //                          "|_  |_  |_|  _|   |   | |_| |_| |_  ",
    //                          "|_| |_|   |  _|   |   |   |  _|  _| ",
    //                          "                                    ");
    //     $this->assertEquals($expected, $actual);
    // }

}
