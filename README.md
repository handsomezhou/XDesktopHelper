# XDesktopHelper
A efficient and simple desktop application search tool.Support T9 search and Qwerty search.

Depend
---------------
### AndroidResideMenu
    The idea of ResideMenu is from Dribbble 1 and 2. It has come true and run in iOS devices.
	iOS ResideMenu This project is the RefsideMenu Android version. The visual effect is partly referred to iOS version of ResideMenu.
[https://github.com/SpecialCyCi/AndroidResideMenu](https://github.com/SpecialCyCi/AndroidResideMenu)

### android-segmented-control
	ios7 UISegmentedControl for android
[https://github.com/hoang8f/android-segmented-control](https://github.com/hoang8f/android-segmented-control)

### gson
	A Java serialization/deserialization library to convert Java Objects into JSON and back
[https://github.com/google/gson](https://github.com/google/gson)

### pinyinsearch 
    The library of PinyinSearch,a Java Library Which provide data analysis methods,  
	data matching method and so on for T9 pinyin search and Qwerty pinyin search.
[https://github.com/handsomezhou/PinyinSearchLibrary](https://github.com/handsomezhou/PinyinSearchLibrary)

### SwitchButton
	This project provides you a convient way to customise a SwitchButton widget in Android. With just resources changed and attrs set, 
	you can get a lifelike SwitchButton in Android 5.0, iOS 7/8, MIUI, or Flyme and so on.
[https://github.com/kyleduo/SwitchButton](https://github.com/kyleduo/SwitchButton)

### rxjava
	RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.
[https://github.com/ReactiveX/RxJava](https://github.com/ReactiveX/RxJava)

### UserGuideView
	UserGuideView – User guide view.
[https://github.com/yilylong/UserGuideView](https://github.com/yilylong/UserGuideView)

### 百度云(自然语言处理)SDK集成
百度云 aip-java-sdk-xxx download:[http://ai.baidu.com/sdk#nlp](http://ai.baidu.com/sdk#nlp) [http://ai.baidu.com/tech/nlp/lexical](http://ai.baidu.com/tech/nlp/lexical)

1.copy library
	(1)aip-java-sdk-4.1.1.jar
	(2)json-20160810.jar
	(3)log4j-1.2.17.jar

2.init app info(com.handsomezhou.xdesktophelper.baidu.aip.constant.BaiduConstant)

(1)APP_ID
(2)API_KEY
(3)SECRET_KEY

### 科大讯飞(在线命令词识别)SDK集成
xfyun sdk download:[http://www.xfyun.cn/sdk/dispatcher](http://www.xfyun.cn/sdk/dispatcher)

1.copy assets

2.copy library

	(1)Msc.jar;
	(2)Sunflower.jar;
	(3)arm*/libmsc.so

3.add permission(AndroidManifest.xmlRemove non-essential permissions)

4.use xfyun code

(1)SpeechDemo\src\com\iflytek\voicedemo\

5.use IFLYTEK_APPKEY:

(1)mobile statistical analysis(in AndroidManifest.xml)

(2)app_id(strings.xml)

### 小米统计SDK集成
MiStats sdk download:[https://dev.mi.com/console/appservice/](https://dev.mi.com/console/appservice/)

### Renderings
<img src="https://github.com/handsomezhou/XDesktopHelper/blob/master/external_res/image/XDesktopHelper.gif"/>


### Apk download
[XDesktopHelper](https://github.com/handsomezhou/XDesktopHelper/blob/master/external_res/bin/XDesktopHelper.apk?raw=true)(latest version)

License 
---------------
	Copyright 2015 handsomezhou

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

		http://www.apache.org/licenses/LICENSE-2.0
		
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
