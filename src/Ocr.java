import java.util.List;
import java.util.ArrayList;

public class Ocr {

    private static final char[][][] NUMERALS = new char[][][] {
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
            final char[] work = "             ".toCharArray();
            for (int pos = 0; pos < 9; ++pos) {
                work[pos] = '?';
                boolean got1 = false;
                for (int numeral = 0; numeral <= 9; ++numeral) {
                    boolean ok = true;
                    for (int row = 0; row < 4; ++row) {
                        for (int col = 0; col < 4; ++col) {
                            if (NUMERALS[numeral][row][col] != lines[i+row].charAt(4*pos+col))
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
            result.add(new String(work));
        }
        return result;
    }
}
