package sschr15.owolib;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import sschr15.owolib.api.OwOTools;

public class OwOLib implements ModInitializer {

    @Override
    public void onInitialize() {
        LogManager.getLogger("owo").info(OwOTools.getOwO());
    }
}
