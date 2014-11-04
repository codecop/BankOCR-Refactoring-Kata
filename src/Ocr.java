import java.util.List;
import java.util.ArrayList;

public class Ocr {

    private static final char[][] DIGITS = new char[][] {
            " _       _   _       _   _   _   _   _  ".toCharArray(),
            "| |   |  _|  _| |_| |_  |_    | |_| |_| ".toCharArray(),
            "|_|   | |_   _|   |  _| |_|   | |_|  _| ".toCharArray(),
            "                                        ".toCharArray()
    };

    public static List<String> parse(String... lines) {
        final List<String> result = new ArrayList<String>();
        final char[] work = new char[13];

        int i,j,k,l,m;
        boolean got1, ok;
        for (i = 0; i < lines.length; i += 4) {
            work[9] = work[10] = work[11] = work[12] = ' ';
            for (j = 0; j < 9; ++j) {
                work[j] = '?';
                got1 = false;
                for (k = 0; k <= 9; ++k) {
                    ok = true;
                    for (l = 0; l < 4; ++l) {
                        for (m = 0; m < 4; ++m) {
                            if (DIGITS[l][4*k+m] != lines[i+l].charAt(4*j+m))
                                ok = false;
                        }
                    }
                    if (ok) {
                        work[j] = (char)(k + (int)'0');
                        got1 = true;
                        break;
                    }
                }
                if (!got1) {
                    work[10] = 'I';
                    work[11] = work[12] = 'L';
                }
            }
            result.add(new String(work));
        }
        return result;
    }
}
