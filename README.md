# gitHubLogin
-bin
	編譯過的程式
-lib
	所有會用到的libraries
-resources
	-buildNum: 每次測試會自動generate一組編號存放在這裡，用於區分screenshot的folder
	-element: 存放所有elements的xpath
	-environment: 測試環境的設定檔
	-testCase: 存放所有test cases
	-userInfo: 存放跟user相關的設定
-screenshot
	-p: 存放pass的screenshot，檔名為test case summary
	-f: 存放fail的screenshot，檔名為test case summary
-src
	-login
		* LoginByCorrectID: 測試腳本的主程式
		* creatBuild: 測試之前會generate一組編號，用於區分screenshot的folder
	-pom
		* core: webdriver的設定
	-tool
		* 改寫過或多次重複使用的function
