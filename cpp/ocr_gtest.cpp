#include <gmock/gmock.h>
#include <gtest/gtest.h>

#include "Ocr.h"

using namespace std;

TEST(OcrTest, parses_one_record_with_888888888)
{
    string lines[] = {" _   _   _   _   _   _   _   _   _  ", /* */
                      "|_| |_| |_| |_| |_| |_| |_| |_| |_| ", /* */
                      "|_| |_| |_| |_| |_| |_| |_| |_| |_| ", /* */
                      "                                    "};
    vector<string> ocr = Ocr::parse(lines, 4);
    ASSERT_THAT(ocr, testing::ElementsAre("888888888    "));
}

TEST(OcrTest, parses_two_records_with_888888888)
{
    string lines[] = {" _   _   _   _   _   _   _   _   _  ", /* */
                      "|_| |_| |_| |_| |_| |_| |_| |_| |_| ", /* */
                      "|_| |_| |_| |_| |_| |_| |_| |_| |_| ", /* */
                      "                                    ", /* */
                      " _   _   _   _   _   _   _   _   _  ", /* */
                      "|_| |_| |_| |_| |_| |_| |_| |_| |_| ", /* */
                      "|_| |_| |_| |_| |_| |_| |_| |_| |_| ", /* */
                      "                                    "};
    vector<string> ocr = Ocr::parse(lines, 8);
    ASSERT_THAT(ocr, testing::ElementsAre("888888888    ", /* */
                                          "888888888    "));
}

TEST(OcrTest, parses_one_record_with_123456790)
{
    string lines[] = {"     _   _       _   _   _   _   _  ", /* */
                      "  |  _|  _| |_| |_  |_    | |_| | | ", /* */
                      "  | |_   _|   |  _| |_|   |  _| |_| ", /* */
                      "                                    "};
    vector<string> ocr = Ocr::parse(lines, 4);
    ASSERT_THAT(ocr, testing::ElementsAre("123456790    "));
}

TEST(OcrTest, parses_two_records)
{
    string lines[] = {"     _   _       _   _   _   _   _  ", /* */
                      "  |  _|  _| |_| |_  |_    | |_| | | ", /* */
                      "  | |_   _|   |  _| |_|   |  _| |_| ", /* */
                      "                                    ", /* */
                      " _       _   _   _       _   _   _  ", /* */
                      "|_| |_|   | |_|   | |_|  _|  _|  _| ", /* */
                      " _|   |   | |_|   |   | |_  |_  |_  ", /* */
                      "                                    "};
    vector<string> ocr = Ocr::parse(lines, 8);
    ASSERT_THAT(ocr, testing::ElementsAre("123456790    ", /* */
                                          "947874222    "));
}

TEST(OcrTest, parses_illegal_digit)
{
    string lines[] = {"     _   _       _   _   _   _   _  ", /* */
                      "  |  _| |_|  _| |_  |_    | |_| | | ", /* */
                      "  | |_   _    |  _| |_|   |  _| |_| ", /* */
                      "                                    "};
    vector<string> ocr = Ocr::parse(lines, 4);
    ASSERT_THAT(ocr, testing::ElementsAre("12??56790 ILL"));
}

// TEST(OcrTest, checksum_fail_returns_ERR)
// {
//     string lines[] = {" _   _       _   _           _   _  ", /* */
//                       "|_  |_  |_|  _|   |   | |_| |_| |_  ", /* */
//                       "|_| |_|   |  _|   |   |   |  _|  _| ", /* */
//                       "                                    "};
//     vector<string> ocr = Ocr::parse(lines, 4);
//     ASSERT_THAT(ocr, testing::ElementsAre("664371495 ERR"));
// }
