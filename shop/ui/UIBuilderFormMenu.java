package shop.ui;

import java.util.ArrayList;
import java.util.List;

abstract class UIBuilderFormMenu {
    private final List _menu;

    UIBuilderFormMenu() {
        _menu = new ArrayList();
    }

    List getMenu(){
        return _menu;
    }

    public <U> void add(String prompt, U action) {
        if (null == action)
            throw new IllegalArgumentException();
        _menu.add(new Pair(prompt, action));
    }
}
