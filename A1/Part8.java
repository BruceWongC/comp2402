package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Part8 {

	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */


	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		TreeMap<String, List<String>> map = new TreeMap<>();
		int count = 0;

		for (String line = r.readLine(); line != null; line = r.readLine()){
			if (!map.containsKey(line)){
				map.put(line, new LinkedList<>());
				map.get(line).add(Integer.toString(count));

			}
			else {
				map.get(line).add(Integer.toString(count));
			}
			count++;

		}

		for (List<String> list : map.values()) {
			for (String s : list) {
				w.println(s);
			}
			
		}

	}



	/*public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		LinkedList<String> list = new LinkedList<> ();
		LinkedList<String> output = new LinkedList<>();
		int num;
		String str;

		for (String line = r.readLine(); line != null; line = r.readLine()){
			list.add(line);
			output.add(line);
		}

		Collections.sort(output);

		for (String s: output) {
			num = list.indexOf(s);
			list.set(num, "");
			w.println(num);
		}

	}

	 */



	/*public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		LinkedList<String> list = new LinkedList<>();
		LinkedList<String> output = new LinkedList<>();
		int num ;
		int count = 0;

		for (String line = r.readLine(); line != null; line = r.readLine()){
			num = 0;

			for (String s : list) {

				if (line.compareTo(s) < 0){
					break;
				}
				else {
					num++;
				}


			}

			output.add(num, Integer.toString(count));
			list.add(num, line);
			count++;

		}

		for (String s : output) {
			w.println(s);
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
