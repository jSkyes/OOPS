package shop.main;

import shop.ui.*;
import shop.data.Data;
import shop.data.Inventory;
import shop.data.Video;
import shop.data.Record;
import shop.command.Command;
import java.util.Iterator;
import java.util.Comparator;

import static shop.ui.UIFormBuilderInterface.UIFormBuilder;

class Control {
  private static final int EXITED = 0;
  private static final int EXIT = 1;
  private static final int START = 2;
  private static final int NUMSTATES = 10;
  private UIMenuInterface[] _menus;
  private int _state;
    
  private Inventory _inventory;
  private UI _ui;
  
  Control(Inventory inventory, UI ui) {
    _inventory = inventory;
    _ui = ui;

    _menus = new UIMenuInterface[NUMSTATES];
    _state = START;
    addSTART(START);
    addEXIT(EXIT);
  }
  Inventory get_inventory(){
    return _inventory;
  }
  void run() {
    try {
      while (_state != EXITED) {
        _ui.processMenu(_menus[_state]);
      }
    } catch (Error e) {
      _ui.displayError("UI closed");
    }
  }
  
  private void addSTART(int stateNum) {
    UIMenuBuilderInterface m = UIMenuBuilderInterface.UIMenuBuilder();
    
    m.add("Default",
      new UIMenuAction() {
        public void run() {
          _ui.displayError("doh!");
        }
      });
    m.add("Add/Remove copies of a video",
      new UIMenuAction() {
        public void run() {
          String[] result1 = _ui.processForm(new FormEnum(FormEnum.Form.VIDEO).getFinishedForm());
          Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);

          UIFormBuilderInterface f = UIFormBuilderInterface.UIFormBuilder();
          f.add("Number of copies to add/remove", new TestsEnum(TestsEnum.tests.NUMBER));
          String[] result2 = _ui.processForm(f.toUIForm(""));
                                             
          Command c = Data.newAddCmd(_inventory, v, Integer.parseInt(result2[0]));
          if (! c.run()) {
            _ui.displayError("Command failed");
          }
        }
      });
    m.add("Check in a video",
      new UIMenuAction() {
        public void run() {
          //get the results from the video form
          String[] result = _ui.processForm( new FormEnum(FormEnum.Form.VIDEO).getFinishedForm() );
          //make a video from it, 0 is title, 1 is year, 2 is director
          Video vid = Data.newVideo(result[0], Integer.parseInt(result[1]), result[2]);
          //checkInCmd
          Command checkInCmd = Data.newInCmd(_inventory, vid);
          //check and report back for errors
          if (checkInCmd.run() == false)
          {
            _ui.displayError("Command failed");
          }
        }
      });
    m.add("Check out a video",
      new UIMenuAction() {
        public void run() {
          //get the results from the video form
          String[] result = _ui.processForm(new FormEnum(FormEnum.Form.VIDEO).getFinishedForm());
          //make a video from it, 0 is title, 1 is year, 2 is director
          Video vid = Data.newVideo(result[0], Integer.parseInt(result[1]),result[2]);
          // checkOutCmd
          Command checkOutCmd = Data.newOutCmd(_inventory, vid);
          //checking and report back for errors
          if (checkOutCmd.run() == false)
          {
            _ui.displayError("Command failed");
          }
        }
      });
    m.add("Print the inventory",
      new UIMenuAction() {
        public void run() {
          _ui.displayMessage(_inventory.toString());
        }
      });
    m.add("Clear the inventory",
      new UIMenuAction() {
        public void run() {
          if (!Data.newClearCmd(_inventory).run()) {
            _ui.displayError("Command failed");
          }
        }
      });
    m.add("Undo",
      new UIMenuAction() {
        public void run() {
          if (!Data.newUndoCmd(_inventory).run()) {
            _ui.displayError("Command failed");
          }
        }
      });
    m.add("Redo",
      new UIMenuAction() {
        public void run() {
          if (!Data.newRedoCmd(_inventory).run()) {
            _ui.displayError("Command failed");
          }
        }
      });
    m.add("Print top ten all time rentals in order",
      new UIMenuAction() {
        public void run() {
          Iterator iter = _inventory.iterator(new numRentComp());
          //builder to build the string of top ten
          StringBuilder ansStringBuilder = new StringBuilder();
          int pos = 0;
          // makes sure it prints out top ten ONLY
          while(pos < 10 && iter.hasNext())
          {
            //add it to the string
            ansStringBuilder.append(iter.next().toString() + "\n");
            //increment position
            pos++;
          }
          //print the top ten rentals here
          _ui.displayMessage("Top ten all time rentals in order: \n" + ansStringBuilder.toString());
        }
      });
          
    m.add("Exit",
      new UIMenuAction() {
        public void run() {
          _state = EXIT;
        }
      });
    
    m.add("Initialize with bogus contents",
      new UIMenuAction() {
        public void run() {
          Data.newAddCmd(_inventory, Data.newVideo("a", 2000, "m"), 1).run();
          Data.newAddCmd(_inventory, Data.newVideo("b", 2000, "m"), 2).run();
          Data.newAddCmd(_inventory, Data.newVideo("c", 2000, "m"), 3).run();
          Data.newAddCmd(_inventory, Data.newVideo("d", 2000, "m"), 4).run();
          Data.newAddCmd(_inventory, Data.newVideo("e", 2000, "m"), 5).run();
          Data.newAddCmd(_inventory, Data.newVideo("f", 2000, "m"), 6).run();
          Data.newAddCmd(_inventory, Data.newVideo("g", 2000, "m"), 7).run();
          Data.newAddCmd(_inventory, Data.newVideo("h", 2000, "m"), 8).run();
          Data.newAddCmd(_inventory, Data.newVideo("i", 2000, "m"), 9).run();
          Data.newAddCmd(_inventory, Data.newVideo("j", 2000, "m"), 10).run();
          Data.newAddCmd(_inventory, Data.newVideo("k", 2000, "m"), 11).run();
          Data.newAddCmd(_inventory, Data.newVideo("l", 2000, "m"), 12).run();
          Data.newAddCmd(_inventory, Data.newVideo("m", 2000, "m"), 13).run();
          Data.newAddCmd(_inventory, Data.newVideo("n", 2000, "m"), 14).run();
          Data.newAddCmd(_inventory, Data.newVideo("o", 2000, "m"), 15).run();
          Data.newAddCmd(_inventory, Data.newVideo("p", 2000, "m"), 16).run();
          Data.newAddCmd(_inventory, Data.newVideo("q", 2000, "m"), 17).run();
          Data.newAddCmd(_inventory, Data.newVideo("r", 2000, "m"), 18).run();
          Data.newAddCmd(_inventory, Data.newVideo("s", 2000, "m"), 19).run();
          Data.newAddCmd(_inventory, Data.newVideo("t", 2000, "m"), 20).run();
          Data.newAddCmd(_inventory, Data.newVideo("u", 2000, "m"), 21).run();
          Data.newAddCmd(_inventory, Data.newVideo("v", 2000, "m"), 22).run();
          Data.newAddCmd(_inventory, Data.newVideo("w", 2000, "m"), 23).run();
          Data.newAddCmd(_inventory, Data.newVideo("x", 2000, "m"), 24).run();
          Data.newAddCmd(_inventory, Data.newVideo("y", 2000, "m"), 25).run();
          Data.newAddCmd(_inventory, Data.newVideo("z", 2000, "m"), 26).run();
        }
      });
    
    _menus[stateNum] = m.toUIMenu("Bob's Video");
  }
  private void addEXIT(int stateNum) {
    UIMenuBuilderInterface m = UIMenuBuilderInterface.UIMenuBuilder();
    
    m.add("Default", new UIMenuAction() { public void run() {} });
    m.add("Yes",
      new UIMenuAction() {
        public void run() {
          _state = EXITED;
        }
      });
    m.add("No",
      new UIMenuAction() {
        public void run() {
          _state = START;
        }
      });
    
    _menus[stateNum] = m.toUIMenu("Are you sure you want to exit?");
  }
}
//to make it easier to print out the top
class numRentComp implements java.util.Comparator<Record>{
  public int compare (Record o1, Record o2) {
    Record r1 = (Record)o1;
    Record r2 = (Record)o2;
    return r2.numRentals() - r1.numRentals();
  }
}
