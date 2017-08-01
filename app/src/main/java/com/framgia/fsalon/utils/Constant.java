package com.framgia.fsalon.utils;

import java.sql.Timestamp;

/**
 * Created by MyPC on 20/07/2017.
 */
public class Constant {
    public static final String END_POINT_URL = "http://fsalon.dinhtai.com/";
    public static final int OUT_OF_INDEX = -1;
    public static final long A_DAY = 1000 *  60 * 60 * 24;
    public static final String DATE_FORMAT_MM_DD_YYYY = "MM/dd/yyyy";
    public static final String DAY_OF_WEEK_FORMAT = "EEEE";
    public static final int FULL = 0;
    public static final int AVAILABLE = 1;
    public static final int OFF_WORK = 2;
    public static final String BUNDLE_ORDER = "BUNDLE_ORDER";

    public class ApiParram {
        public static final String DEPARTMENT_ID = "department_id";
        public static final String STYLIST_ID = "stylist_id";
        public static final String DATE = "date";
        public static final String PHONE = "phone";
        public static final String NAME = "name";
        public static final String RENDER_BOOKING_ID = "render_booking_id";
        public static final String STYLIST_CHOSEN = "stylist_chosen";
    }

    public class Permission {
        public static final int PERMISSION_NOMAL = 0;
        public static final int PERMISSION_ASSISTANT = 1;
        public static final int PERMISSION_MAIN_WORKER = 2;
        public static final int PERMISSION_ADMIN = 3;
    }
}
