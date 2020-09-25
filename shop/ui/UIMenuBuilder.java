package shop.ui;

import java.util.ArrayList;
import java.util.List;

class UIMenuBuilder extends UIBuilderFormMenu implements UIMenuBuilderInterface {

    UIMenuBuilder() {
        super();
    }

    public UIMenu toUIMenu(String heading) {
        List menu = getMenu();
        if (null == heading)
            throw new IllegalArgumentException();
        if (menu.size() <= 1)
            throw new IllegalStateException();
        shop.ui.Pair[] array = new shop.ui.Pair[menu.size()];
        for (int i = 0; i < menu.size(); i++)
            array[i] = (Pair) (menu.get(i));
        return new UIMenu(heading, array);
    }
}
