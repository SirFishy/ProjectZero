package main.java.projectzero.gameinput;

import main.java.projectzero.command.Command;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kristianhfischer on 7/10/15.
 */
public class KeyMapper {

    private Map<Integer, Command> mKeyMap;

    public KeyMapper() {
        mKeyMap = new HashMap<>();
    }

    public void setKeyMapping(Integer keyCode, Command command) {
        mKeyMap.put(keyCode, command);
    }

    public Command getCommand(Integer keyCode) {
        return mKeyMap.get(keyCode);
    }
}
