package com.example.projekt_kalendarz;

import java.util.Date;

public class Task
{
    private String taskName;
    private Date dateStart;
    private Date dateEnd;
    private String localization;
    private String notes;
    //...

    public Task(String taskName,
                Date dateStart,
                Date dateEnd,
                String localization,
                String notes)
    {
        this.taskName = taskName;
        this.dateStart = dateStart;
        this.notes = notes;
        this.localization = localization;
        this.dateEnd = dateEnd;
    }

    public String getTaskName()
    {
        return this.taskName;
    }

    public String getNotes()
    {
        return notes;
    }

    public Date getDateStart()
    {
        return dateStart;
    }

    public Date getDateEnd()
    {
        return dateEnd;
    }

    public String getLocalization()
    {
        return localization;
    }

}
