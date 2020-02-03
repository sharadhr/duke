package sharadhr.duke.task;

import sharadhr.duke.io.Input;
import sharadhr.duke.io.Output;
import sharadhr.duke.io.Storage;

/**
 * 
 */
public class Duke
{
    private static TaskList tasks;
    private static Input reader;
    static Output writer;
    static Storage fileRW;

    public boolean run() {
        return false;
    }

    /**
     * Runs the main program loop, and returns {@code false} when the user says
     * 'bye'.
     * 
     * @return {@code false} when the user says 'bye'; otherwise, never returns.
     */
    public static boolean programLoop()
    {
        boolean exitLoop = false;
        String command;
        
        // do
        // {
        //     Command cmd = Command.whichCommand((command = reader.nextLine().getFirstToken()));
        //     exitLoop = cmd.equals(Command.BYE);
            
        //     switch (cmd)
        //     {
        //         case TODO:
        //             try
        //             {
        //                 tasks.addTask(new Todo(reader.inputWithoutFirstToken()));
        //             }
        //             catch (DukeException e)
        //             {
        //                 System.err.println(e);
        //                 continue;
        //             }
        //             break;
        //         case DEADLINE:
        //             try
        //             {
        //                 tasks.addTask(new Deadline(reader.getFirstToken(), reader.getTime()));
        //             }
        //             catch (DukeException e)
        //             {
        //                 System.err.println(e);
        //                 continue;
        //             }
        //             break;
        //         case EVENT:
        //             try
        //             {
        //                 tasks.addTask(new Event(reader.getDetail(), reader.getTime()));
        //             }
        //             catch (DukeException e)
        //             {
        //                 System.err.println(e);
        //                 continue;
        //             }
        //             break;
        //         case LIST:
        //             tasks.listTasks();
        //             break;
        //         case DONE:
        //             tasks.getTaskAtPosition(Integer.parseInt(reader.inputWithoutFirstToken())).markComplete();
        //             break;
        //         case DELETE:
                    
        //         case EMPTY:
        //             writer.say("Empty input; please enter something.");
        //             continue;
        //         case INVALID:
        //             try
        //             {
        //                 throw new DukeInvalidCommandException(command, Duke.class.getName());
        //             }
        //             catch (DukeException e)
        //             {
        //                 writer.say("Invalid command; try again.");
        //             }
        //             continue;
        //         case BYE:
        //             break;
        //         default:
        //             break;
        //     }
        // } while (!exitLoop);
        // return exitLoop;
        return false;
    }

    /**
     * Cleans up objects and quits the program by calling {@link System#exit(int)}.
     */
    public static void exit()
    {
        writer.sayGoodBye();

        reader.close();
        writer.close();

        System.exit(0);
    }
    
    public static void main(String[] args)
    {
        // Initialises file and UI I/O
        fileRW = new Storage("data", "duke.txt");
        reader = new Input();
        writer = new Output();

        // Greets the user.
        writer.sayHello();

        // Creates the task list
        tasks = new TaskList();

        if (programLoop()) exit();
    }
}
