
1.webservice.war 复制到 MAXIMO.ear
2.META-INF/application.xml 添加 
	
	<module id="WebModule_1077124923845">
		<web>
			<web-uri>webservice.war</web-uri>
			<context-root>/mxservice</context-root>
		</web>
	</module>

3. businessobjects.jar/META-INF/MANIFEST.MF 添加
 lib/kotlin-runtime.jar
 
 还有2个websphere缓存目录需要修改
 D:\IBM\WebSphere\AppServer\profiles\ctgAppSrv01\config\cells\ctgCell01\applications\MAXIMO.ear\deployments\MAXIMO
 D:\IBM\WebSphere\AppServer\profiles\ctgDmgr01\config\cells\ctgCell01\applications\MAXIMO.ear\deployments\MAXIMO
 以下简称 maximoconfig 目录
 例如 
 D:\IBM\WebSphere\AppServer\profiles\ctgAppSrv01\config\cells\ctgCell01\applications\MAXIMO.ear\deployments\MAXIMO\deployment.xml 
 将称之为
 maximoconfig\deployment.xml 
 
4.maximoconfig\deployment.xml 添加
	其中的target需要与其它的modules中的相同
	xmi:id 不可与其它的重复

    <modules xmi:type="appdeployment:WebModuleDeployment" xmi:id="WebModuleDeployment_1465720423654" deploymentId="1" startingWeight="5000" uri="webservice.war" containsEJBContent="0">
      <targetMappings xmi:id="DeploymentTargetMapping_1465720423662" target="ServerTarget_1481192059734"/>
      <targetMappings xmi:id="DeploymentTargetMapping_1465720423663" target="ServerTarget_1481192059735"/>
    </modules>
5. webservice.war.config.7z 中的 webservice.war 复制到 maximoconfig\MAXIMO

6. 将MAXIMO.ear/META-INF/application.xml 复制到 maximoconfig/META-INF/application.xml
 