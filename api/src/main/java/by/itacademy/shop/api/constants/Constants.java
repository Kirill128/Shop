package by.itacademy.shop.api.constants;

import by.itacademy.shop.utilenum.Lang;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    public static final Lang GLOBAL_LANG=Lang.RU;
    public static final int PRODUCT_PAGE_SIZE=20;


    public static final String ROLE_USER="ROLE_USER";
    public static final String ROLE_ADMIN="ROLE_ADMIN";
    public static final String NEW_USER_DEFAULT_ROLE=ROLE_USER;
    //------------------Mappings----------------------------
    private static final String ROOT="";
    private static final String CREATE="/create";
    private static final String UPDATE="/update";
    private static final String DELETE="/delete";

    private static final String ADMIN="/admin";
    public static final String ROLE_ADMIN_ACCOUNT_PRODUCTS=ADMIN+"/products";
    public static final String ROLE_ADMIN_ACCOUNT_PRODUCTS_ROOT=ROOT;
    public static final String ROLE_ADMIN_ACCOUNT_PRODUCTS_UPLOAD_FILE="/upload-file";
    public static final String ROLE_ADMIN_ACCOUNT_PRODUCTS_CREATE=CREATE;
    public static final String ROLE_ADMIN_ACCOUNT_PRODUCTS_CREATE_LIST=CREATE+"/list";
    public static final String ROLE_ADMIN_ACCOUNT_PRODUCTS_UPDATE=UPDATE;
    public static final String ROLE_ADMIN_ACCOUNT_PRODUCTS_DELETE=DELETE;


    public static final String ROLE_ADMIN_ACCOUNT_PROVIDERS=ADMIN+"/providers";
    public static final String ROLE_ADMIN_ACCOUNT_PROVIDERS_ROOT=ROOT;
    public static final String ROLE_ADMIN_ACCOUNT_PROVIDERS_CREATE=CREATE;
    public static final String ROLE_ADMIN_ACCOUNT_PROVIDERS_UPDATE=UPDATE;
    public static final String ROLE_ADMIN_ACCOUNT_PROVIDERS_DELETE=DELETE;

    public static final String ROLE_ADMIN_ACCOUNT_PHOTOS=ADMIN+"/photos";
    public static final String ROLE_ADMIN_ACCOUNT_PHOTOS_ROOT=ADMIN+"/photos";
    public static final String ROLE_ADMIN_ACCOUNT_PHOTOS_CREATE=ADMIN+"/photos";
    public static final String ROLE_ADMIN_ACCOUNT_PHOTOS_UPDATE=ADMIN+"/photos";
    public static final String ROLE_ADMIN_ACCOUNT_PHOTOS_DELETE=ADMIN+"/photos";


    public static final String ROLE_ADMIN_ACCOUNT_CATEGORIES=ADMIN+"/categories";

    public static final String ROLE_ADMIN_ACCOUNT_USERS=ADMIN+"/users";


    public static final String ROLE_GUEST_MAIN="";
    public static final String ROLE_GUEST_MAIN_ROOT="";
    public static final String ROLE_GUEST_MAIN_SIGN_UP="/sing-up";

    public static final String ROLE_GUEST_PRODUCT_SEARCH="/products";
    public static final String ROLE_GUEST_PRODUCT_SEARCH_PAGES="/pages";
    public static final String ROLE_GUEST_PRODUCT_SEARCH_PAGES_NUM=ROLE_GUEST_PRODUCT_SEARCH_PAGES+"/{num}";
    public static final String ROLE_GUEST_PRODUCT_SEARCH_PAGES_NEXT=ROLE_GUEST_PRODUCT_SEARCH_PAGES+"/next";
    public static final String ROLE_GUEST_PRODUCT_SEARCH_PAGES_PREVIOUS=ROLE_GUEST_PRODUCT_SEARCH_PAGES+"/previous";


    public static final String ROLE_USER_ACCOUNT_USER="/user";
    public static final String ROLE_USER_ACCOUNT_USER_ACCOUNT="/account";
    public static final String ROLE_USER_ACCOUNT_USER_UPDATE=UPDATE;
    public static final String ROLE_USER_ACCOUNT_USER_DELETE=DELETE;
    public static final String ROLE_USER_ACCOUNT_USER_ADD_ORDER="/add-order";



}
