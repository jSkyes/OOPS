package shop.ui;

 abstract class UIFormMenu {
        private final String _heading;
        private final Pair[] _form;

        UIFormMenu(String heading, Pair[] menu) {
            _heading = heading;
            _form = menu;
        }

        String get_heading(){
            return _heading;
        }

        Pair[] getPair(){
            return _form;
        }

        public int size() {
            return _form.length;
        }

        public String getHeading() {
            return _heading;
        }

        public String getPrompt(int i) {
            return (String) _form[i].prompt;
        }
}
