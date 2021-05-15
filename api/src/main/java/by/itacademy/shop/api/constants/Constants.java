package by.itacademy.shop.api.constants;

import by.itacademy.shop.utilenum.Lang;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    public static final String ROLE_USER="ROLE_USER";
    public static final String ROLE_ADMIN="ROLE_ADMIN";
    public static final String NEW_USER_DEFAULT_ROLE=ROLE_USER;

    private static final String ADMIN="/admin";
    public static final String ROLE_ADMIN_ACCOUNT_PRODUCTS=ADMIN+"/products";
    public static final String ROLE_ADMIN_ACCOUNT_PROVIDERS="/admin/providers";
    public static final String ROLE_ADMIN_ACCOUNT_PHOTOS="/admin/photos";
    public static final String ROLE_ADMIN_ACCOUNT_CATEGORIES="/admin/categories";
    public static final String ROLE_ADMIN_ACCOUNT_USERS="/admin/users";
    public static final String ROLE_ADMIN_ACCOUNT_UPLOAD_FILE="/admin/uploadfile";

    public static final String MAIN_PAGE="/";
//    public static final String

    public static final Lang GLOBAL_LANG=Lang.RU;
    public static final int PRODUCT_PAGE_SIZE=20;
}
