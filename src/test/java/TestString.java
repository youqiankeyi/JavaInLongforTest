import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;
import org.junit.Test;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class TestString {
    public static final String PATTERN_SPLIT = "^[0-9]+[号|楼]+[0-9]+单元[0-9]+";
    public static final String PATTERN = "^[0-9]+[号|楼]+[0-9]+单元[0-9]+.+";

    /**
     * 天洋项目编码
     */
    public static final String TIAN_YANG = "tianyang";

    /**
     * 月亮湾项目编码
     */
    public static final String YUE_LIANG_WAN = "yueliangwan";

    @Test
    public void testSplit(){
        String s = "24号楼1单元101张立娜";
        String s_0 = "24号楼2单元2801孙强,程淑圆";

        extracted(s_0);
        extracted(s);
    }

    private void extracted(String s_0) {
        boolean matches = Pattern.matches(PATTERN, s_0);
        System.out.println("matches = " + matches);
        if (matches) {
            String s1 = s_0.replaceAll(PATTERN_SPLIT, "").trim();
            System.out.println("s1 = " + s1);
            String[] split = s_0.split(s1);
            System.out.println("split = " + split[0]);

        }
    }

    @Test
    public void testEnum(){
        isMatch(TIAN_YANG, "1");
        isMatch(TIAN_YANG, "2");
        isMatch(YUE_LIANG_WAN, "1");
        System.out.println("PATTERN_SPLIT = " + "1".equals(String.valueOf(1)));
    }

    private void isMatch(String customProjectCode, String extentCode){
        StreamEx.of(MeterProjectInfoEnum.values()).forEach(
                meterProjectInfoEnum -> {
                    if(meterProjectInfoEnum.getCustomProjectCode().equals(customProjectCode)){
                        if(null == meterProjectInfoEnum.getExtentCode()){
                            System.out.println("only customProjectCode!:" + meterProjectInfoEnum.getCustomAreaCode());
                        }else if(meterProjectInfoEnum.getExtentCode().equals(extentCode)){
                            System.out.println("has extentCode!:" + meterProjectInfoEnum.getCustomAreaCode());
                        }
                    }
                }
        );
    }

    @Test
    public void testDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        System.out.println("calendar.get(Calendar.YEAR) = " + calendar.get(Calendar.YEAR)%2000);
        System.out.println("calendar.get(Calendar.YEAR) = " + calendar.get(Calendar.DAY_OF_YEAR));
    }

    @Test
    public void testCompare() throws UnknownHostException {
        System.out.println("LocalDateTime.now() = " + LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        System.out.println("LocalDateTime.now() = " + LocalDateTime.now());
        InetAddress ip4 = Inet4Address.getLocalHost();
        System.out.println(ip4.getHostAddress());
    }

    @Data
    class HouseMeterInfo {
        /**
         * 房间号
         */
        private String roomNo;

        /**
         * 设备编号
         */
        private String serialNo;

        /**
         * 自定义区域编码
         */
        private String customAreaCode;

        /**
         * 营业所代码
         */
        private String extentCode;
    }
}
