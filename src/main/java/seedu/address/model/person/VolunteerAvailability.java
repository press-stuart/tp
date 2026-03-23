package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Represents a Volunteer's availability in the address book.
 * Guarantees: immutable; details are present and not null.
 */
public class VolunteerAvailability {

    public final DayOfWeek dayOfWeek;
    public final LocalTime startTime;
    public final LocalTime endTime;

    /**
     * Constructs a {@code VolunteerAvailability}.
     *
     * @param dayOfWeek A valid day of the week.
     * @param startTime A valid start time.
     * @param endTime A valid end time.
     */
    public VolunteerAvailability(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        requireNonNull(dayOfWeek);
        requireNonNull(startTime);
        requireNonNull(endTime);
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof VolunteerAvailability)) {
            return false;
        }

        VolunteerAvailability otherAvailability = (VolunteerAvailability) other;
        return dayOfWeek.equals(otherAvailability.dayOfWeek)
                && startTime.equals(otherAvailability.startTime)
                && endTime.equals(otherAvailability.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfWeek, startTime, endTime);
    }

    @Override
    public String toString() {
        return String.format("%s %s to %s", dayOfWeek, startTime, endTime);
    }
}
