package ua.com.alevel.task4;

@Service
public class Test {
    @Value("52")
    private int a;
    private int b;
    @Value("MyStr")
    private String str;

    @Init
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
    @Init
    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
    @Init
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
