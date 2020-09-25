package shop.ui;

import javax.swing.JOptionPane;
//import java.io.IOException;

final class PopupUI implements UI {
  PopupUI() {}

  public void displayMessage(String message) {
    JOptionPane.showMessageDialog(null,message);
  }

  public void displayError(String message) {
    JOptionPane.showMessageDialog(null,message,"Error",JOptionPane.ERROR_MESSAGE);
  }

  public void processMenu(UIMenuInterface menu) {
    StringBuffer b = new StringBuffer();
    b.append(menu.getHeading());
    b.append("\n");
    b.append("Enter choice by number:");
    b.append("\n");

    for (int i = 1; i < menu.size(); i++) {
      b.append("  " + i + ". " + menu.getPrompt(i));
      b.append("\n");
    }

    String response = JOptionPane.showInputDialog(b.toString());
    int selection;
    try {
      selection = Integer.parseInt(response, 10);
      if ((selection < 0) || (selection >= menu.size()))
        selection = 0;
    } catch (NumberFormatException e) {
      selection = 0;
    }

    menu.runAction(selection);
  }

  public String[] processForm(UIFormInterface form) {
    //create a buffer to collect the answer
    StringBuffer b = new StringBuffer();
    //snag the heading
    b.append(form.getHeading());
    b.append("\nEnter choice by number: \n");

    //create an answer array for all the responses
    String[] ans = new String[form.size()];

    b.append(form.getHeading());

    //iterate through responses and add them to ans
    for (int i = 0; i < form.size(); i++)
    {
      String prompt = JOptionPane.showInputDialog(form.getPrompt(i));
      if (!form.checkInput(i, prompt)){
        displayError("Invalid input. Try again.");
        i--;
      }else{
        //pop it in
        ans[i] = prompt;
      }
    }
    return ans;
  }
}
