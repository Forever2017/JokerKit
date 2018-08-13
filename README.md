# JokerKit
常用代码的集合，方便自己开发

＃添加依赖方式
https://jitpack.io/

输入库地址：https://github.com/Forever2017/JokerKit

获取依赖地址
要在您的构建中获得Git项目：

步骤1.将JitPack存储库添加到构建文件中

gradle这个
行家
SBT
leiningen
将其添加到存储库末尾的根build.gradle中：

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
步骤2.添加依赖项

	dependencies {
	        implementation 'com.github.Forever2017:JokerKit:1.0.0'
	}
