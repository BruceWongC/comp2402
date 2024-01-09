package comp2402a4;

import java.util.Iterator;
import java.util.Random;

public class Tester {
    static <T> void showContents(Iterable<Integer> ds) {
        System.out.print("[");
        Iterator<Integer> it = ds.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
            if (it.hasNext()) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    static void ultraTest(UltraStack rs, int n) {
        System.out.println(rs.getClass().getName());
        comp2402a4.UltraSlow rs1 = new comp2402a4.UltraSlow();

        Random rand = new Random();
        for (int j = 0; j < n; j++) {
            int x = rand.nextInt(3*n/2);
            System.out.println("push(" + x + ")");
            rs.push(x);
            rs1.push(x);
            showContents(rs);
            int i = rand.nextInt(rs.size());
            System.out.println("get("+ i +") = " + rs.get(i));
            System.out.println("max() = " + rs.max());
            int k = rand.nextInt(rs.size()+1);
            System.out.println("ksum("+ k +") = " + rs.ksum(k));
            if(rs.ksum(k) != rs1.ksum(k)){
                System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
                System.exit(0);
            }
            assert(rs.ksum(k) == rs1.ksum(k));
        }

        System.out.println();
        for (int j = 0; j < n/2; j++) {
            int i = rand.nextInt(rs.size());
            int x = rand.nextInt(3*n/2);
            System.out.println("set(" + i + ", " + x + ") = " + rs.set(i, x));
            rs1.set(i,x);
            showContents(rs);
            i = rand.nextInt(rs.size());
            System.out.println("get("+ i +") = " + rs.get(i));
            System.out.println("max() = " + rs.max());
            int k = rand.nextInt(rs.size()+1);
            System.out.println("ksum("+ k +") = " + rs.ksum(k));
            if(rs.ksum(k) != rs1.ksum(k)){
                System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
                System.exit(0);
            }
            assert(rs.ksum(k) == rs1.ksum(k));

        }


        System.out.println();

        while (rs.size() > 0) {
            System.out.println("pop() = " + rs.pop());
            rs1.pop();
            showContents(rs);
            if(rs.size() > 0){
                int i = rand.nextInt(rs.size());
                System.out.println("get("+ i +") = " + rs.get(i));
                System.out.println("max() = " + rs.max());
                int k = rand.nextInt(rs.size()+1);
                System.out.println("ksum("+ k +") = " + rs.ksum(k));
                if(rs.ksum(k) != rs1.ksum(k)){
                    System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
                    System.exit(0);
                }
                assert(rs.ksum(k) == rs1.ksum(k));
            }
        }

    }

    public static void main(String[] args) {
        //ultraTest(new UltraSlow(), 17);
        ultraTest(new UltraFast(), 160);
    }
}
