import com.google.common.collect.Lists;
import com.sun.javafx.logging.Logger;
import com.sun.org.apache.bcel.internal.generic.ATHROW;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class TestJavaStream {

    @Test
    public void testStream() {
        ArrayList<People> people = Lists.newArrayList(new People("red", "1.8"),
                new People("white", "1.5"),
                new People("green", "2.0"),
                new People("black", "1.7"));
        Optional<People> first = people.stream().findFirst();
        System.out.println("first = " + first);
        //过滤查找
        List<String> red = people.stream().filter(p -> p.getColor().equals("red")).map(People::getHeight).collect(Collectors.toList());
        System.out.println("red = " + red);
        People people1 = null;
        Optional<People> people11 = Optional.ofNullable(people1);
        //遍历
        people.stream().forEach(p -> {
            System.out.println("p.getColor() = " + p.getColor());
        });
        //过滤查找
        boolean b = people.stream().map(People::getHeight).anyMatch(red::contains);
        System.out.println("b = " + b);
        Optional<String> any = people.stream().map(People::getHeight).filter(red::contains).findAny();
        System.out.println("any.get() = " + any.get());
        boolean present = people.stream().map(People::getHeight).filter(red::contains).findAny().isPresent();
        System.out.println("present = " + present);
        //map转化
        Map<String, People> peopleMap = people.stream().collect(Collectors.toMap(People::getColor, o -> o));
        System.out.println("peopleMap = " + peopleMap);
        //排序
//        people.stream().sorted(Comparator.comparing(People::getHeight).reversed()).forEach(
//                people2 -> {
//                    people2.setColor(people2.getColor() + "sms");
//                }
//        );
        List<People> collect = StreamEx.of(people).reverseSorted(Comparator.comparing(People::getHeight)).collect(Collectors.toList());
        System.out.println("collect = " + collect);
    }

    @Test
    public void testStreamEx(){
        ArrayList<People> people = Lists.newArrayList(
                new People("red", "1.8"),
                new People("white", "1.5"),
                new People("green", "2.0"),
                new People("green", "2.7"),
                new People("black", "2.5"),
                new People("black", "1.7"));
        people.forEach(
                people1 -> {
                    people1.setColor("gas");
                }
        );
        System.out.println("people = " + people);
        ArrayList<String> colors = new ArrayList<>();
        colors.add("white");
        List<People> people2 = StreamEx.of(people).filter(people1 -> colors.contains(people1.getColor())).toList();

        System.out.println("people = " + people2);
        Map<String, List<People>> stringListMap = StreamEx.of(people).groupingBy(People::getColor);
        System.out.println("stringListMap = " + stringListMap);
        int size = StreamEx.of(people).groupingBy(People::getColor).size();
        System.out.println("size = " + size);
        Collection<List<People>> values = StreamEx.of(people).groupingBy(People::getHeight).values();
        System.out.println("values = " + values);

        ArrayList<String> strings = Lists.newArrayList("BLUE_RECHARGE", "REMOTE_RECHARGE","REMOTE_RECHARGE");
        List<String> strings1 = StreamEx.of(strings).reverseSorted().toList();
        strings1.forEach(System.out::println);

    }

    @Test
    public void testOptional() throws Exception {
        People people1 = null;
        boolean present = Optional.ofNullable(people1).map(people2 -> people2.getColor()).map(s -> s.toUpperCase()).isPresent();
        System.out.println("s1 = " + present);
        String color = Optional.ofNullable(people1).orElseGet(() -> new People()).getColor();
        System.out.println("color = " + color);
        ArrayList<People> people2 = Lists.newArrayList(new People("red", "1.8"),
                new People("white", "1.5"),
                new People("green", "2.0"),
                new People("green", "2.7"),
                new People("black", "2.5"),
                new People("black", "1.7"));
        String s = Optional.ofNullable(people2).map(people3 -> people3.get(0).getColor()).get();
        System.out.println("s = " + s);
    }

    @Test
    public void testBigDecimal(){
        String date_1 = "2012-12-17";
        String date_2 = "2012/12/17";
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("^[0-9]{4}[-\\/]\\d+[-\\/]\\d+");
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy[\\-\\/]+MM[\\-\\/]+dd");
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy[-|\\/]MM[-|\\/]dd");
//        LocalDate parse = LocalDate.parse(date_1);
//        System.out.println("parse = " + parse);
//        LocalDate parse1 = LocalDate.parse(date_2);
//        System.out.println("parse1 = " + parse1);
        LocalDateTime localDate = LocalDateTime.now().minusDays(1);
        System.out.println("localDate = " + localDate.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        System.out.println("LocalDateTime.now().getMonth().toString() = " + LocalDateTime.now().getMonth().getValue());

        BigDecimal affectSchemerAmount = new BigDecimal(-2);
        BigDecimal finish = new BigDecimal(86.3);
        BigDecimal thiReceiptAccrualRate = affectSchemerAmount.divide(
                finish, 4, BigDecimal.ROUND_HALF_EVEN).multiply(BigDecimal.valueOf(100.00f))
                .setScale(2, BigDecimal.ROUND_HALF_EVEN);

        System.out.println("thiReceiptAccrualRate = " + thiReceiptAccrualRate);
    }


    @Test
    public void testConvert(){
        String s = "000000000827";
        System.out.println("s1 = " + String.format("%08x", Integer.valueOf(s)));
        List<String> strings = Arrays.asList("1", "2", "3");
        strings.forEach(s1 -> {
            System.out.println("s1_start = " + s1);
            if(s1.equals("1")){
                return;
            }
            System.out.println("s1_end = " + s1);
        });

    }

    @Test
    public void testNullAble(){
        if (null == ThreadLocalAccrualHelper.get()) {
            System.out.println("11111 ");
            ThreadLocalAccrualHelper.set(true);
        }
        if (null == ThreadLocalAccrualHelper.get()) {
            System.out.println("22222 ");
        }
        //对象为空和不为空时计算流程
        if(Boolean.TRUE.equals(ThreadLocalAccrualHelper.get())){
            System.out.println("ttttttt ");
            ThreadLocalAccrualHelper.clear();
        }
        if(Boolean.TRUE.equals(ThreadLocalAccrualHelper.get())){
            System.out.println("ttttttt====1111 ");
            ThreadLocalAccrualHelper.clear();
        }else {
            System.out.println("===== " + ThreadLocalAccrualHelper.get());
        }
    }

    @Data
    class People {

        public People(String color, String height) {
            this.color = color;
            this.height = height;
        }

        private String color;

        private String height;

        public People() {

        }
    }
}
