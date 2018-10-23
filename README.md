>##  JokerKit
常用代码的集合，方便自己开发

>##  添加依赖方式
JitPack仓库地址：https://jitpack.io/

输入库地址：https://github.com/Forever2017/JokerKit 生成依赖地址..

   步骤1.将JitPack存储库添加到构建文件中
   将其添加到存储库末尾的根build.gradle中：
```groovy
		allprojects {
			repositories {
				...
				maven { url 'https://jitpack.io' }
			}
		}
```
步骤2.添加依赖项
```groovy
	dependencies {
		implementation 'com.github.Forever2017:JokerKit:1.0.0'
	}
```
>## 表格写法
|包名|描述|类名|描述|
|:---|:---:|:---|:---:|
|base|基类| FragmentJoker |Fragment使用..更方便
|function|系统功能封装|KeyboardJoker|软键盘相关操作方法

>##  功能类介绍
* github.document     //git第三方库，使用笔记文档~
	* github_异步_RxJava.txt
	* github_网络请求库_Retrofit2.txt
	* github_黄油刀_butterknife.txt
* github.encapsulation     //git第三方库，封装一些便用的直接使用
	* ormlite	数据库
	* SwitchButton  //类似IOS风格的开关按钮 开源地址：https://github.com/Forever2017/SwitchButton

//###########################

* joker.kit.adapt      //适配
	* InstallAdapt	APK安装适配..
	
* joker.kit.base      //基类
    * FragmentJoker  	//Fragment使用..更方便
	* ActivityJoker  	//Activity使用..更方便
	* ApplicationJoker	//Application使用方便..
	
* joker.kit.customView //自定义控件
	* NormalTitleBar 自定义标题栏
	
* joker.kit.file //文件操作
	* SdcardUtil	SD卡操作..

* joker.kit.function //系统功能封装，例如操作软键盘之类的..
	* KeyboardJoker	软键盘操作

* joker.kit.number //数字相关..算法、随机数啊、时间啊之类的
	* RandomUitl	随机生成各种数字~
	* TimeUitl		时间相关，生成时间格式之类的..
	
* joker.kit.permissions //权限相关
	*	搁浅~

* joker.kit.view //系统控件 功能的扩展封装
	* recyclerview.GridItemDecoration  //（网格布局）设置item的间隔
	* recyclerview.ListItemDecoration   //List（listView布局）设置item的间隔

//###########################	
		
* notes	 //备注手记..
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	