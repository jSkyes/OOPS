package shop.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

final class TextUI implements UI {
  final BufferedReader _in;
  final PrintStream _out;

  TextUI() {
    _in = new BufferedReader(new InputStreamReader(System.in));
    _out = System.out;
  }

  public void displayMessage(String message) {
    _out.println(message);
  }

  public void displayError(String message) {
    _out.println(message);
  }

  private String getResponse() {
    String result;
    try {
      result = _in.readLine();
    } catch (IOException e) {
      throw new UIError(); // re-throw UIError
    }
    if (result == null) {
      throw new UIError(); // input closed
    }
    return result;
  }

  public void processMenu(UIMenuInterface menu) {
    _out.println(menu.getHeading());
    _out.println("Enter choice by number:");

    for (int i = 1; i < menu.size(); i++) {
      _out.println("  " + i + ". " + menu.getPrompt(i));
    }

    String response = getResponse();
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
    //create an answer array for all the responses
    String[] ans = new String[form.size()];

    _out.println(form.getHeading());
    //iterate through responses and add them to ans
    for (int i = 0; i < form.size(); i++)
    {
      int result = i + 1;
      //build the string
      _out.println("  " + result + ". " + form.getPrompt(i));
      String prompt = getResponse();
      if (!form.checkInput(i, prompt)){
        i--;
        displayError("Invalid input. Please try again.");
      }else{
        //pop it in
        ans[i] = prompt;
      }
    }
    return ans;
  }
}
