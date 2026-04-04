package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.PersonListView;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    public static final String MESSAGE_NOT_VIEWING_KEPT_PERSONS =
            "You must be viewing the working list of kept contacts to perform this command.";
    public static final String MESSAGE_NOT_VIEWING_DELETED_PERSONS =
            "You must be viewing the recycle bin of recently deleted contacts to perform this command.";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @param personListView List which is viewed by the user before command execution.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model, PersonListView personListView) throws CommandException;

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     * @deprecated Use {@link #execute(Model, PersonListView)} instead, which specifies the currently viewed list.
     */
    @Deprecated
    public CommandResult execute(Model model) throws CommandException {
        return execute(model, PersonListView.KEPT_PERSONS);
    }

    /**
     * Checks if the user is currently viewing the kept persons list.
     *
     * @throws CommandException If the user is not viewing the kept persons list.
     */
    protected static void requireViewingKeptPersons(PersonListView personListView) throws CommandException {
        if (personListView != PersonListView.KEPT_PERSONS) {
            throw new CommandException(MESSAGE_NOT_VIEWING_KEPT_PERSONS);
        }
    }

    /**
     * Checks if the user is currently viewing the deleted persons list.
     *
     * @throws CommandException If the user is not viewing the deleted persons list.
     */
    protected static void requireViewingDeletedPersons(PersonListView personListView) throws CommandException {
        if (personListView != PersonListView.DELETED_PERSONS) {
            throw new CommandException(MESSAGE_NOT_VIEWING_DELETED_PERSONS);
        }
    }

    /**
     * Returns true if indices are in strictly increasing order.
     */
    protected static boolean isStrictlyIncreasing(List<Index> indices) {
        for (int i = 0; i + 1 < indices.size(); i++) {
            if (indices.get(i).compareTo(indices.get(i + 1)) >= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validates that all target indices are within bounds of the target list.
     */
    protected static void requireIndicesInRange(List<Index> targetIndices, List<?> targetList) throws CommandException {
        for (Index targetIndex : targetIndices) {
            if (targetIndex.getZeroBased() >= targetList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        }
    }

    /**
     * Collects elements from a list, given indices.
     */
    protected static <T> List<T> collectItemsByIndices(List<Index> targetIndices, List<T> targetList) {
        List<T> items = new ArrayList<>();
        for (Index index : targetIndices) {
            items.add(targetList.get(index.getZeroBased()));
        }
        return items;
    }

}
