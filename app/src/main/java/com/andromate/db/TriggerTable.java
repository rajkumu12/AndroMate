package com.andromate.db;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class TriggerTable {
    @PrimaryKey(autoGenerate = true)
    int id=0;

    @ColumnInfo(name = "trigger_name")
    public String trigger_name;

    @ColumnInfo(name = "trigger_description")
    public String lastName;

    @ColumnInfo(name = "icon")
    public int icon;

}
