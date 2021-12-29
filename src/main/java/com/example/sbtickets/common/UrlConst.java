package com.example.sbtickets.common;

public class UrlConst {

    /**
     * API login hệ thống Sbtickets
     */

    public static final String HOME = "/Sbtickets";
    public static final String LOGIN_SBTICKETS = HOME + "/login";

    public static final String USER_REGISTER = HOME + "/register";

    public static class HOMEADIM{

        /**
         * Home url hệ thống Sbtickets (ROLE_ADMIN)
         */
        public static final String HOMEADIM = HOME + "/admin";
        /**
         * API getAllDriver CuongNv
         */
        public static final String GET_DRIVER = HOMEADIM + "/getDriver";

        public static final String GET_DRIVER_BY_ID = HOMEADIM + "/getDriver/{id}";

        /**
         * API find name Driver CuongNv
         */
        public static final String FIND_DRIVER = HOMEADIM + "/findDriver";

        public static final String CREATE_DRIVER = HOMEADIM + "/createDriver";

        public static final String UPDATE_DRIVER = HOMEADIM + "/updateDriver/{id}";

        public static final String DELETE_DRIVER = HOMEADIM + "/deleteDriver/{id}";

        public static final String DELETE_DRIVERS = HOMEADIM + "/deleteDrivers";

        /**
         * API getAllBus SonPK
         */
        public static final String GET_BUS = HOMEADIM + "/getBus";

        public static final String FIND_BUS = HOMEADIM + "/findBus/{id}";

        public static final String CREATE_BUS = HOMEADIM + "/createBus";

        public static final String UPDATE_BUS = HOMEADIM + "/updateBus/{id}";

        public static final String DELETE_BUS = HOMEADIM + "/deleteBus/{id}";

        public static final String DELETE_BUSES = HOMEADIM + "/deleteBuses";

        public static final String GET_BUS_BY_ID = HOMEADIM + "/getBus/{id}";

        /**
         * API find name Line Bus SonPK
         */
        public static final String GET_LINE_BUS = HOMEADIM + "/getLineBus";

        public static final String GET_LINE_BUS_BY_ID = HOMEADIM + "/getLineBus/{id}";

        public static final String CREATE_LINE_BUS = HOMEADIM + "/createLineBus";

        public static final String UPDATE_LINE_BUS = HOMEADIM + "/updateLineBus/{id}";

        public static final String DELETE_LINE_BUS = HOMEADIM + "/deleteLineBus/{id}";

        public static final String DELETE_LINE_BUSES = HOMEADIM + "/deleteLineBuses";


        /**
         * API TripBusAddress SonPK
         */
        public static final String GET_TRIPBUS_ADDRESS = HOMEADIM + "/getTripBusAddress";

        public static final String GET_TRIPBUS_ADDRESS_BY_ID = HOMEADIM + "/getTripBusAddress/{id}";

        public static final String CREATE_TRIPBUS_ADDRESS = HOMEADIM + "/createTripBusAddress";

        public static final String UPDATE_TRIPBUS_ADDRESS = HOMEADIM + "/updateTripBusAddress/{id}";

        public static final String DELETE_TRIPBUS_ADDRESS = HOMEADIM + "/deleteTripBusAddress/{id}";

        public static final String DELETE_TRIPBUS_ADDRESSES = HOMEADIM + "/deleteTripBusAddresses";
        /**
         * API find name TripBus CuongNv
         */
        public static final String CREATE_TRIP_BUS = HOMEADIM + "/createTripBus";

        public static final String EDIT_TRIP_BUS = HOMEADIM + "/editTripBus";

        public static final String DELETE_TRIP_BUS = HOMEADIM + "/deleteTripBus";

        public static final String FIND_TRIP_BUS = HOMEADIM + "/findTripBus/{id}";

        public static final String GET_ALL_TRIP_BUS = HOMEADIM + "/getAllTripBus";

        /**
         * API find name thong ke bang luong CuongNv
         */
        public static final String GET_WAGES_DRIVER = HOMEADIM + "/getListWages";

        public static final String GET_LOACTION = HOMEADIM + "/getLocation";

        /**
         * API thong ke chuyen xe di duoc trong 30 ngay lien tiep CuongNv
         */
        public static final String COUNT_TRIP_BY_WEEK = HOMEADIM + "/getCountTripByWeek";

        /**
         * API thong ke chuyen xe da co bao nhieu nguoi dat coc CuongNv
         */
        public static final String GET_TRIPBUS_CUSTOMER = HOMEADIM + "/getTripBusCustomer/{id}";
        /**
         * API thong ke doanh thu xe theo thoi gian SonPK
         */
        public static final String GET_REVENUE_BUS = HOMEADIM + "/getRevenueBus";

        public static final String GET_REVENUE_BUS_BY_ID = HOMEADIM + "/getRevenueBus/{busId}";


    }

    public static class HOME_USER{
        public static final String HOME_USER = HOME + "/user";

        public static final String GET_USER = HOME_USER + "/getUser";

        public static final String BOOK_SEAT = HOME_USER + "/bookSeat";

        public static final String FIND_BY_TRIPBUS = HOME_USER + "/findByTripBus";

        public static final String GET_LOACTION = HOME_USER + "/getLocation";

        /**
         * API Edit Customer User SonPK
         */
        public static final String UPDATE_CUSTOMER = HOME_USER + "/updateCustomer/{id}";

        public static final String UPDATE_ACCOUNT = HOME_USER + "/update/{id}";

        public static final String GET_CUSTOMER_DETAIL = HOME_USER + "/customerDetail/{id}";

    }
}
