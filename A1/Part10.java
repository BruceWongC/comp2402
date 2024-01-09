package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

public class Part10 {

	/**
	 * Your code goes here - see Part0 for an example
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		LinkedList<String> list = new LinkedList<>();
		String biggest = null;
		int counter = 0;
		int index = 0;
		String last = "";

		for (String line = r.readLine(); line != null; line = r.readLine()){
			if (list.isEmpty()){
				biggest = line;
			}
			else{
				if(line.compareTo(biggest) > 0){//gets index of biggest number
					biggest = line;
					index = counter;
				}
			}
			list.add(line);
			counter++;
			last = line;

		}

		while (index != 0){//cuts out everything before biggest num
			list.removeFirst();
			index--;
		}

		//biggest = "";

		while(true){
			biggest = "";
			System.out.println(list);
			counter = 0;

			w.println(list.get(0));

			if(list.get(0).equals(last)){
				break;
			}

			list.removeFirst();
			for (String s : list) {
				if (s.compareTo(biggest) > 0) {
					biggest = s;
					index = counter;
				}
				counter++;

			}

			while (index != 0){//cuts out everything before biggest num
				list.removeFirst();
				index--;
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
