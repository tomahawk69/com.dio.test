package com.dio.calendar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Class for calendar entry
 *
 * spring, maven
 *
 * Main class
 * UUID (class)
 *
 * tests
 * interfaces
 *
 *
 * Created by yur on 29.04.2014.
 */
public class Entry {

    private final String subject;
    private final String description;
    private final Date startDate;
    private final Date endDate;
    private final List<String> attenders;
    private final List<Notification> notifications;

    private Entry(Builder builder) {
        this.subject = builder.subject;
    }

    public class Builder {
        private String subject;
        private String description;
        private Date startDate;
        private Date endDate;
        private List<String> attenders;
        private List<Notification> notifications;

        public Builder() {
        }

        public Builder(Entry oldEntry) {
            //this.subject = oldEntry.get
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder startDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder attenders(List<String> attenders) {
            this.attenders = attenders;
            return this;
        }

        public Builder attenders(String... attenders) {
            if (this.attenders == null)
                this.attenders = Arrays.asList(attenders);

            this.attenders.addAll(Arrays.asList(attenders));
            return this;
        }

        public Builder notifications(List<Notification> notifications) {
            this.notifications = notifications;
            return this;
        }

        public Entry build() {
            return new Entry(this);
        }
    }

}

