package nefu.gc;

public class GCTest {
    public static void main(String[] args) {
        System.setProperty("java.vm.options", "-XX:+UseG1GC");
        for (int i = 0; i < 100000000; i++) {
            new Object();
        }
    }
}
