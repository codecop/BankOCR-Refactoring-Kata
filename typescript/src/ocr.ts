
const NUMERALS = [
    [' _  ',
     '| | ',
     '|_| ',
     '    '],
    ['    ',
     '  | ',
     '  | ',
     '    '],
    [' _  ',
     ' _| ',
     '|_  ',
     '    '],
    [' _  ',
     ' _| ',
     ' _| ',
     '    '],
    ['    ',
     '|_| ',
     '  | ',
     '    '],
    [' _  ',
     '|_  ',
     ' _| ',
     '    '],
    [' _  ',
     '|_  ',
     '|_| ',
     '    '],
    [' _  ',
     '  | ',
     '  | ',
     '    '],
    [' _  ',
     '|_| ',
     '|_| ',
     '    '],
    [' _  ',
     '|_| ',
     ' _| ',
     '    ']];

export class Ocr {

    public static parse(lines: string[]): string[] {
        const result: string[] = [];
        for (let i = 0; i < lines.length; i += 4) {
            let work: string[] = [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '];
            for (let pos = 0; pos < 9; ++pos) {
                work[pos] = '?';
                let got1 = false;
                for (let numeral = 0; numeral <= 9; ++numeral) {
                    let ok = true;
                    for (let row = 0; row < 4; ++row) {
                        for (let col = 0; col < 4; ++col) {
                            if (NUMERALS[numeral][row][col] !== lines[i + row][4 * pos + col])
                                ok = false;
                        }
                    }
                    if (ok) {
                        work[pos] = String.fromCharCode(numeral + '0'.charCodeAt(0));
                        got1 = true;
                        break;
                    }
                }
                if (!got1) {
                    work[10] = 'I';
                    work[11] = work[12] = 'L';
                }
            }
            result.push(work.join(''));
        }
        return result;
    }
}
