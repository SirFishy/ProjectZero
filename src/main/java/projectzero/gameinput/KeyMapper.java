package main.java.projectzero.gameinput;

import main.java.projectzero.command.ICommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kristianhfischer on 7/10/15.
 */
public class KeyMapper {

    private Map<Integer, ICommand> mKeyMap;

    public KeyMapper() {
        mKeyMap = new HashMap<>();
    }

    public void setKeyMapping(Integer keyCode, ICommand command) {
        mKeyMap.put(keyCode, command);
    }

    public ICommand getCommand(Integer keyCode) {
        return mKeyMap.get(keyCode);
    }
}
