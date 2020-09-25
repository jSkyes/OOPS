package shop.ui;

public interface UIMenuInterface {

//    void UIMenu(String heading, Pair[] menu);
    int size();
    String getHeading();
    String getPrompt(int i);
    void runAction(int i);
}
