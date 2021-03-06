package com.example.application.connections;

public class ProcedurList {

    public static final String GET_ALL_CLIENT = "{call get_all_clients ()}";
    public static final String GET_ALL_MASTERS = "{call get_all_masters ()}";
    public static final String GET_ALL_PROPS = "{call get_all_props ()}";
    public static final String GET_ALL_TYPES = "{call get_all_types ()}";
    public static final String GET_ALL_WAYBILLS = "{call get_all_waybills ()}";

    public static final String GET_WAYBILLS_WITH_NAMES = "{call get_waybills_with_names()}";
    public static final String GET_PROPS_WITH_NAMES = "{call get_props_with_names()}";

    public static final String CLIENT_REPORT = "{call client_report (?)}";
    public static final String PROPS_REPORT = "{call props_report (?)}";
    public static final String MASTER_REPORT = "{call get_master_report(?)}";
    public static final String CLIENT_PROP_REPORT = "{call client_bought_report(?)}";

    public static final String GET_CART = "{call get_cart(?)}";
    public static final String GET_ONE_WAYBILL = "{call get_one_waybill(?)}";

    public static final String UPDATE_PROP_PRICE="{call updateprice(?,?)}";
    public static final String UPDATE_ORDER_STATUS = "{call close_order(?)}";
    public static final String UPDATE_CLIENT_DISCOUNT = "{call updateclientdisc(?)}";

    public static final String NEW_CLIENT = "{call new_client(?,?)}";
    public static final String NEW_PROP = "{call new_prop(?,?,?)}";
    public static final String NEW_CART_POS = "{call new_cart_memb(?,?,?)}";
    public static final String NEW_MASTER = "{call new_master(?)}";
    public static final String NEW_TYPE = "{call new_type(?)}";
    public static final String NEW_WAYBILL = "{call new_waybill(?,?)}";
    public static final String NEW_LOGER_TEXT = "{call new_loger_row(?)}";
    public static final String ADD_NUMBER_OF_PROPS = "{call update_numberof(?,?)}";
    public static final String REMOVE_NUMBER_OF_PROPS = "{call update_numberof_remove(?,?)}";
    public static final String ADD_NEW_CRAFT_ROW = "{call newcraftstory(?,?,?)}";


}
