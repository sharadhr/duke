package sharadhr.duke.command;

import sharadhr.duke.exception.DukeEmptyDetailException;
import sharadhr.duke.exception.DukeInvalidArgumentException;
import sharadhr.duke.exception.DukeInvalidDateException;
import sharadhr.duke.io.Storage;
import sharadhr.duke.task.TaskList;

/**
 * ByeCommand
 */
public class ByeCommand extends Command
{

    public ByeCommand(String argumentTokens) throws DukeInvalidArgumentException
    {
        super(argumentTokens);
        this.commandName = CommandName.BYE;
        
        if (argumentTokens.length() >= 1)
        {
            throw new DukeInvalidArgumentException("Bye command must have no arguments.",
                    ByeCommand.class.getSimpleName());
        }
    }
    
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeEmptyDetailException, DukeInvalidDateException
    {
        
    }
}