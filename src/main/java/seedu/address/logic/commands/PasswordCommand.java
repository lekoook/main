package seedu.address.logic.commands;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.FileEncryptor;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.XmlAddressBookStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Encrypts the XML data using a password and returns a message
 * Message will be displayed on CommandResult
 */
public class PasswordCommand extends Command {
    public static final String COMMAND_WORD = "password";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Hashes file using password.\n"
            + "Parameters: KEYWORD PASSWORD...\\n"
            + "Example: " + COMMAND_WORD + " myPassword";

    // public static final String MESSAGE_SUCCESS = "Command executed";
    private String message;

    /**
     * Executes the FileEncryptor and obtains a message
     * @param credentials will be obtained from parser
     */
    public PasswordCommand (String[] credentials) {
        FileEncryptor fe = new FileEncryptor("data/addressbook.xml");
        fe.process(credentials[0]);
        String message = fe.getMessage();
        this.message = message;
    }

    @Override
    public CommandResult execute (Model model, CommandHistory history) {
        // TODO: Reset the display locally, do not commit to changes
        Path path = Paths.get("data/addressbook.xml");
        XmlAddressBookStorage storage = new XmlAddressBookStorage(path);
        ReadOnlyAddressBook initialData;
        try {
            initialData = storage.readAddressBook().orElseGet(SampleDataUtil::getSampleAddressBook);
            model.resetData(initialData);
        } catch (IOException ioe) {

        } catch (DataConversionException data) {

        }

        return new CommandResult(this.message);
    }

}
