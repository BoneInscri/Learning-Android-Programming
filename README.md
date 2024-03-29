# Learning-Android-Programming

这是一个记录学习安卓开发的仓库。

将大二写的课程作业重新整理了出来，打赢复活赛了。

### 环境配置

Ubuntu 22.04 + 最新版 Android Studio（2024.3.29）

**（1）安装 JDK**

```
sudo apt install openjdk-11-jdk
```

```
$ java -version
openjdk 11.0.22 2024-01-16
OpenJDK Runtime Environment (build 11.0.22+7-post-Ubuntu-0ubuntu222.04.1)
OpenJDK 64-Bit Server VM (build 11.0.22+7-post-Ubuntu-0ubuntu222.04.1, mixed mode, sharing)
```

**（2）安装 Android SDK**

还是用Android Studio 进行开发把

```
https://developer.android.com/studio#downloads
```

下载压缩包：

```
android-studio-2023.2.1.24-linux.tar.gz
```

解压之后得到：

<img src="README.assets/image-20240329170941677.png" alt="image-20240329170941677" style="zoom: 80%;" />



```
cd bin
sh ./studio.sh
```

所有设置存储在这个目录下

```
~/.config/Google/AndroidStudio4.1
```

数据缓存在：

```
~/.local/share/Google/AndroidStudio4.1
```

![image-20240329171157641](README.assets/image-20240329171157641.png)

![image-20240329171211476](README.assets/image-20240329171211476.png)

<img src="README.assets/image-20240329171222239.png" alt="image-20240329171222239" style="zoom: 67%;" />

<img src="README.assets/image-20240329171315149.png" alt="image-20240329171315149" style="zoom:67%;" />

<img src="README.assets/image-20240329171336140.png" alt="image-20240329171336140" style="zoom:67%;" />

起始界面：

<img src="README.assets/image-20240329171604011.png" alt="image-20240329171604011" style="zoom: 67%;" />



SDK默认安装位置：

```
~/Android/Sdk
```



**（3）添加快捷方式**

假设android-studio的路径在下面这个地方：

```
~/Documents/android-studio
```

```
sudo ln -s ~/Documents/android-studio/bin/studio.sh /usr/bin/android-studio
```

可以在终端中输入 `android-studio` 即可启动Android-Studio

```
sudo vim /usr/share/applications/android-studio.desktop
```

```
[Desktop Entry]
Type=Application
Name=Android Studio
Comment=Android Studio Integrated Development Environment
Icon=~/Documents/android-studio/bin/studio.png
Exec=~/Documents/android-studio/bin/studio.sh
Terminal=false
```



**（4）SDK Manager**

![image-20240329172925488](README.assets/image-20240329172925488.png)





**（5）Android Studio的一些设置**

字体、主题





**（6）手动安装 gradle**

```
Could not install Gradle distribution from 'https://services.gradle.org/distributions/gradle-7.0.2-bin.zip'.
Reason: java.net.SocketTimeoutException: Read timed out
```

File——>Settings——>Gradle——> Build,Execution,Deployment —>Build Tools——>Gradle 

<img src="README.assets/image-20240329175538316.png" alt="image-20240329175538316" style="zoom: 50%;" />



将 下载得到的gradle安装包放入 `.gradle/wrapper/dists`

不需要解压！

每个项目的gradle配置信息在这里：

```
./gradle/wrapper/gradle-wrapper.properties
```

一个可能的内容如下：

```
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-7.2-bin.zip
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
```



最好的办法就是设置代理服务器。

File->Settings->Apperance & Behavior->System Settings->**HTTP Proxy**

![image-20240329182834399](README.assets/image-20240329182834399.png)

![image-20240329182926090](README.assets/image-20240329182926090.png)

![image-20240329182935637](README.assets/image-20240329182935637.png)

至于梯子用什么，推荐PigCha Proxy。

**设置后，能显著提神速度。**



![image-20240329183924971](README.assets/image-20240329183924971.png)





### 代码解释

**Calculator**

一个计算器

<img src="README.assets/image-20240329183841600.png" alt="image-20240329183841600" style="zoom:67%;" />



**Counter**

一个计时器

<img src="README.assets/image-20240329184759786.png" alt="image-20240329184759786" style="zoom: 67%;" />

**Login**

简易的登陆界面

<img src="README.assets/image-20240329195207573.png" alt="image-20240329195207573" style="zoom: 67%;" />



**Music_player**

一个音乐播放器

<img src="README.assets/image-20240329195617687.png" alt="image-20240329195617687" style="zoom:67%;" />



