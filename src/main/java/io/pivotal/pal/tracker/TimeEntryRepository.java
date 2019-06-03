package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository{
    TimeEntry create(TimeEntry any);

    TimeEntry find(Long timeEntryId);

    List list();

    TimeEntry update(Long eq, TimeEntry any);

    void delete(Long timeEntryId);
}
