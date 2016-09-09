/*****************************************************************************
 *
 *                      FORNOW PROPRIETARY INFORMATION
 *
 *          The information contained herein is proprietary to ForNow
 *           and shall not be reproduced or disclosed in whole or in part
 *                    or used for any design or manufacture
 *              without direct written authorization from ForNow.
 *
 *            Copyright (c) 2014 by ForNow.  All rights reserved.
 *
 *****************************************************************************/
package com.taoists.common.context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.taoists.common.util.LoggerUtils;

/**
 * Load System property info
 * 
 * @author Jiafa Lv
 * @date Apr 22, 2014 11:09:46 AM
 * @email simon-jiafa@126.com
 * 
 */
public class SystemPropertyInit {
	private static final String CLASS_NAME = SystemPropertyInit.class.getName();
	private static SystemPropertyInit propertyUtils = null;
	private Properties propObject = null;
	private static String propetyFilePath = "config.properties";
	protected long lastModifiedData = -1;

	private SystemPropertyInit() {
		this(propetyFilePath);
	}

	private SystemPropertyInit(final String filePath) {
		propObject = new Properties();
		propertyUtils = this;
		File propertyFile = null;
		propertyFile = new File(SystemPropertyInit.class.getResource("/")
				.getPath() + filePath);
		loadFile(propertyFile);
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
						File propertyFile = new File(SystemPropertyInit.class
								.getResource("/").getPath() + filePath);
						if (propertyUtils.lastModifiedData != propertyFile
								.lastModified()) {
							LoggerUtils.debug(CLASS_NAME, "Property file is changed to reload!");
							propertyUtils.loadFile(propertyFile);
							propertyUtils.lastModifiedData = propertyFile
									.lastModified();
						}
					} catch (Exception e) {

					}
				}
			}
		}).start();
	}

	private static synchronized SystemPropertyInit getInstance(String filePath) {
		if (propertyUtils == null) {
			propertyUtils = new SystemPropertyInit(filePath);
		}
		return propertyUtils;
	}

	public static synchronized SystemPropertyInit getInstance() {
		return getInstance(propetyFilePath);
	}

	private void loadFile(File propertyFile) {
		LoggerUtils.debug(CLASS_NAME, "Load System property file info ...");
		FileInputStream inputStreamLocal = null;
		try {
			inputStreamLocal = new FileInputStream(propertyFile);
			propObject.load(inputStreamLocal);
			lastModifiedData = propertyFile.lastModified();
		} catch (FileNotFoundException e) {
			LoggerUtils.debug(CLASS_NAME, "No Local Properties File Found");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStreamLocal != null) {
					inputStreamLocal.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getProperty(String property) {
		String val = null;
		if (propObject != null) {
			val = propObject.getProperty(property);
		}
		return (val);
	}

	public String getProperty(String property, String defaultValue) {
		String val = null;

		if (propObject != null) {
			val = propObject.getProperty(property, defaultValue);
		} else {
			val = defaultValue;
		}
		return val;
	}

	public Integer getInt(String property) {
		String val = getProperty(property);
		Integer nVal = null;
		try {
			nVal = Integer.parseInt(val);
		} catch (Exception e) {

		}
		return nVal;

	}

	public Integer getIntProperty(String property, Integer defaultValue) {
		Integer val = getInt(property);

		if (val == null) {
			val = defaultValue;
		}

		return (val);
	}

	public Double getDouble(String property) {
		String val = getProperty(property);
		Double nVal = null;
		try {
			nVal = Double.parseDouble(val);
		} catch (Exception e) {

		}
		return nVal;

	}

	public Double getDoubleProperty(String property, Double defaultValue) {
		Double val = getDouble(property);

		if (val == null) {
			val = defaultValue;
		}

		return (val);
	}

	public Long getLong(String property) {
		String val = getProperty(property);
		Long nVal = null;
		try {
			nVal = Long.parseLong(val);
		} catch (Exception e) {

		}
		return nVal;

	}

	public Long getLong(String property, Long defaultValue) {
		Long val = getLong(property);

		if (val == null) {
			val = defaultValue;
		}

		return (val);
	}

	public boolean getBooleanProperty(String property, boolean defaultValue) {
		boolean retval = false;
		String val = getProperty(property);
		if (val == null || val.equals(""))
			retval = defaultValue;
		else if (val.trim().equalsIgnoreCase("true") || val.trim().equals("1"))
			retval = true;

		return (retval);
	}
}
