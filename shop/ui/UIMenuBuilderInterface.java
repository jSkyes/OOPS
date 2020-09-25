package shop.ui;

public interface UIMenuBuilderInterface {
    static UIMenuBuilder UIMenuBuilder() {
        return new UIMenuBuilder();
    }

    UIMenuInterface toUIMenu(String heading);

    <U> void add(String prompt, U test);
}
