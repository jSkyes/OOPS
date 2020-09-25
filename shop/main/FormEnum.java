package shop.main;

import shop.ui.UIFormBuilderInterface;
import shop.ui.UIFormInterface;

import static shop.ui.UIFormBuilderInterface.UIFormBuilder;

class FormEnum {
    enum Form{
        VIDEO,
        ADD_REMOVE
    }

    Form formType;
    UIFormInterface finishedForm;
    FormEnum(Form type){
        formType = type;
        run();
    }
    public void run(){
        UIFormBuilderInterface f = UIFormBuilder();
        switch (formType){
            case VIDEO:
                f.add("Title", new TestsEnum(TestsEnum.tests.STRING));
                f.add("Year", new TestsEnum(TestsEnum.tests.YEAR));
                f.add("Director", new TestsEnum(TestsEnum.tests.STRING));
                finishedForm = f.toUIForm("Enter Video");
            case ADD_REMOVE:
                f.add("Number of copies to add/remove", new TestsEnum(TestsEnum.tests.NUMBER));
        }
    }

    UIFormInterface getFinishedForm(){
        return finishedForm;
    }
}
