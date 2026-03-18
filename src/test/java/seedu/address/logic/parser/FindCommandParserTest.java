package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATCH_TYPE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.FindMatchType.KEYWORD_TOKEN;
import static seedu.address.logic.parser.FindMatchType.SUBSTRING_TOKEN;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.predicates.PersonContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PersonContainsSubstringsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PersonContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);
        assertParseSuccess(parser, PREFIX_MATCH_TYPE + KEYWORD_TOKEN + " Alice Bob", expectedFindCommand);

        FindCommand expectedSubstringFindCommand =
                new FindCommand(new PersonContainsSubstringsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, PREFIX_MATCH_TYPE + SUBSTRING_TOKEN + " Alice Bob", expectedSubstringFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
        assertParseSuccess(parser, " \n " + PREFIX_MATCH_TYPE + KEYWORD_TOKEN + " \n \t Alice  \t Bob  \t",
                expectedFindCommand);
        assertParseSuccess(parser, " \n " + PREFIX_MATCH_TYPE + SUBSTRING_TOKEN + " \n \t Alice  \t Bob  \t",
                expectedSubstringFindCommand);
    }

    @Test
    public void parse_matchTypeNotFirstToken_throwsParseException() {
        assertParseFailure(parser, "Alice " + PREFIX_MATCH_TYPE + KEYWORD_TOKEN + " Bob",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_unsupportedMatchType_throwsParseException() {
        assertParseFailure(parser, PREFIX_MATCH_TYPE + "regex Alice Bob",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingKeywords_throwsParseException() {
        assertParseFailure(parser, PREFIX_MATCH_TYPE + KEYWORD_TOKEN,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

}
