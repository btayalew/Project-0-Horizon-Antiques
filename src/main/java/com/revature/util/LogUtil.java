package com.revature.util;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class LogUtil {
//	private static final Logger logger = LogManager.getLogger(LogUtil.class);
	private static Logger log = LogManager.getRootLogger();
	public static void descriptiveError(String description){
        log.error(description);
    }
	
}
