package shop.ui;

public interface UI {
  public void processMenu(UIMenuInterface menu);
  public String[] processForm(UIFormInterface form);
  public void displayMessage(String message);
  public void displayError(String message);
}
