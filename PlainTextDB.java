import java.io.*;
import java.util.*;
import java.util.stream.Stream;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class PlainTextDB extends DAO {
	private Path filePath;
	private final static String CSV_SEPERATOR = ",";

	public PlainTextDB(String filename) {
		this.filePath = Paths.get(filename);
		try {
			Files.createFile(filePath);
		} catch (Exception e) {
		}
	}

	@Override
	public void put(String name, String number) {
		try {
			Files.write(filePath, (name + "," + number + "\n").getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
		}
	}

	@Override
	public String get(String name) {
		try {
			return Files.lines(filePath)
						.filter(data -> data.startsWith(name + CSV_SEPERATOR))
						.findFirst().get()
						.split(CSV_SEPERATOR)[1];
		} catch (IOException e) {
		}
		return null;
	}

	@Override
	public void edit(String name, String number) {
		try(Stream<String> input = Files.lines(filePath)) {
			StringBuilder content = new StringBuilder();
			input
				.map(s -> s.startsWith(name + CSV_SEPERATOR) ? (name + "," + number) : s)
				.forEachOrdered(s -> content.append(s + "\n"));
			
			Files.write(filePath, content.toString().getBytes());
		} catch(Exception e) { e.printStackTrace(); }
	}

	@Override
	public void delete(String name) {
		try(Stream<String> input = Files.lines(filePath)) {
			StringBuilder content = new StringBuilder();
			input
				.filter(s -> !s.startsWith(name + CSV_SEPERATOR))
				.forEachOrdered(s -> content.append(s + "\n"));

			Files.write(filePath, content.toString().getBytes());
		} catch(Exception e) {}
	}
}