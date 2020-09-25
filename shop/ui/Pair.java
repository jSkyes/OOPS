package shop.ui;

import java.util.concurrent.Callable;

final class Pair <U , S>{
    final S prompt;
    final U action;

    Pair(S thePrompt, U theAction){
        prompt = thePrompt;
        action = theAction;
    }

    U getAction(){
        return action;
    }

    S getPrompt(){
        return prompt;
    }
}
