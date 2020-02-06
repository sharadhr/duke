package sharadhr.duke.io;

import sharadhr.duke.Duke;
import sharadhr.duke.exception.DukeEmptyDetailException;
import sharadhr.duke.exception.DukeInvalidDateException;
import sharadhr.duke.parse.DateParser;
import sharadhr.duke.task.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Optional;

/**
 * A class to handle file read/write operations by the Duke program.
 */
public class Storage
{
	private Path taskFile;

	private BufferedWriter writer;

	public Storage(String... directory)
	{
		this.taskFile = Paths.get(".", directory).normalize().toAbsolutePath();

		try
		{
			Files.createDirectories(this.taskFile.getParent());

			if (Files.notExists(this.taskFile))
			{
				Files.createFile(this.taskFile);
			}

			this.writer = Files.newBufferedWriter(this.taskFile, StandardOpenOption.WRITE,
					StandardOpenOption.APPEND);
			Files.newBufferedReader(this.taskFile);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	static Optional<Task> decodeLine(String line)
			throws DukeEmptyDetailException, DukeInvalidDateException
	{
		String[] tokens = line.split(",");

		switch (tokens[0])
		{
			case "T":
				return tokens.length == 3 ?
						Optional.of(new Todo(tokens[2], Boolean.parseBoolean(tokens[1]))) :
						Optional.empty();
			case "D":
				return tokens.length == 4 ?
						Optional.of(new Deadline(tokens[2], Boolean.parseBoolean(tokens[1]),
								DateParser.parseDateTimeString(tokens[3]))) :
						Optional.empty();
			case "E":
				return tokens.length == 5 ?
						Optional.of(new Event(tokens[2], Boolean.parseBoolean(tokens[1]),
								DateParser.parseDateTimeString(tokens[3]),
								DateParser.parseDateTimeString(tokens[4]))) :
						Optional.empty();
			default:
				return Optional.empty();
		}
	}

	public void appendTaskToFile(Task task)
	{
		try
		{
			writer.append(task.encode());
			writer.newLine();
			writer.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Returns a {@link TaskList}, parsed from the specified file associated with
	 * this {@link Storage} object.
	 *
	 * @return the {@link Tasklist} after parsing the file on disk
	 * @throws DukeEmptyDetailException
	 */
	public TaskList loadFromFile()
	{
		ArrayList<Task> loadedTasks = new ArrayList<Task>();
		try
		{
			for (String line : Files.readAllLines(taskFile, Charset.defaultCharset()))
			{
				Optional<Task> possibleTask;
				try
				{
					possibleTask = decodeLine(line);
				}
				catch (DukeEmptyDetailException e)
				{
					possibleTask = Optional.empty();
					Duke.output.sayError(e);

				}
				catch (DukeInvalidDateException e)
				{

				}

			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return new TaskList(loadedTasks);
	}
}