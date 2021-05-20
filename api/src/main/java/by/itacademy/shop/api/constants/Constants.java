package by.itacademy.shop.api.constants;

import by.itacademy.shop.utilenum.Lang;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    public static final Lang GLOBAL_LANG=Lang.RU;
    public static final int PRODUCT_PAGE_SIZE=20;

    public static final String HIBERNATE_DIALECT="hibernate.dialect";
    public static final String HIBERNATE_SHOW_SQL="hibernate.show_sql";
    public static final String HIBERNATE_FORMAT_SQL="hibernate.format_sql";


    public static final String ROLE_USER="ROLE_USER";
    public static final String ROLE_ADMIN="ROLE_ADMIN";
    public static final String NEW_USER_DEFAULT_ROLE=ROLE_USER;
    //------------------Mappings----------------------------
    public static final String REDIRECT="redirect:";

    private static final String ROOT="";
    private static final String CREATE="/create";
    private static final String UPDATE="/update";
    private static final String DELETE="/delete";

    private static final String ADMIN="/admin";

    private static final String ROLE_ADMIN_ACCOUNT_PRODUCTS=ADMIN+"/products";
    public static final String ROLE_ADMIN_ACCOUNT_PRODUCTS_ROOT=ROLE_ADMIN_ACCOUNT_PRODUCTS+ROOT;
    public static final String ROLE_ADMIN_ACCOUNT_PRODUCTS_UPLOAD_FILE=ROLE_ADMIN_ACCOUNT_PRODUCTS+"/upload-file";
    public static final String ROLE_ADMIN_ACCOUNT_PRODUCTS_CREATE=ROLE_ADMIN_ACCOUNT_PRODUCTS+CREATE;
    public static final String ROLE_ADMIN_ACCOUNT_PRODUCTS_CREATE_LIST=ROLE_ADMIN_ACCOUNT_PRODUCTS+CREATE+"/list";
    public static final String ROLE_ADMIN_ACCOUNT_PRODUCTS_UPDATE=ROLE_ADMIN_ACCOUNT_PRODUCTS+UPDATE;
    public static final String ROLE_ADMIN_ACCOUNT_PRODUCTS_DELETE=ROLE_ADMIN_ACCOUNT_PRODUCTS+DELETE;

    private static final String ROLE_ADMIN_ACCOUNT_PROVIDERS=ADMIN+"/providers";
    public static final String ROLE_ADMIN_ACCOUNT_PROVIDERS_ROOT=ROLE_ADMIN_ACCOUNT_PROVIDERS+ROOT;
    public static final String ROLE_ADMIN_ACCOUNT_PROVIDERS_CREATE=ROLE_ADMIN_ACCOUNT_PROVIDERS+CREATE;
    public static final String ROLE_ADMIN_ACCOUNT_PROVIDERS_UPDATE=ROLE_ADMIN_ACCOUNT_PROVIDERS+UPDATE;
    public static final String ROLE_ADMIN_ACCOUNT_PROVIDERS_DELETE=ROLE_ADMIN_ACCOUNT_PROVIDERS+DELETE;

    private static final String ROLE_ADMIN_ACCOUNT_PHOTOS=ADMIN+"/photos";
    public static final String ROLE_ADMIN_ACCOUNT_PHOTOS_ROOT=ROLE_ADMIN_ACCOUNT_PHOTOS+ROOT;
    public static final String ROLE_ADMIN_ACCOUNT_PHOTOS_CREATE=ROLE_ADMIN_ACCOUNT_PHOTOS+CREATE;
    public static final String ROLE_ADMIN_ACCOUNT_PHOTOS_UPDATE=ROLE_ADMIN_ACCOUNT_PHOTOS+UPDATE;
    public static final String ROLE_ADMIN_ACCOUNT_PHOTOS_DELETE=ROLE_ADMIN_ACCOUNT_PHOTOS+DELETE;

    private static final String ROLE_ADMIN_ACCOUNT_CATEGORIES=ADMIN+"/categories";
    public static final String ROLE_ADMIN_ACCOUNT_CATEGORIES_ROOT=ROLE_ADMIN_ACCOUNT_CATEGORIES+ROOT;
    public static final String ROLE_ADMIN_ACCOUNT_CATEGORIES_CREATE=ROLE_ADMIN_ACCOUNT_CATEGORIES+CREATE;
    public static final String ROLE_ADMIN_ACCOUNT_CATEGORIES_UPDATE=ROLE_ADMIN_ACCOUNT_CATEGORIES+UPDATE;
    public static final String ROLE_ADMIN_ACCOUNT_CATEGORIES_DELETE=ROLE_ADMIN_ACCOUNT_CATEGORIES+DELETE;

    private static final String ROLE_ADMIN_ACCOUNT_USERS=ADMIN+"/users";
    public static final String ROLE_ADMIN_ACCOUNT_USERS_ID=ROLE_ADMIN_ACCOUNT_USERS+"/{id}";
    public static final String ROLE_ADMIN_ACCOUNT_USERS_ROOT=ROLE_ADMIN_ACCOUNT_USERS+ROOT;
    public static final String ROLE_ADMIN_ACCOUNT_USERS_SET_ROLE=ROLE_ADMIN_ACCOUNT_USERS+"/set-role";
    public static final String ROLE_ADMIN_ACCOUNT_USERS_DELETE_ROLE=ROLE_ADMIN_ACCOUNT_USERS+"/delete-role";
    public static final String ROLE_ADMIN_ACCOUNT_USERS_DELETE=ROLE_ADMIN_ACCOUNT_USERS+DELETE;


    private static final String ROLE_GUEST_MAIN="";
    public static final String ROLE_GUEST_MAIN_ROOT=ROLE_GUEST_MAIN+ROOT;
    public static final String ROLE_GUEST_MAIN_SIGN_UP=ROLE_GUEST_MAIN+"/sing-up";

    private static final String ROLE_GUEST_PRODUCT_SEARCH="/products";
    public static final String ROLE_GUEST_PRODUCT_SEARCH_PAGES=ROLE_GUEST_PRODUCT_SEARCH+"/pages";
    public static final String ROLE_GUEST_PRODUCT_SEARCH_PAGES_NUM=ROLE_GUEST_PRODUCT_SEARCH_PAGES+"/{num}";
    public static final String ROLE_GUEST_PRODUCT_SEARCH_PAGES_NEXT=ROLE_GUEST_PRODUCT_SEARCH_PAGES+"/next";
    public static final String ROLE_GUEST_PRODUCT_SEARCH_PAGES_PREVIOUS=ROLE_GUEST_PRODUCT_SEARCH_PAGES+"/previous";


    private static final String ROLE_USER_ACCOUNT_USER="/user";
    public static final String ROLE_USER_ACCOUNT_USER_ACCOUNT=ROLE_USER_ACCOUNT_USER+"/account";
    public static final String ROLE_USER_ACCOUNT_USER_UPDATE=ROLE_USER_ACCOUNT_USER+UPDATE;
    public static final String ROLE_USER_ACCOUNT_USER_DELETE=ROLE_USER_ACCOUNT_USER+DELETE;
    public static final String ROLE_USER_ACCOUNT_USER_ADD_ORDER=ROLE_USER_ACCOUNT_USER+"/add-order";



}
