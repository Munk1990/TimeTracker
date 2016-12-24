package com.kmayank.timetracker.dao;

import com.kmayank.timetracker.models.Entry;
import com.kmayank.timetracker.models.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kmayank on 11/27/16.
 */

public class EntryDaoListImpl implements IEntryDao {
    List<Entry> entryList = new ArrayList<>();

    @Override
    public void insertEntry(Entry entry) {
        entryList.add(entry);
    }

    @Override
    public void deleteEntry(Entry entry) {
        entryList.remove(entry);
    }

    @Override
    public List<Entry> findEntries(Task task, Date startDate, Date endDate) {
        //todo: Optimization required
        List<Entry> filteredList = new ArrayList<>();
        for (Entry e:entryList){
            if (e.getEntryDate().compareTo(startDate) >= 0 && e.getEntryDate().compareTo(endDate) <= 0 ){
                filteredList.add(e);
            }
        }
       return filteredList;
    }
}
