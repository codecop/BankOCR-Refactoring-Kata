#include <setjmp.h> /* jmp_buf for mocka */
#include <stdarg.h> /* va_start for mocka */
#include <stddef.h> /* size_t for mocka */

#include <cmocka.h>

#include "Ocr.h"

static void test_parses_one_record_with_888888888(void** state)
{
    (void)state;                                                    /* unused */
    char lines[][36 + 1] = {" _   _   _   _   _   _   _   _   _  ", /* */
                            "|_| |_| |_| |_| |_| |_| |_| |_| |_| ", /* */
                            "|_| |_| |_| |_| |_| |_| |_| |_| |_| ", /* */
                            "                                    "};
    char result[][13 + 1] = {""};
    size_t resultLines = 0;

    parse(lines, 4, result, &resultLines);

    assert_string_equal("888888888    ", result[0]);
}

static void test_parses_two_records_with_888888888(void** state)
{
    (void)state;                                                    /* unused */
    char lines[][36 + 1] = {" _   _   _   _   _   _   _   _   _  ", /* */
                            "|_| |_| |_| |_| |_| |_| |_| |_| |_| ", /* */
                            "|_| |_| |_| |_| |_| |_| |_| |_| |_| ", /* */
                            "                                    ", /* */
                            " _   _   _   _   _   _   _   _   _  ", /* */
                            "|_| |_| |_| |_| |_| |_| |_| |_| |_| ", /* */
                            "|_| |_| |_| |_| |_| |_| |_| |_| |_| ", /* */
                            "                                    "};
    char result[][13 + 1] = {"", ""};
    size_t resultLines = 0;

    parse(lines, 8, result, &resultLines);

    assert_string_equal("888888888    ", result[0]);
    assert_string_equal("888888888    ", result[1]);
}

static void test_parses_one_record_with_123456790(void** state)
{
    (void)state;                                                    /* unused */
    char lines[][36 + 1] = {"     _   _       _   _   _   _   _  ", /* */
                            "  |  _|  _| |_| |_  |_    | |_| | | ", /* */
                            "  | |_   _|   |  _| |_|   |  _| |_| ", /* */
                            "                                    "};
    char result[1][13 + 1] = {""};
    size_t resultLines = 0;

    parse(lines, 4, result, &resultLines);

    assert_string_equal("123456790    ", result[0]);
}

static void test_parses_two_records(void** state)
{
    (void)state;                                                    /* unused */
    char lines[][36 + 1] = {"     _   _       _   _   _   _   _  ", /* */
                            "  |  _|  _| |_| |_  |_    | |_| | | ", /* */
                            "  | |_   _|   |  _| |_|   |  _| |_| ", /* */
                            "                                    ", /* */
                            " _       _   _   _       _   _   _  ", /* */
                            "|_| |_|   | |_|   | |_|  _|  _|  _| ", /* */
                            " _|   |   | |_|   |   | |_  |_  |_  ", /* */
                            "                                    "};
    char result[2][13 + 1] = {"", ""};
    size_t resultLines = 0;

    parse(lines, 8, result, &resultLines);

    assert_string_equal("123456790    ", result[0]);
    assert_string_equal("947874222    ", result[1]);
}

static void test_parses_illegal_digit(void** state)
{
    (void)state;                                                    /* unused */
    char lines[][36 + 1] = {"     _   _       _   _   _   _   _  ", /* */
                            "  |  _| |_|  _| |_  |_    | |_| | | ", /* */
                            "  | |_   _    |  _| |_|   |  _| |_| ", /* */
                            "                                    "};
    char result[1][13 + 1] = {""};
    size_t resultLines = 0;

    parse(lines, 4, result, &resultLines);

    assert_string_equal("12??56790 ILL", result[0]);
}

static void test_checksum_fail_returns_ERR(void** state)
{
    (void)state;                                                    /* unused */
    char lines[][36 + 1] = {" _   _       _   _           _   _  ", /* */
                            "|_  |_  |_|  _|   |   | |_| |_| |_  ", /* */
                            "|_| |_|   |  _|   |   |   |  _|  _| ", /* */
                            "                                    "};
    char result[1][13 + 1] = {""};
    size_t resultLines = 0;

    parse(lines, 4, result, &resultLines);

    assert_string_equal("664371495 ERR", result[0]);
}

int main(void)
{
    const struct CMUnitTest test_suite[] = {
        cmocka_unit_test(test_parses_one_record_with_888888888),  /* */
        cmocka_unit_test(test_parses_two_records_with_888888888), /* */
        cmocka_unit_test(test_parses_one_record_with_123456790),  /* */
        cmocka_unit_test(test_parses_two_records),                /* */
        cmocka_unit_test(test_parses_illegal_digit),              /* */
        // cmocka_unit_test(test_checksum_fail_returns_ERR),      /* */
    };

    return cmocka_run_group_tests(test_suite, NULL, NULL);
}
