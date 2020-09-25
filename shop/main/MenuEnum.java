package shop.main;

import shop.command.Command;
import shop.data.Data;
import shop.data.Inventory;
import shop.data.Video;
import shop.ui.UIFormBuilderInterface;

 class MenuEnum {
    enum Menu{
        ADD_REMOVE,
        CHECK_IN,
        CHECK_OUT,
        PRINT,
        CLEAR,
        UNDO,
        REDO,
        PRINT_TOP,
        EXIT,
        BOGUS
    }

    Menu menuType;
    MenuEnum (Menu type){
        menuType = type;
    }
    public boolean run(){
        switch (menuType){
            case ADD_REMOVE:
                break;
            case CHECK_IN:
                break;
            case CHECK_OUT:
                break;
            case PRINT:
                break;
            case CLEAR:
                break;
            case UNDO:
                break;
            case REDO:
                break;
            case PRINT_TOP:
                break;
            case EXIT:
                break;
            case BOGUS:
                break;
        }
        //if nothing else return false
        return false;
    }
}
