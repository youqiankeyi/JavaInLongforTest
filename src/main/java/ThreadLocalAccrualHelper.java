public class ThreadLocalAccrualHelper {

    private static final ThreadLocal threadLocalAccrual = new ThreadLocal<>();

    /**
     *
     *
     * @return
     */
    @SuppressWarnings({"unchecked"})
    public static <T> T get() {
        return (T) threadLocalAccrual.get();
    }

    /**
     *
     *
     */
    public static void clear() {
        threadLocalAccrual.remove();
    }


    /**
     *
     *
     */
    @SuppressWarnings({"unchecked"})
    public static <T> void set(T existUnFinishRefund) {
        threadLocalAccrual.set(existUnFinishRefund);
    }

}
