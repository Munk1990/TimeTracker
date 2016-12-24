package com.kmayank.timetracker.dao;

import com.kmayank.timetracker.models.Entry;
import com.kmayank.timetracker.models.Task;

import java.util.Date;
import java.util.List;

/**
 * Created by kmayank on 11/29/16.
 */

public interface IEntryDao {
    public void insertEntry(Entry entry);
    public void deleteEntry(Entry entry);
    public List<Entry> findEntries(Task task, Date startDate, Date endDate);
}
