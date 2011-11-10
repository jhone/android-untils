package com.redsun.platf.service.sys;

import java.util.List;
import java.util.Map;

import com.redsun.platf.entity.sys.SystemLanguage;
import com.redsun.platf.entity.sys.SystemTheme;

/**
 * <p>Title        : com.webapp        </p>
 * <p>Description  :                   </p>
 * <p>Copyright    : Copyright (c) 2011</p>
 * <p>Company      : FreedomSoft       </p>
 * 
 */

/**
 * @author dick pan
 * @version 1.0
 * @since 1.0
 *        <p>
 *        <H3>Change history</H3>
 *        </p>
 *        <p>
 *        2011/1/28 : Created
 *        </p>
 * 
 */
public interface ConfigLoaderService {

	public abstract  ConfigLoaderService getInstance();

	/**
	 * get system themes from configuation.xml
	 * 
	 * @return list object :Theme
	 */

	public abstract List<SystemLanguage> getLanguages();

	/**
	 * get system language from configuation.xml
	 * 
	 * @return map list key:language ,value: description
	 */
	public abstract Map<String, String> getLanguagesMap();

	/**
	 * get system themes from configuation.xml
	 * 
	 * @return list object :Theme
	 */
	public abstract List<SystemTheme> getThemes();

	/**
	 * get system themes from configuation.xml
	 * 
	 * @return map list key:theme ,value: description
	 */
	public abstract Map<String, String> getThemesMap();

}