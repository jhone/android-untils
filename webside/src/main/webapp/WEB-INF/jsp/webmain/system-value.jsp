<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

	<html>
	  <head>
	    <sj:head jqueryui="true" locale="cn_ZH" />
	   <script type="text/javascript"> 
	   
	    $.subscribe('rowselect', function(event,data) {
      	var selected = $("#mygrid").jqGrid('getGridParam','selarrrow');

	    	$("#gridinfo").html('<p>Edit Mode for Row : '
             +event.originalEvent.id+'<br>'+selected+'</p>');
      
         
    //  	alert('Selected Rows : '+s); 
    	
  	    });
	/** 
    $.subscribe('rowadd', function(event,data) {
        $("#mygrid").jqGrid('editGridRow',"new",{height:280,reloadAfterSubmit:false}); 
  	});
   
    $.subscribe('searchgrid', function(event,data) {
        $("#mygrid").jqGrid('searchGrid', {sopt:['cn','bw','eq','ne','lt','gt','ew']} );
  	});
    
    $.subscribe('showcolumns', function(event,data) {
        $("#mygrid").jqGrid('setColumns',{});
  	});
	   **/
	   
	    </script>
	    
	  </head>
	  <body>
	   <br>
	      grid example:
  	      
	<!--	entry of grid      -->  	      
	     <s:url id="remoteurl" action="system-value-json" namespace="webmain" /> 
	     <s:url id="editurl" action="system-value-entry" namespace="webmain"/>
	 <sjg:grid
		editurl="%{editurl}"
	    editinline="false"
		gridModel="gridModel.result" 
		caption="jquery-grid 系統" 
		id="mygrid" 
		href="%{remoteurl}"
		pager="true" 
    	navigator="true"
    	navigatorSearchOptions="{sopt:['eq','le','lt','gt','cn']}"
    	
    	navigatorAddOptions="{height:280,reloadAfterSubmit:true}"
    	navigatorEditOptions="{height:280,reloadAfterSubmit:false}"
    	navigatorEdit="true"
    	navigatorView="false"
    	navigatorDelete="true"
    	navigatorDeleteOptions="{height:280,reloadAfterSubmit:true}"
    	rowList="10,15,20"
    	rowNum="15" 
    	onSelectRowTopics="rowselect"
    	
    	multiselect="true"
    	viewrecords="true"
    	autowidth="false"
    	dataType="json"
    	
	  >
	  
	  	<sjg:gridColumn name="id" index="id" title="ID" width="30" hidden="true" editable="true"  dataType="integer" formatter="integer" sortable="false" search="true" searchoptions="{sopt:['eq','le','lt','gt','like']}" />
    	<sjg:gridColumn name="sysNo" index="sysNo" title="sysNo" editable="true" edittype="select" editoptions="{value:'CP:庫存;ZC:資產'}" sortable="false" search="false"/>
    	<sjg:gridColumn name="sysKey" index="sysKey" title="sys Key " editable="true" sortable="true" />
    	<sjg:gridColumn name="sysValue" index="sysValue" title="sys Value" editable="true" sortable="true" />
    	<sjg:gridColumn name="description" index="description" title="description s" editable="true" sortable="false" />
<%-- 	<sjg:gridColumn name="id"  title="line" />--%>
	 
	 
	 </sjg:grid>
	   
	<br>
<%--	<sj:submit id="grid_edit_addbutton" value="Add Row" onClickTopics="rowadd" button="true"/>--%>
<%--    <sj:submit id="grid_edit_searchbutton" value="Search" onClickTopics="searchgrid" button="true"/>--%>
<%--    <sj:submit id="grid_edit_colsbutton" value="Show/Hide Columns" onClickTopics="showcolumns" button="true"/>--%>
<%--	<br/>--%>
	<br/>
    <div id="gridinfo" class="ui-widget-content ui-corner-all"><p>Edit Mode for Row :</p></div>
	  <br> 
	  
	  </body>
	</html>
