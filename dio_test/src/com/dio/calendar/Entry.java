package com.dio.calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class for calendar entry
 * Created by yur on 29.04.2014.
 */
public class Entry {

    private String subject;
    private String description;
    private Date startDate;
    private Date endDate;
    private List<String> attenders;
    private List<Notification> notifications;

    {
        attenders = new ArrayList<>(); //?
        notifications = new ArrayList<>(); //?
    }

}

