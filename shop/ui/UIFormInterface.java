package shop.ui;

public interface UIFormInterface {
    int size();
    String getHeading();
    String getPrompt(int i);
    boolean checkInput(int i, String input);
}
