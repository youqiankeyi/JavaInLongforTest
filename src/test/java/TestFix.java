import org.junit.Test;

public class TestFix {

    @Test
    public void testBool(){
        System.out.println("testDoubleBool(true,true) = " + testDoubleBool(true,true));
        System.out.println("testDoubleBool(true,true) = " + testDoubleBool(true,false));
        System.out.println("testDoubleBool(true,true) = " + testDoubleBool(false,true));
        System.out.println("testDoubleBool(true,true) = " + testDoubleBool(false,false));
        System.out.println("testDoubleBool(true,true) = " + testDoubleBool_stu(true,true));
        System.out.println("testDoubleBool(true,true) = " + testDoubleBool_stu(true,false));
        System.out.println("testDoubleBool(true,true) = " + testDoubleBool_stu(false,true));
        System.out.println("testDoubleBool(true,true) = " + testDoubleBool_stu(false,false));
    }

    private int testDoubleBool(boolean a, boolean b) {
        if (a && b) {
            return 1;
        } else if (a) {
            return 2;
        } else if (b) {
            return 3;
        }
        return 4;
    }
    private int testDoubleBool_stu(boolean a, boolean b) {
        if (a) {
            if (b) {
                return 1;
            } else {
                return 2;
            }
        } else {
            if (b) {
                return 3;
            }
        }
        return 4;
    }
}
