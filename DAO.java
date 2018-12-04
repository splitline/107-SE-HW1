import java.io.*;
import java.util.*;

public abstract class DAO {
	abstract void put(String name, String number);

	abstract String get(String name);

	abstract void edit(String name, String number);

	abstract void delete(String name);
}