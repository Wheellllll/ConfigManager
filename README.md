## 配置模块
> 这是一个简单的配置管理模块，目前仅支持ini格式，支持动态加载

### 安装
从 https://github.com/Wheellllll/ConfigManager/releases 下载最新的jar包添加到项目依赖里去

### 使用方法
在使用之前，请先使用`setConfigName`方法设置配置文件的路径

配置文件的路径是相对于当前工作目录的路径，如果想获取当前的工作目录，可以使用如下语句输出工作目录：
```java
System.out.println(System.getProperty(“user.dir”));
```


#### 读取配置
使用`getXXX(String key)`或者`getXXX(String key, XXX defaultValue)`(包含默认选项)获取配置属性
`XXX`包括以下几种属性：
- String
- Bool
- Int
- Long
- Float
- Double

`application.conf`

```conf
DEBUG=true
host=localhost
MAX_MESSAGE_PER_SECOND=5
MAX_MESSAGE_PER_SESSION=100
```

代码
```java
Config.setConfigName("./application.conf");                //读取当前目录下的application.conf文件
String host = Config.getConfig().getString("host");        //获取host属性，这里会得到localhost
int port = Config.getConfig().getInt("port", 9001);        //获取port属性，由于没有设置，故这里会使用默认值9001
boolean DEBUG = Config.getConfig().getBool("DEBUG", false) //获取debug属性，由于在配置文件里设置过，故这里没有使用默认值而是得到了true
int MAX_MESSAGE_PER_SECOND = Config.getConfig.getInt("MAX_MESSAGE_PER_SECOND")   //获取到5
int MAX_MESSAGE_PER_SESSION = Config.getConfig.getInt("MAX_MESSAGE_PER_SESSION") //获取到100
```

#### 写入配置
有时候我们可能需要保存配置，本模块库提供了`setProperty`方法来修改配置

代码
```java
Config.setConfigName("./application.conf");              //读取当前目录下的application.conf文件
Config.getConfig().setProperty("host", "9001");          //设置host为9001
int host = Config.getConfig.getInt("host");              //获取到9001
```

#### 动态加载(可选项)
当我们修改配置文件的时候，可以添加一个监听函数来监听配置更改的事件

PS:`getXXX`函数总是保证获取到最新的配置，而这里只是添加一个接口方便一些特殊用途

```java
Config.setConfigListener(new Config.ConfigListener() {
    @Override
    public void OnConfigChanged() {
        //TODO: 可以重启服务器之类的
    }
});
```
