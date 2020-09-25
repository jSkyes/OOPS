package shop.command;
import java.util.Stack;

final class CommandHistoryObj implements CommandHistory {
  Stack<UndoableCommand> _undoStack = new Stack<UndoableCommand>();
  Stack<UndoableCommand> _redoStack = new Stack<UndoableCommand>();
  RerunnableCommand _undoCmd = new RerunnableCommand () {
      public boolean run () {
        boolean result = !_undoStack.empty();
        //if stack isnt empty run
        if (result) {
          // Undo
          UndoableCommand cmd = _undoStack.pop();
          //push it to redo stack
          _redoStack.push(cmd);
          //undo it now
          cmd.undo();
        }
        return result;
      }
    };
  RerunnableCommand _redoCmd = new RerunnableCommand () {
      public boolean run () {
        boolean result = !_redoStack.empty();
        //if the redo stack is not empty, run
        if (result) {
          // Redo
          //pop it off the stack
          UndoableCommand cmd = _redoStack.pop();
          //pop it onto the undo stack
          _undoStack.push(cmd);
          //redo it
          cmd.redo();
        }
        return result;
      }
    };

  public void add(UndoableCommand cmd) {
    //Add command undoable and clear redoable.
    _undoStack.push(cmd);
    _redoStack.clear();
  }
  
  public RerunnableCommand getUndo() {
    return _undoCmd;
  }
  
  public RerunnableCommand getRedo() {
    return _redoCmd;
  }
  
  // For testing
  UndoableCommand topUndoCommand() {
    if (_undoStack.empty())
      return null;
    else
      return _undoStack.peek();
  }
  // For testing
  UndoableCommand topRedoCommand() {
    if (_redoStack.empty())
      return null;
    else
      return _redoStack.peek();
  }
}
