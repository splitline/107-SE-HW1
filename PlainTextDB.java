import java.nio.file.*;
import java.util.stream.Stream;

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
		} catch (Exception e) {
		}
	}

	@Override
	public String get(String name) {
		try {
			return Files.lines(filePath)
						.filter(data -> data.startsWith(name + CSV_SEPERATOR))
						.findFirst().get()
						.split(CSV_SEPERATOR)[1];
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public void delete(String name) {
		try(Stream<String> input = Files.lines(filePath)) {
			StringBuilder content = new StringBuilder();
			input.filter(s -> !s.startsWith(name + CSV_SEPERATOR))
				.forEachOrdered(s -> content.append(s + "\n"));

			Files.write(filePath, content.toString().getBytes());
		} catch(Exception e) {}
	}

	@Override
	public void edit(String name, String number) {
		this.delete(name);
		this.put(name, number);
	}
}