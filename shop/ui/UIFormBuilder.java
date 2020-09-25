package shop.ui;

import java.util.ArrayList;
import java.util.List;

final class UIFormBuilder extends UIBuilderFormMenu implements UIFormBuilderInterface {

    public UIFormBuilder() {
        super();
    }

    public UIFormInterface toUIForm(String heading) {
        List menu = getMenu();
        if (null == heading)
            throw new IllegalArgumentException();
        if (menu.size() < 1)
            throw new IllegalStateException();
        Pair[] array = new Pair[menu.size()];
        for (int i = 0; i < menu.size(); i++)
            array[i] = (Pair) (menu.get(i));
        return new UIForm(heading, array);
    }
}
