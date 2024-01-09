package comp2402a3;
import java.util.Iterator;
import java.util.Random;

public class Tester {
    static <T> void showContents(Iterable<T> ds) {
        System.out.print("[");
        Iterator<T> it = ds.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
            if (it.hasNext()) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    static void skippityTest(int n){
        Random rand = new Random();
        IndexedSSet<Integer> isss = new SkippitySlow<>();
        IndexedSSet<Integer> iss = new SkippityFast<>();

        for (int i = 0; i < n; i++) {
            int x = rand.nextInt(3*n);
            System.out.println("add(" + x + ") = " + iss.add(x) + ": correct: "+ isss.add(x));
        }

        for (int i = 0; i < iss.size(); i++) {
            System.out.println("get(" + i + ")=" + iss.get(i) + ":correct: " + isss.get(i));
        }

        System.out.print("Contents (Fast): ");
        showContents(iss);
        System.out.println("size()=" + iss.size());

        System.out.print("Contents (Slow): ");
        showContents(isss);
        System.out.println("size()=" + isss.size());
        System.out.println("\n");


        for (int i = 0; i < n; i++){
            int x = rand.nextInt(3*n);
            boolean flag1 = iss.remove(x), flag2 = isss.remove(x);
            System.out.println("remove(" + x + ") = " + flag1 +":correct: " + flag2);


            if(flag1 != flag2){
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            }
//            else if (flag1){
//                for (int j = 0; j < iss.size(); j++) {
//                    System.out.println("get(" + j + ")=" + iss.get(j) + ":correct: " + isss.get(j));
//                }
//            }

        }


        for (int i = 0; i < iss.size(); i++) {
            System.out.println("get(" + i + ")=" + iss.get(i) + ":correct: " + isss.get(i));
        }


        System.out.print("Contents (Fast): ");
        showContents(iss);

        System.out.println("size()=" + iss.size());

        System.out.print("Contents (Slow): ");
        showContents(isss);
        System.out.println("size()=" + isss.size());
        System.out.println("\n");

        for (int i = 0; i < n; i++) {
            int x = rand.nextInt(3*n);
            boolean flag1 = iss.add(x), flag2 = isss.add(x);
            System.out.println("add(" + x + ") = " + flag1 + ": correct: "+ flag2);
            if(flag1 != flag2){
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            }

        }

        for (int i = 0; i < iss.size(); i++) {
            System.out.println("get(" + i + ")=" + iss.get(i) + ":correct: " + isss.get(i));
        }


        System.out.print("Contents (Fast): ");
        showContents(iss);

        System.out.println("size()=" + iss.size());

        System.out.print("Contents (Slow): ");
        showContents(isss);
        System.out.println("size()=" + isss.size());

//        for (int i = 0; i < iss.size(); i++) {
//            System.out.println("get(" + i + ")=" + iss.get(i) + ":correct: " + isss.get(i));
//           // System.out.println("get(" + i + ")=" + isss.get(i));
//        }

        for (int i = 0; i < 10; i++) {
            int x = rand.nextInt(3*n);
            int y = rand.nextInt(3*n);
            System.out.println("rangecount(" + x + ", " + y + ") = "+iss.rangecount(x, y) + ":correct: " + isss.rangecount(x,y));
        }

        for (int i = 0; i < n/2; i++){
            int x = rand.nextInt(3*n);
            System.out.println("Find(" + x + ")=" + iss.find(x) + ":correct: " + isss.find(x));
        }

    }

    static void treeTest(int n){
        BinaryTree t = BinaryTree.randomBST(n);
        System.out.println("Tree:" + t);
        System.out.println("size() = " + t.size());
        System.out.println("leafAndOnlyLeaf() = " + t.leafAndOnlyLeaf());
        System.out.println("dawnOfSpring() = " + t.dawnOfSpring());
        System.out.println("monkeyLand() = " + t.monkeyLand());
        System.out.println("bracketSequence() = " + t.bracketSequence());
    }

    public static void main(String[] args) {
        skippityTest(10);
        //treeTest(3);
    }
}
