import org.junit.Test;

import java.util.regex.Pattern;

public class TestString {
    public static final String PATTERN = "^[0-9]+楼[0-9]+单元[0-9]+";

    @Test
    public void testSplit(){
        String s = "19楼1单元101张丽桥";

        boolean matches = Pattern.matches(PATTERN, s);
        System.out.println("matches = " + matches);
        String s1 = s.replaceAll(PATTERN, "").trim();
        System.out.println("s1 = " + s1);
    }
}
