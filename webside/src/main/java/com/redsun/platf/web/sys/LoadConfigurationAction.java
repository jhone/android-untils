package com.redsun.platf.web.sys;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.redsun.platf.entity.sys.SystemLanguage;
import com.redsun.platf.entity.sys.SystemTheme;
import com.redsun.platf.service.sys.ConfigLoaderService;
/**
 * 
 * @category system configuration value loader
 * @author dick pan 
 * @version 1.0
 * @since   1.0
 * <p><H3>Change history</H3></p>
 * <p>2011/1/28   : Created </p>
 *
 */

@ParentPackage(value = "webmain")
public class LoadConfigurationAction extends ActionSupport implements Preparable {

    private static final long serialVersionUID = 6133938114942344128L;

    @Resource(name = "configLoaderManager")
    ConfigLoaderService configLoader;

    private List<SystemLanguage> languages = new ArrayList<SystemLanguage>();
    private List<SystemTheme> themes = new ArrayList<SystemTheme>();
    
    
    @Actions( { @Action(value = "/list_languages", results = { @Result(name = "success", type = "json", params = {
	    "root", "languages" })
    }) })
    /**
     * 返回系統語言選項列表
     */
    public String getLanguageConfig() {
	return SUCCESS;
    }

    @Actions( { @Action(value = "/list_themes", results = { @Result(name = "success", type = "json", params = {
	    "root", "themes" })

    }) })
     /**
     * 返回系統風格選項列表
     */
    public String getThemeConfig() {

	return SUCCESS;
    }

//    public String getJSON() {
//	return execute();
//    }

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork2.Preparable#prepare()
     */
    @Override
    public void prepare() throws Exception {
	// TODO Auto-generated method stub

	languages = configLoader.getLanguages();

	themes = configLoader.getThemes();
	
//	System.out.println(languages);

    }

    public List<SystemLanguage> getLanguages() {
        return languages;
    }

    public void setLanguages(List<SystemLanguage> languages) {
        this.languages = languages;
    }

    public List<SystemTheme> getThemes() {
        return themes;
    }

    public void setThemes(List<SystemTheme> themes) {
        this.themes = themes;
    }

   
}
