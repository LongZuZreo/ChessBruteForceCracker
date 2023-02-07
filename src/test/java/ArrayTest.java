

public class ArrayTest {

    public static void main(String[] args) {
        long[] a = new long[]{123334,4445};
        long[] b = new long[]{123334,4445};
        System.out.println(a.hashCode() == b.hashCode());

        byte[] aaa = new byte[]{0b001,0b001001011,0b0101010};

        String strA = new String(aaa);
        String strB = new String(aaa);

        System.out.println(strA.hashCode() == strB.hashCode());

    }


}
