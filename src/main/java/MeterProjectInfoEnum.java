
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MeterProjectInfoEnum {

    TIAN_YANG_1("天洋新二期", "1", "1","tianyang"),
    TIAN_YANG_2("天洋新天城", "2","2","tianyang"),
    YUE_LIANG_WAN("嫦娥月亮湾小镇", null,"3","yueliangwan");

    private String desc;

    private String extentCode;

    private String customAreaCode;

    private String customProjectCode;

}