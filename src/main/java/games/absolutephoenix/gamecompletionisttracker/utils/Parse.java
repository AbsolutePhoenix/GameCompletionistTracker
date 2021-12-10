package games.absolutephoenix.gamecompletionisttracker.utils;

import games.absolutephoenix.gamecompletionisttracker.reference.AppArgs;

import java.util.ArrayList;
import java.util.List;

public class Parse {
    public static void programArguments(String[] args) {
        List<String> arguments = new ArrayList<>();
        for(String argument : args){
            if ((argument.startsWith("-") && argument.length() > 1) && !argument.startsWith("--") ){
                arguments.add(argument.substring(1).toUpperCase());
            }
            else if (argument.startsWith("--") && argument.length() > 2){
                arguments.add(argument.substring(2).toUpperCase());
            }else{
                arguments.set(arguments.size() - 1, arguments.get(arguments.size() - 1) + " " + argument);
            }
        }
        for(String argument : arguments){
            if(argument.contains("DEVELOPMENT"))
                AppArgs.DevelopmentMode = true;
            else
                AppArgs.DevelopmentMode = false;
        }
    }
}
