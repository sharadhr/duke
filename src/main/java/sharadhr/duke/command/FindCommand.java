package sharadhr.duke.command;

import java.util.stream.Stream;

import sharadhr.duke.Duke;
import sharadhr.duke.exception.DukeEmptyDetailException;
import sharadhr.duke.exception.DukeInvalidArgumentException;
import sharadhr.duke.exception.DukeInvalidDateException;
import sharadhr.duke.io.Storage;
import sharadhr.duke.task.Task;
import sharadhr.duke.task.TaskList;

/**
 * FindCommand
 */
public class FindCommand extends Command {
    public FindCommand(String[] argumentTokens) throws DukeInvalidArgumentException {
        super(argumentTokens);

        if (argumentTokens.length > 1) {
            throw new DukeInvalidArgumentException("Find command must be of the format: 'find <keyword>'.",
                    this.getClass().getSimpleName());
        }
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeEmptyDetailException, DukeInvalidDateException {
        Duke.output.add("These tasks match or contain the keyword ''");
        Duke.output.say(Stream.of(Duke.tasks.findTasksWithKeyword(argumentTokens[0])).map(Task::toString)
                .toArray(String[]::new));

    }
}