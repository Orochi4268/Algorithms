# 算法（第四版）
## 1 基础
书本自带的版本库在 `std.lib` 下，这是解压的 `stdlib.jar` 源码，你也可以直接将 `lib/stdlib.jar` 加入到 **library** 中去。

............跳过一些基础知识

#### 1.1.9.5 重定向与管道
如果想要运行书中的命令，那么进入 [lib](./lib) 目录中，然后
```
java -Djava.ext.dirs=. RandomSeq 1000 100.0 200.0 > data.txt
```
这样就会在当前**lib**下生成一个包含 100 到 200 之间的随机数文件。

求**平均数**：
```
java -Djava.ext.dirs=. Average < data.txt
--------结果分割线
Average is 150.27822999999998
```

**管道：
```
java -Djava.ext.dirs=. RandomSeq 1000 100.0 200.0 | java -Djava.ext.dirs=. Average
--------结果分割线
Average is 149.16858000000005
```

