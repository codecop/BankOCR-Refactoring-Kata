#include <stdbool.h>
#include <string.h>

#include "Ocr.h"

const char NUMERALS[10][4][4 + 1] = {{" _  ",  /* */
                                      "| | ",  /* */
                                      "|_| ",  /* */
                                      "    "}, /* */
                                     {"    ",  /* */
                                      "  | ",  /* */
                                      "  | ",  /* */
                                      "    "}, /* */
                                     {" _  ",  /* */
                                      " _| ",  /* */
                                      "|_  ",  /* */
                                      "    "}, /* */
                                     {" _  ",  /* */
                                      " _| ",  /* */
                                      " _| ",  /* */
                                      "    "}, /* */
                                     {"    ",  /* */
                                      "|_| ",  /* */
                                      "  | ",  /* */
                                      "    "}, /* */
                                     {" _  ",  /* */
                                      "|_  ",  /* */
                                      " _| ",  /* */
                                      "    "}, /* */
                                     {" _  ",  /* */
                                      "|_  ",  /* */
                                      "|_| ",  /* */
                                      "    "}, /* */
                                     {" _  ",  /* */
                                      "  | ",  /* */
                                      "  | ",  /* */
                                      "    "}, /* */
                                     {" _  ",  /* */
                                      "|_| ",  /* */
                                      "|_| ",  /* */
                                      "    "}, /* */
                                     {" _  ",  /* */
                                      "|_| ",  /* */
                                      " _| ",  /* */
                                      "    "}};

void parse(char lines[][36 + 1], size_t numberLines, char result[][13 + 1], size_t* resultLines)
{
    *resultLines = 0;
    for (size_t i = 0; i < numberLines; i += 4) {
        char work[13 + 1] = "             ";
        for (size_t pos = 0; pos < 9; ++pos) {
            work[pos] = '?';
            bool got1 = false;
            for (size_t numeral = 0; numeral <= 9; ++numeral) {
                bool ok = true;
                for (size_t row = 0; row < 4; ++row) {
                    for (size_t col = 0; col < 4; ++col) {
                        if (NUMERALS[numeral][row][col] != lines[i + row][4 * pos + col]) {
                            ok = false;
                        }
                    }
                }
                if (ok) {
                    work[pos] = (char)(numeral + (int)'0');
                    got1 = true;
                    break;
                }
            }
            if (!got1) {
                work[10] = 'I';
                work[11] = work[12] = 'L';
            }
        }

        strcpy(result[(*resultLines)++], work);
    }
}
