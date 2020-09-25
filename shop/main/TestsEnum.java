package shop.main;

import shop.ui.UIFormTest;

import static shop.main.TestsEnum.tests.NUMBER;
import static shop.main.TestsEnum.tests.YEAR;

class TestsEnum implements UIFormTest {
    enum tests{
        YEAR,
        NUMBER,
        STRING
    }

    tests testName;
    TestsEnum(tests type){
        testName = type;
    }

    public boolean run(String input){
        switch (testName){
            case YEAR:
                try {
                    int i = Integer.parseInt(input);
                    return i > 1800 && i < 5000;
                } catch (NumberFormatException e) {
                    return false;
                }
            case NUMBER:
                try {
                    Integer.parseInt(input);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            case STRING:
                return ! "".equals(input.trim());
        }
        //if nothing else return false
        return false;
    }

}
