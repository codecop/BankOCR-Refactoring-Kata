import java.util.List;
import java.util.ArrayList;

public class Ocr {

    private static final char[][][] DIGITS = new char[][][] {
        {" _  ".toCharArray(),
         "| | ".toCharArray(),
         "|_| ".toCharArray(),
         "    ".toCharArray()},
        {"    ".toCharArray(),
         "  | ".toCharArray(),
         "  | ".toCharArray(),
         "    ".toCharArray()},
        {" _  ".toCharArray(),
         " _| ".toCharArray(),
         "|_  ".toCharArray(),
         "    ".toCharArray()},
        {" _  ".toCharArray(),
         " _| ".toCharArray(),
         " _| ".toCharArray(),
         "    ".toCharArray()},
        {"    ".toCharArray(),
         "|_| ".toCharArray(),
         "  | ".toCharArray(),
         "    ".toCharArray()},
        {" _  ".toCharArray(),
         "|_  ".toCharArray(),
         " _| ".toCharArray(),
         "    ".toCharArray()},
        {" _  ".toCharArray(),
         "|_  ".toCharArray(),
         "|_| ".toCharArray(),
         "    ".toCharArray()},
        {" _  ".toCharArray(),
         "  | ".toCharArray(),
         "  | ".toCharArray(),
         "    ".toCharArray()},
        {" _  ".toCharArray(),
         "|_| ".toCharArray(),
         "|_| ".toCharArray(),
         "    ".toCharArray()},
        {" _  ".toCharArray(),
         "|_| ".toCharArray(),
         " _| ".toCharArray(),
         "    ".toCharArray()}};

    public static List<String> parse(String... lines) {
        final List<String> result = new ArrayList<String>();
        for (int i = 0; i < lines.length; i += 4) {
            final char[] work = new char[13];
            work[9] = work[10] = work[11] = work[12] = ' ';
            for (int j = 0; j < 9; ++j) {
                work[j] = '?';
                boolean got1 = false;
                for (int k = 0; k <= 9; ++k) {
                    boolean ok = true;
                    for (int l = 0; l < 4; ++l) {
                        for (int m = 0; m < 4; ++m) {
                            if (DIGITS[k][l][m] != lines[i+l].charAt(4*j+m))
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
