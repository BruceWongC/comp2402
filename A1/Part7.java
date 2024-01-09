package comp2402a1;

import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Part7 {
	
	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		LinkedList<LinkedList<String>> list = new LinkedList<>();
		int index = 0;
		int counter = 1;
		int count = 1;

		for (String line = r.readLine(); line != null; line = r.readLine()){
			if (count == 0){
				counter++;
				count = counter;
				list.add(new LinkedList<>());

				index++;
			}
			if(list.isEmpty()){
				list.add(new LinkedList<>());
				list.get(0).add(line);
				if (!line.equals("***reset***")) {
					count--;
				}
				else {
					index++;
				}

			}
			else if (line.equals("***reset***")) {
			//	index++;
			//	list.add(new LinkedList<>());
				list.get(index).add(line);
				list.add(new LinkedList<>());
				index++;
				counter = 1;
				count = 1;

			}
			else {
				list.get(index).add(line);
				count--;

			}

		}

		Collections.reverse(list);

		for (LinkedList<String> L: list) {
			for (String s : L) {
				w.println(s);
			}
		}



	}

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
