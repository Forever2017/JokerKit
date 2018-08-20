#### JokerKit
常用代码的集合，方便自己开发

#### 添加依赖方式
JitPack仓库地址：https://jitpack.io/

输入库地址：https://github.com/Forever2017/JokerKit 生成依赖地址..

   步骤1.将JitPack存储库添加到构建文件中

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

#### 功能类介绍
	joker.kit.base //基类
		FragmentJoker  Fragment使用..更方便	
		
	joker.kit.permissions //权限相关
	
	joker.kit.file //文件操作
	
	joker.kit.arithmetic //算法相关

	joker.kit.function //系统功能封装
		KeyboardJoker  //软键盘相关操作方法
		
	joker.kit.view //控件，主要是自定义控件和一些控件方法封装 	
		recyclerview.GridItemDecoration //GridLayoutManager（网格布局）设置item的间隔
		recyclerview.ListItemDecoration //List（listView布局）设置item的间隔
		
		button.SwitchButton //类似IOS风格的开关按钮 开源地址：https://github.com/Forever2017/SwitchButton
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	