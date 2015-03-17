package com.ctn.entity.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Barry on 2014/10/20.
 */
public class LogConstants {

    private static Map<String,String> mapModule;

    public static final String LOG_MODULE_ESL_LABEL = "ESLController";
    public static final String LOG_MODULE_ESL_LABEL_CODE = "esl_label";
    public static final String LOG_MODULE_ESL_EQUIP = "EquipController";
    public static final String LOG_MODULE_ESL_EQUIP_CODE = "esl_equip";
    public static final String LOG_MODULE_SYS_LOG = "LogController";
    public static final String LOG_MODULE_SYS_LOG_CODE = "sys_log";
    public static final String LOG_MODULE_INTER_APP = "AppController";
    public static final String LOG_MODULE_INTER_APP_CODE = "interface_app";
    public static final String LOG_MODULE_INTER_LINK = "LinkController";
    public static final String LOG_MODULE_INTER_LINK_CODE = "interface_link";
    public static final String LOG_MODULE_INTER_REGIST = "RegistController";
    public static final String LOG_MODULE_INTER_REGIST_CODE = "interface_regist";
    public static final String LOG_MODULE_SYS_MENU = "MenuController";
    public static final String LOG_MODULE_SYS_MENU_CODE = "sys_menu";
    public static final String LOG_MODULE_SYS_PARAM = "ParamController";
    public static final String LOG_MODULE_SYS_PARAM_CODE = "sys_param";
    public static final String LOG_MODULE_USER_INFO = "UserInfoController";
    public static final String LOG_MODULE_USER_INFO_CODE = "user_info";
    public static final String LOG_MODULE_USER_RIGHT = "RoleRightController";
    public static final String LOG_MODULE_USER_RIGHT_CODE = "user_right";
    public static final String LOG_MODULE_USER_LOGIN = "PermissionController";
    public static final String LOG_MODULE_USER_LOGIN_CODE = "user_login";

    private static void intModule(){
        if(mapModule == null){
            mapModule = new HashMap<String, String>();
        }
        mapModule.put(LOG_MODULE_USER_LOGIN,LOG_MODULE_USER_LOGIN_CODE);
        mapModule.put(LOG_MODULE_USER_INFO,LOG_MODULE_USER_INFO_CODE);
        mapModule.put(LOG_MODULE_USER_RIGHT,LOG_MODULE_USER_RIGHT_CODE);
        mapModule.put(LOG_MODULE_ESL_EQUIP,LOG_MODULE_ESL_EQUIP_CODE);
        mapModule.put(LOG_MODULE_ESL_LABEL,LOG_MODULE_ESL_LABEL_CODE);
        mapModule.put(LOG_MODULE_SYS_LOG,LOG_MODULE_SYS_LOG_CODE);
        mapModule.put(LOG_MODULE_INTER_APP,LOG_MODULE_INTER_APP_CODE);
        mapModule.put(LOG_MODULE_INTER_LINK,LOG_MODULE_INTER_LINK_CODE);
        mapModule.put(LOG_MODULE_INTER_REGIST,LOG_MODULE_INTER_REGIST_CODE);
        mapModule.put(LOG_MODULE_SYS_MENU,LOG_MODULE_SYS_MENU_CODE);
        mapModule.put(LOG_MODULE_SYS_PARAM,LOG_MODULE_SYS_PARAM_CODE);
    }

    public static String getLogModule(String code){
        if(mapModule == null || mapModule.isEmpty()){
            intModule();
        }
        return mapModule.get(code);
    }

    private static Map<String,String> mapOperate;

    public static final String LOG_OPERATE_DEL = "del";
    public static final String LOG_OPERATE_PUT = "put";
    public static final String LOG_OPERATE_GET = "get";
    public static final String LOG_OPERATE_NEW = "new";
    public static final String LOG_OPERATE_ADD = "add";
    public static final String LOG_OPERATE_DROP = "drop";
    public static final String LOG_OPERATE_POST = "post";
    public static final String LOG_OPERATE_SAVE = "save";
    public static final String LOG_OPERATE_FIND = "find";
    public static final String LOG_OPERATE_LIST = "list";
    public static final String LOG_OPERATE_EDIT = "edit";
    public static final String LOG_OPERATE_CREATE = "create";
    public static final String LOG_OPERATE_UPDATE = "update";
    public static final String LOG_OPERATE_MODIFY = "modify";
    public static final String LOG_OPERATE_DELETE = "delete";
    public static final String LOG_OPERATE_REMOVE = "remove";
    public static final String LOG_OPERATE_ASSIGN = "assign";
    public static final String LOG_OPERATE_INSERT = "insert";
    public static final String LOG_OPERATE_SELECT = "select";
    public static final String LOG_OPERATE_LOGIN= "login";
    public static final String LOG_OPERATE_ADD_CODE = "log_operate_add";
    public static final String LOG_OPERATE_LOGIN_CODE = "log_operate_login";
    public static final String LOG_OPERATE_DEL_CODE = "log_operate_delete";
    public static final String LOG_OPERATE_UPT_CODE = "log_operate_modify";
    public static final String LOG_OPERATE_GET_CODE = "log_operate_get";
    public static final String LOG_OPERATE_ASG_CODE = "log_operate_assign";


    private static void intOperate(){
        if(mapOperate == null){
            mapOperate = new HashMap<String, String>();
        }
        mapOperate.put(LOG_OPERATE_LOGIN,LOG_OPERATE_LOGIN_CODE);
        mapOperate.put(LOG_OPERATE_EDIT,LOG_OPERATE_UPT_CODE);
        mapOperate.put(LOG_OPERATE_UPDATE,LOG_OPERATE_UPT_CODE);
        mapOperate.put(LOG_OPERATE_MODIFY,LOG_OPERATE_UPT_CODE);
        mapOperate.put(LOG_OPERATE_INSERT,LOG_OPERATE_ADD_CODE);
        mapOperate.put(LOG_OPERATE_SELECT,LOG_OPERATE_GET_CODE);
        mapOperate.put(LOG_OPERATE_ADD,LOG_OPERATE_ADD_CODE);
        mapOperate.put(LOG_OPERATE_POST,LOG_OPERATE_ADD_CODE);
        mapOperate.put(LOG_OPERATE_PUT,LOG_OPERATE_UPT_CODE);
        mapOperate.put(LOG_OPERATE_SAVE,LOG_OPERATE_ADD_CODE);
        mapOperate.put(LOG_OPERATE_NEW,LOG_OPERATE_ADD_CODE);
        mapOperate.put(LOG_OPERATE_CREATE,LOG_OPERATE_ADD_CODE);
        mapOperate.put(LOG_OPERATE_DEL,LOG_OPERATE_DEL_CODE);
        mapOperate.put(LOG_OPERATE_DELETE,LOG_OPERATE_DEL_CODE);
        mapOperate.put(LOG_OPERATE_REMOVE,LOG_OPERATE_DEL_CODE);
        mapOperate.put(LOG_OPERATE_DROP,LOG_OPERATE_DEL_CODE);
        mapOperate.put(LOG_OPERATE_GET,LOG_OPERATE_GET_CODE);
        mapOperate.put(LOG_OPERATE_FIND,LOG_OPERATE_GET_CODE);
        mapOperate.put(LOG_OPERATE_LIST,LOG_OPERATE_GET_CODE);
        mapOperate.put(LOG_OPERATE_ASSIGN,LOG_OPERATE_ASG_CODE);
    }

    public static String findOperateCode(String method){
        String lowerName = method.trim().toLowerCase();
        String code = null;
        if(lowerName.length() >= 3){
            String words_3 = lowerName.substring(0,3);
            code = getLogOperate(words_3);
            if(code != null && !"".equals(code)){return code;}
        }
        if(lowerName.length() >= 4){
            String words_4 = lowerName.substring(0,4);
            code = getLogOperate(words_4);
            if(code != null && !"".equals(code)){return code;}
        }
        if(lowerName.length() >= 5){
            String words_5 = lowerName.substring(0,5);
            code = getLogOperate(words_5);
            if(code != null && !"".equals(code)){return code;}
        }
        if(lowerName.length() >= 6){
            String words_6 = lowerName.substring(0,6);
            code = getLogOperate(words_6);
            if(code != null && !"".equals(code)){return code;}
        }
        return code;
    }

    public static String getLogOperate(String code){
        if(mapOperate == null || mapOperate.isEmpty()){
            intOperate();
        }
        return mapOperate.get(code.toLowerCase());
    }

    public static final String LOG_MODULE_TYPE = "logModule";
    public static final String LOG_OPERATE_TYPE = "logOperate";

    public static final String LOG_SAVE_DESC = "System Log";

}
