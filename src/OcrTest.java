import org.junit.*;
import static org.junit.Assert.*;
import static java.util.Arrays.asList;

public class OcrTest {

    @Test
    public void parses_one_record_with_888888888() {
        assertEquals(asList("888888888    "),
                     Ocr.parse(" _   _   _   _   _   _   _   _   _  ",
                               "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                               "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                               "                                    "));
    }

    @Test
    public void parses_two_records_with_888888888() {
        assertEquals(asList("888888888    ",
                            "888888888    "),
                     Ocr.parse(" _   _   _   _   _   _   _   _   _  ",
                               "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                               "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                               "                                    ",
                               " _   _   _   _   _   _   _   _   _  ",
                               "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                               "|_| |_| |_| |_| |_| |_| |_| |_| |_| ",
                               "                                    "));
    }

    @Test
    public void parses_one_record_with_123456790() {
        assertEquals(asList("123456790    "),
                     Ocr.parse("     _   _       _   _   _   _   _  ",
                               "  |  _|  _| |_| |_  |_    | |_| | | ",
                               "  | |_   _|   |  _| |_|   |  _| |_| ",
                               "                                    "));
    }

    @Test
    public void parses_two_records() {
        assertEquals(asList("123456790    ",
                            "947874222    "),
                     Ocr.parse("     _   _       _   _   _   _   _  ",
                               "  |  _|  _| |_| |_  |_    | |_| | | ",
                               "  | |_   _|   |  _| |_|   |  _| |_| ",
                               "                                    ",
                               " _       _   _   _       _   _   _  ",
                               "|_| |_|   | |_|   | |_|  _|  _|  _| ",
                               " _|   |   | |_|   |   | |_  |_  |_  ",
                               "                                    "));
    }

    @Test
    public void parses_illegal_digit() {
        assertEquals(asList("12??56790 ILL"),
                     Ocr.parse("     _   _       _   _   _   _   _  ",
                               "  |  _| |_|  _| |_  |_    | |_| | | ",
                               "  | |_   _    |  _| |_|   |  _| |_| ",
                               "                                    "));
    }

  
}

