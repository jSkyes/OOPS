package shop.ui;

/**
 * @see UIMenuBuilder
 */
 final class UIMenu extends UIFormMenu implements UIMenuInterface{

    UIMenu(String heading, Pair[] menu) {
        super(heading, menu);
    }

    public void runAction(int i) {
        Pair[] menu = getPair();
        UIMenuAction action = (UIMenuAction) menu[i].action;
        action.run();
    }
}
