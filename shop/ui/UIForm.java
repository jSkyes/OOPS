package shop.ui;

/**
 * @see UIFormBuilder
 */
 final class UIForm extends UIFormMenu implements UIFormInterface{

  UIForm(String heading, Pair[] menu) {
      super(heading, menu);
  }
  public boolean checkInput(int i, String input) {
    Pair[] form = getPair();
    if (null == form[i])
      return true;
    UIFormTest test = (UIFormTest) form[i].action;
    return test.run(input);
  }
}
