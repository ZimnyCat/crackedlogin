package zimnycat.crackedlogin;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import zimnycat.crackedlogin.utils.FileUtils;

public class CrackedLogin implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger("crackedlogin");

	@Override
	public void onInitialize() {
		FileUtils.init();
		LOGGER.info("CrackedLogin enabled!");
	}
}
