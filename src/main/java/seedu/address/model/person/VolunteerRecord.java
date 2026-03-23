package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a Volunteer's specific volunteering record in the address book.
 * Guarantees: immutable; details are present and not null.
 */
public class VolunteerRecord {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public final LocalDateTime startDateTime;
    public final LocalDateTime endDateTime;

    /**
     * Constructs a {@code VolunteerRecord}.
     *
     * @param startDateTime A valid start date and time.
     * @param endDateTime A valid end date and time.
     */
    public VolunteerRecord(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        requireNonNull(startDateTime);
        requireNonNull(endDateTime);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof VolunteerRecord)) {
            return false;
        }

        VolunteerRecord otherRecord = (VolunteerRecord) other;
        return startDateTime.equals(otherRecord.startDateTime)
                && endDateTime.equals(otherRecord.endDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDateTime, endDateTime);
    }

    @Override
    public String toString() {
        return String.format("%s to %s",
                startDateTime.format(FORMATTER),
                endDateTime.format(FORMATTER));
    }
}
