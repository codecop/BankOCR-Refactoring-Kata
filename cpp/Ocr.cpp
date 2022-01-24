#include <stdbool.h>

#include "Ocr.h"

using namespace std;

const char Ocr::NUMERALS[10][4][5] = {{" _  ",  /* */
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

vector<string> Ocr::parse(string lines[])
{
    vector<string> result = {};
    for (int i = 0; i < lines->size(); i += 4) {
        char work[] = "             ";
        for (int pos = 0; pos < 9; ++pos) {
            work[pos] = '?';
            bool got1 = false;
            for (int numeral = 0; numeral <= 9; ++numeral) {
                bool ok = true;
                for (int row = 0; row < 4; ++row) {
                    for (int col = 0; col < 4; ++col) {
                        if (NUMERALS[numeral][row][col] != lines[i + row][4 * pos + col])
                            ok = false;
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
        result.push_back(work);
    }
    return result;
}
