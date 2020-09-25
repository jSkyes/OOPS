package shop.ui;

public interface UIFormBuilderInterface {
    static UIFormBuilder UIFormBuilder() {
        return new UIFormBuilder();
    }

    UIFormInterface toUIForm(String heading);

    <U> void add(String prompt, U action);

}
