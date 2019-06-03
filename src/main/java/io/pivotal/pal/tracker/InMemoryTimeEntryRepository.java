package io.pivotal.pal.tracker;

import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository  {

    List list = new ArrayList();
    Map<Long, List<TimeEntry>> map = new HashMap<Long, List<TimeEntry>>();
    TimeEntry timeEntry1 = new TimeEntry();
    static Long i = 1L;
    static int count = 1;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry1 = new TimeEntry(i, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        list.add(timeEntry1);
        map.put(i,list);
        i++;
        return timeEntry1;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        List<TimeEntry> list = new ArrayList();
        for (Map.Entry<Long, List<TimeEntry>> m : map.entrySet()) {
            if (m.getKey() == timeEntryId){
                list = m.getValue();
                return list.get(0);
            }
        };
        return null;
    }

    @Override
    public List<TimeEntry> list() {
      return  list;
    }

    @Override
    public TimeEntry update(long eq, TimeEntry any) {
        TimeEntry timeEntry = find(eq);

        if (timeEntry !=  null && eq == timeEntry.getId()) {
            timeEntry.setId(timeEntry.getId());
            timeEntry.setProjectId(any.getProjectId());
            timeEntry.setUserId(any.getUserId());
            timeEntry.setDate(any.getDate());
            timeEntry.setHours(any.getHours());
            return timeEntry;
        }
        return null;
    }

    @Override
    public void delete(long timeEntryId) {
            for (Map.Entry<Long, List<TimeEntry>> longListEntry : map.entrySet()) {
               if (longListEntry.getKey() == timeEntryId){
                   List<TimeEntry> removeList = map.remove(longListEntry.getKey());
                   list.removeAll(removeList);
               }
            }

    }
}
