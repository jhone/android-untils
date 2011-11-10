<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<html>
	<head>
		<sj:head locale='en' jqueryui="true" jquerytheme="start"/>
		
	</head>
	<body>
	
	<s:debug/>
		<br>
		according
		<div id="col3_content">
			<h2>
				Accordion

			</h2>
			<p class="text">
				A simple Accordion.
			</p>
			<s:url id="urlajax1" action="success_message" />
			<sj:accordion id="accordion">
				<sj:accordionItem title="Mauris mauris ante">
					<sj:div id="divInAccrodionItem" href="%{urlajax1}" />
					bbb
					<div>
					   <a href="http://www.163.com">go to 163</a>
					   
					</div>
				</sj:accordionItem>

				<sj:accordionItem title="Sed non urna">
    		Sed non urna. Donec et ante. Phasellus eu ligula. Vestibulum sit amet purus. Vivamus hendrerit, dolor at aliquet laoreet, mauris turpis porttitor velit, faucibus interdum tellus libero ac justo. Vivamus non quam. In suscipit faucibus urna.
    	</sj:accordionItem>
				<sj:accordionItem title="Nam enim risus">
    		Nam enim risus, molestie et, porta ac, aliquam ac, risus. Quisque lobortis. Phasellus pellentesque purus in massa. Aenean in pede. Phasellus ac libero ac tellus pellentesque semper. Sed ac felis. Sed commodo, magna quis lacinia ornare, quam ante aliquam nisi, eu iaculis leo purus venenatis dui.
    	       </sj:accordionItem>

			</sj:accordion>

			===== list from action
			<sj:accordion id="accordionmouseover" list="accordion" active="false"
				openOnMouseover="true" collapsible="true">
				<sj:accordionItem title="Cras dictum">
    		Cras dictum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean lacinia mauris vel est. Suspendisse eu nisl. Nullam ut libero. Integer dignissim consequat lectus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.
    		
    		
				</sj:accordionItem>
			</sj:accordion>
			bb

		</div>
		
<s:url id="remoteurl" action="success_message"/>
				<sj:textarea 
					href="%{remoteurl}" 
					id="echo" 
					name="echo" 
					rows="10" 
					cols="80" 
					effect="highlight" 
					effectDuration="1500" 
					loadingText="Loading content of textarea ..."
				/>
				

		<br>


	</body>
</html>
