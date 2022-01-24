#ifndef OCR_OCR_H
#define OCR_OCR_H

#include <string>
#include <vector>

class Ocr {
public:
    static std::vector<std::string> parse(std::string lines[], size_t numberLines);

private:
    static const char NUMERALS[10][4][5];
};

#endif // OCR_OCR_H
