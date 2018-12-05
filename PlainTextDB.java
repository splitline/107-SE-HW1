import java.io.*;
import java.util.*;

public class PlainTextDB extends DAO {
	private String filename;
	private final static String CSV_SEPERATOR = ",";

	public PlainTextDB(String filename) {
		this.filename = filename;
		File file = new File(filename);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
			}
		}
	}

	void put(String name, String number) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
			writer.println(name + "," + number);
			writer.close();
		} catch (IOException e) {
		}
	}

	String get(String name) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(CSV_SEPERATOR);
				if (data[0].equals(name)) {
					br.close();
					return data[1];
				}
			}
		} catch (IOException e) {
		}
		return null;
	}

	void edit(String name, String number) {
		StringBuilder content = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(CSV_SEPERATOR);
				if (data[0].equals(name))
					content.append(data[0] + "," + number + "\n");
				else
					content.append(line + "\n");
			}
			br.close();
		} catch (IOException e) {
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			bw.write(content.toString());
			bw.close();
		} catch (IOException e) {
		}
	}

	void delete(String name) {
		StringBuilder content = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(CSV_SEPERATOR);
				if (!data[0].equals(name))
					content.append(line + "\n");
			}
			br.close();
		} catch (IOException e) {
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			bw.write(content.toString());
			bw.close();
		} catch (IOException e) {
		}
	}
}