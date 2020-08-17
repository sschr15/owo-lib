package sschr15.owolib.api;

import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Tools for getting and adding randomized owos
 * @author sschr15
 */
public class OwOTools {
    private static final Random random = new Random();
    private static List<String> owoList;
    private static List<String> intermediate = new ArrayList<>();

    private static final String[] defaults = {
            "owo", "ovo", "ówó", "óvó", "öwö", "övö", "òwò", "òvò",
            "OwO", "OvO", "ÓwÓ", "ÓvÓ", "ÖwÖ", "ÖvÖ", "ÒwÒ", "ÒvÒ",
            "uwu", "uvu", "úwú", "úvú", "üwü", "üvü", "ùwù", "ùvù",
            "UwU", "UvU", "ÚwÚ", "ÚvÚ", "ÜwÜ", "ÜvÜ", "ÙwÙ", "ÙvÙ",
            "ÙwÚ", "ÚwÙ", "ÙvÚ", "ÚvÙ", "úwù", "ùwú", "úvù", "ùvú",
            "ÓwÒ", "ÒwÓ", "ówò", "òwó", "ÓvÒ", "ÒvÓ", "óvò", "òvó",
            "nwn", "^w^", ">w<", "Owo", "owO", ";w;", "0w0", "QwQ",
            "TwT", "-w-", "$w$", "@w@", "*w*", ":w:", "°w°", "ºwº",
            "`w´", "´w`", "~w~", "umu", "nmn", "own", "nwo", "ñwñ",
            "NwN", "PwP", "own", "nwo", "ÖwÖ", "ÔwÔ", ".w.", "+w+",
            ")w(", "]w[", "}w{", "_w_", "/w/", "\\w\\", "|w|", "#w#",
            "'w'", "\"w\"", "öwö", "ôwô", "=w=", "!w!", "<>w<>"
    };

    private static void genOwOs() {
        owoList = new ArrayList<>();

        try {
            File owoCfg = new File(FabricLoader.getInstance().getConfigDir().toFile(), "owo.txt");

            if (owoCfg.createNewFile()) {
                FileWriter writer = new FileWriter(owoCfg);
                writer.write(String.join("\n",
                        "# This file contains all custom owos that you want!",
                        "# Lines that start with a # will not be added to the list.",
                        "",
                        "# Example:",
                        "# owo (will be shown as \"owo what's this\")",
                        "# cheese (will be shown as \"cheese what's this\")",
                        "") + String.join("\n", defaults));
                writer.close();
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(owoCfg))) {
                reader.lines().forEach(s -> {
                    if (!s.isEmpty() && !s.startsWith("#")) owoList.add(s + " what's this");
                });
            }
        } catch (IOException ignored) {}
    }

    /**
     * Get a random owo from the list
     * @return one owo (plus "what's this"), picked at random, with a placeholder if none was found
     */
    public static String getOwO() {
        if (owoList == null) genOwOs();
        else if (!intermediate.isEmpty()) {
            owoList.addAll(intermediate);
            intermediate = new ArrayList<>();
        }
        return owoList.isEmpty() ? "owo what's this" : owoList.get(random.nextInt(owoList.size()));
    }

    /**
     * Add more owos to the list
     * @param owos one or more owos
     */
    public static void addOwO(String... owos) {
        // Using an intermediate list to prevent NPEs from happening before the array was initialized
        for (String owo : owos) {
			intermediate.add(owo + " what's this");
		}
    }

    /**
     * Get many owos
     * @param count the number of owos you want
     * @return a string array containing owos
     */
    public static String[] getOwOs(int count) {
        if (owoList == null) genOwOs();
        else if (!intermediate.isEmpty()) {
            owoList.addAll(intermediate);
            intermediate = new ArrayList<>();
        }
        String[] owos = new String[count];
        for (int i = 0; i < count; i++) {
            owos[i] = getOwO();
        }
        return owos;
    }

    /**
     * Get the owo list at the time of the call
     * @return an array of the list's contents
     */
    public static String[] getAllOwOs() {
        if (owoList == null) genOwOs();
        else if (!intermediate.isEmpty()) {
            owoList.addAll(intermediate);
            intermediate = new ArrayList<>();
        }
        return owoList.toArray(new String[0]);
    }
}
