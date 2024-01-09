package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;

public class Part4 {
	
	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */

	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		TreeSet<String> set = new TreeSet<>();
		SortedSet<String> out;
		boolean flag;
		int count;

		for (String line = r.readLine(); line != null; line = r.readLine()) {
			flag = true;
			out = set.tailSet(line);
			count = 0;
			for (String s : out) {
				if (s.startsWith(line)){
					flag = false;
					break;
				}
				else if (count == 5) {
					break;
				}
				count++;

			}
			if (flag){
				w.println(line);
				set.add(line);
			}


		}
	}



	/*public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		LinkedList<String> list = new LinkedList<>();
		LinkedList<String> list2 = new LinkedList<>();
		boolean flag = true;

		for (String line = r.readLine(); line != null; line = r.readLine()) {
			list.add(line);
		}

		for (String s : list) {

			for (String str : list2) {
				if (str.startsWith(s)){
					flag = false;
					break;
				}
			}

			if (flag){
				w.println(s);
				list2.add(s);
			}
			flag = true;
		}
	}

	 */

	/*public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		LinkedList<String> list = new LinkedList<>();

		for (String line = r.readLine(); line != null; line = r.readLine()) {
			String finalLine = line;

			if (list.stream().noneMatch((a) -> a.startsWith(finalLine))){
				list.add(line);
				w.println(line);
			}
		}
	}

	 */



	/**
	 * The driver.  Open a BufferedReader and a PrintWriter, either from System.in
	 * and System.out or from filenames specified on the command line, then call doIt.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader r;
			PrintWriter w;
			if (args.length == 0) {
				r = new BufferedReader(new InputStreamReader(System.in));
				w = new PrintWriter(System.out);
			} else if (args.length == 1) {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(System.out);				
			} else {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(new FileWriter(args[1]));
			}
			long start = System.nanoTime();
			doIt(r, w);
			w.flush();
			long stop = System.nanoTime();
			System.out.println("Execution time: " + 1e-9 * (stop-start));
		} catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}
}
