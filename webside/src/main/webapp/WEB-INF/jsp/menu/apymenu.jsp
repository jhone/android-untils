<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>


<html>
	<head>
		<sj:head debug="true" compressed="false" jquerytheme="start"
			customBasepath="themes" defaultLoadingText="Please wait ..." />


		<link type="text/css" href="${ctx}/js/apymenu/menu.css"
			rel="stylesheet" />
		<script type="text/javascript" src="${ctx}/js/apymenu/menu.js" >

        </script>

	</head>

	<body>

		<div>
			<img id="myDefaultIndicator" src="images/ajax-loader.gif"
				alt="Loading..." style="display: none" />
		</div>
      
		<br>
		<s:debug />
		message of locale: [${saveMessage}] ,
		<s:property value="getText(\"springapp.heading\")" />
		<br>
		locale: ${pageContext.response.locale}
		<br />

		<%--menu define --%>
		menu
		<div id="menu2">
			<ul class="menu">
				<li>
					<a href="#">Aberdeen</a>
				</li>
				<li>
					<a href="#">Ada</a>
				</li>
				<li>
					<a href="#">Adamsville</a>
				</li>
				<li>
					<a href="#">Addyston</a>
				</li>
				<li>
					<a href="#">Adelphi</a>
				</li>
			</ul>

		</div>


		<br>

		<div id="menu">
			<ul class="menu">
				<li>
					<a href="#" class="parent"><span>Home</span> </a>
					<div>
						<ul>
							<li>
								<a href="#" class="parent"><span>Sub Item 1</span> </a>
								<div>
									<ul>
										<li>
											<a href="#" class="parent"><span>Sub Item 1.1</span> </a>
											<div>
												<ul>
													<li>
														<a href="#"><span>Sub Item 1.1.1</span> </a>
													</li>
													<li>
														<a href="#"><span>Sub Item 1.1.2</span> </a>
													</li>
												</ul>
											</div>
										</li>
										<li>
											<a href="#"><span>Sub Item 1.2</span> </a>
										</li>
										<li>
											<a href="#"><span>Sub Item 1.3</span> </a>
										</li>
										<li>
											<a href="#"><span>Sub Item 1.4</span> </a>
										</li>
										<li>
											<a href="#"><span>Sub Item 1.5</span> </a>
										</li>
										<li>
											<a href="#"><span>Sub Item 1.6</span> </a>
										</li>
										<li>
											<a href="#" class="parent"><span>Sub Item 1.7</span> </a>
											<div>
												<ul>
													<li>
														<a href="#"><span>Sub Item 1.7.1</span> </a>
													</li>
													<li>
														<a href="#"><span>Sub Item 1.7.2</span> </a>
													</li>
												</ul>
											</div>
										</li>
									</ul>
								</div>
							</li>
							<li>
								<a href="#"><span>Sub Item 2</span> </a>
							</li>
							<li>
								<a href="#"><span>Sub Item 3</span> </a>
							</li>
						</ul>
					</div>
				</li>
				<li>
					<a href="#" class="parent"><span>Product Info</span> </a>
					<div>
						<ul>
							<li>
								<a href="#" class="parent"><span>Sub Item 1</span> </a>
								<div>
									<ul>
										<li>
											<a href="#"><span>Sub Item 1.1</span> </a>
										</li>
										<li>
											<a href="#"><span>Sub Item 1.2</span> </a>
										</li>
									</ul>
								</div>
							</li>
							<li>
								<a href="#" class="parent"><span>Sub Item 2</span> </a>
								<div>
									<ul>
										<li>
											<a href="#"><span>Sub Item 2.1</span> </a>
										</li>
										<li>
											<a href="#"><span>Sub Item 2.2</span> </a>
										</li>
									</ul>
								</div>
							</li>
							<li>
								<a href="#"><span>Sub Item 3</span> </a>
							</li>
							<li>
								<a href="#"><span>Sub Item 4</span> </a>
							</li>
							<li>
								<a href="#"><span>Sub Item 5</span> </a>
							</li>
							<li>
								<a href="#"><span>Sub Item 6</span> </a>
							</li>
							<li>
								<a href="#"><span>Sub Item 7</span> </a>
							</li>
						</ul>
					</div>
				</li>
				<li>
					<a href="#"><span>Help</span> </a>
				</li>
				<li class="last">
					<a href="#"><span>Contacts</span> </a>
				</li>
			</ul>
		</div>

		<div id="copyright">
			Copyright &copy; 2011
			<a href="http://apycom.com/">Apycom jQuery Menus</a>
		</div>
	</body>
</html>
