-- MySQL dump 10.13  Distrib 5.7.27, for linux-glibc2.12 (x86_64)
--
-- Host: localhost    Database: judge
-- ------------------------------------------------------
-- Server version	5.7.27-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED='b43d09c3-69c3-11ea-9128-00163e0a4512:1-710';

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quiz_id` int(11) NOT NULL,
  `submit_time` bigint(20) NOT NULL,
  `score` int(11) DEFAULT NULL,
  `singlechoice_count` int(11) DEFAULT NULL,
  `trueorfalse_count` int(11) DEFAULT NULL,
  `fillintheblank_count` int(11) DEFAULT NULL,
  `readprogram_count` int(11) DEFAULT NULL,
  `programfillintheblank_count` int(11) DEFAULT NULL,
  `programing_count` int(11) DEFAULT NULL,
  `student_name` varchar(100) NOT NULL,
  `correctmistake_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (5,20,1589104862006,57,0,6,8,20,10,15,'夏崇康',0),(6,21,1589105692996,35,2,6,0,0,0,15,'高鹏\r',10),(7,21,1589108087404,31,0,5,0,0,0,15,'夏崇康',10),(8,23,1590499105468,35,4,6,0,0,0,15,'夏崇康',10),(9,23,1590499283699,33,0,8,0,0,0,15,'高鹏\r',10),(10,24,1590510452780,10,0,10,0,0,0,0,'夏崇康',0),(11,24,1590510458006,10,0,10,0,0,0,0,'夏崇康',0),(12,24,1590510463164,10,0,10,0,0,0,0,'夏崇康',0),(13,24,1590510467887,10,0,10,0,0,0,0,'夏崇康',0);
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `type` varchar(100) CHARACTER SET latin1 NOT NULL,
  `answer` text,
  `input` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `output` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `choice_a` varchar(100) DEFAULT NULL,
  `choice_b` varchar(100) DEFAULT NULL,
  `choice_c` varchar(100) DEFAULT NULL,
  `choice_d` varchar(100) DEFAULT NULL,
  `question_img` varchar(255) DEFAULT NULL,
  `input2` varchar(100) DEFAULT NULL,
  `input3` varchar(100) DEFAULT NULL,
  `output2` varchar(100) DEFAULT NULL,
  `output3` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (6,'@RequestMapping(\"/questionManageTrueOrFalse\")<br /> private String questionManageTrueOrFalse(Model model){ <br />List allTrueOrFalse = questionRepository.findAllTrueOrFalse(); <br />_.addAttribute(\"allTrueOrFalse\",allTrueOrFalse); <br />return \"admin/questionManageTrueOrFalse\";<br /> }','Program Fill In The Blank','model',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,'import java.io.* ; <br />public class abc{ <br />public static void main(String[] args){ <br /> int i, s = 0; <br /> int a[] = {20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120};<br /> for(i = 0; i < a.length; i++) <br /> if(i % 3 == 0) <br /> s += a[i]; <br /> System.out.println(\"s=\" + s); <br /> } } <br />请写出该程序的输出结果:','Read Program','s=260',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(20,'Js中“未定义”的关键词','Fill In The Blank','undefined',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(21,'hibernate配置文件设置方言的关键字','Fill In The Blank','dialect',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(22,'hibernate中通过主键获取一个JavaBean对象的方法是','Fill In The Blank','get()',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(23,'spring中包括AOP事务管理、IOC容器还包含___组件','Fill In The Blank','spring-orm(等)',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(24,'int i=10 ; i++==10 ? ++i : --i 最后i=','Read Program','12',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(27,'程序功能：输入一个整数后，输出该数的位数(例如：输入3214则输出4，输入-23156则输出5)<br />#include <stdio.h><br />void main() {<br />    int n, k = 0;<br />    scanf(\"%d\", &n);<br />    while(n != 0)<br />    { k++; __; }<br />    print(\"%d\\n\", k);<br />}','Program Fill In The Blank','n=n/10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(29,'以下程序要求从键盘输入一个整数，判别该整数为几位数，并输出结果，请将下面的程序填写完整。<br />public class Blank1 {<br />    public static void main(String[] args) throws IOException {<br />        Scanner sc=new Scanner( __ );<br />        int count=0,t;<br />        int x=sc.nextInt();<br />        sc.close();<br />        t=x;<br />        while(t!=0){<br />            count++;<br />            t=t/10 ;<br />        }<br />        System.out.println(x+\"是\"+count+\"位数。\");<br />    }<br />}','Program Fill In The Blank','System.in',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(30,'给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。\n<br />\n说明：你不能倾斜容器，且 n 的值至少为 2。','Programing',NULL,'[1,8,6,2,5,4,8,3,7]','49',NULL,NULL,NULL,NULL,'https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/07/25/question_11.jpg','[1,2,4,3]','[1,2]','4','1'),(34,'下述程序的输出结果是_____。<br />main ( ) {<br />    int x=-1,y=4,k;<br />    k=x++<=0 && !(y--<=0);<br />    printf(“%d,%d,%d”,k,x,y);<br />}','Read Program','1,0,3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL),(36,'假设你正在爬楼梯。需要 n 阶你才能到达楼顶。<br /><br />每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？<br /><br />注意：给定 n 是一个正整数','Programing',NULL,'[2]','2',NULL,NULL,NULL,NULL,'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586951032813&di=3defe6f815d00ff19b83610cb35a1d3f&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20190201%2F5867c19882aa4299851d87eb4d7c401a.jpeg','[3]','[4]','3','5'),(37,'给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。<br /><br />如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。<br /><br />注意：你不能在买入股票前卖出股票。Excalibur!<br /><br />来源：力扣（LeetCode）<br />链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock<br />著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。','Correct Mistake','class Solution {<br />public int maxProfit(int[] prices) {<br />if (prices.length == 0){<br />return 0;<br />}<br />int lowPoint = prices[0];<br />int result = 0;<br />for (int i = 1; i < prices.length; i++){<br />if (prices[i] < lowPoint){<br />lowPoint = prices[i];<br />continue;<br />}else {<br />if (prices[i] - lowPoint > result){<br />result = prices[i] - lowPoint;<br />}<br />}<br />}<br />return result;<br />}<br />}','[7,1,5,3,6,4]','5',NULL,NULL,NULL,NULL,NULL,'[7,6,4,3,1]','[0]','0','0'),(38,'int i = 0;<br />i = i++;<br />System.out.println(\"i的值是 \"+i);','Fill In The Blank','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(42,'在 Java 中,负责对字节代码解释执行的是()','Singel Choice','B',NULL,NULL,'应用服务器','虚拟机','垃圾回收器','编译器',NULL,NULL,NULL,NULL,NULL),(43,' 一个栈的输入序列为 1 2 3 4 5, 则下列序列中不可能是栈输出的序列的是()','Singel Choice','A',NULL,NULL,'5 4 1 3 2','2 3 4 1 5','1 5 4 3 2','2 3 1 4 5',NULL,NULL,NULL,NULL,NULL),(44,'下列那一个选项按照顺序包括了 OSI 模型的 7 个层次( )','Singel Choice','C',NULL,NULL,'物理层 数据链路层 传输层 网络层 会话层 表示层 应用层','物理层 数据链路层 会话层 网络层 传输层 表示层 应用层','物理层 数据链路层 网络层 传输层 会话层 表示层 应用层','网络层 传输层 物理层 数据链路层 会话层 表示层 应用层',NULL,NULL,NULL,NULL,NULL),(45,'当客户度关闭一个从连接池中获取的连接, 会发生下面哪一种情况?()','Singel Choice','A',NULL,NULL,' 连接不会关闭, 只是简单地归还给连接池','连接被关闭 , 但又被重新打开并归还给连接池','连接永久性关闭','',NULL,NULL,NULL,NULL,NULL),(46,' 以下哪些不是 javaScript 的全局函数( )','Singel Choice','C',NULL,NULL,'eval','escape','setTimeout',' parseFloat',NULL,NULL,NULL,NULL,NULL),(47,' 你使用 mkdir 命令创建一个临时的文件夹/tmp/aaa, 并将一些文件复制其中，使用完<br />后要删除/mnt/tmp 文件夹及其中的所有文件, 应该使用命令（）','Singel Choice','B',NULL,NULL,' rm /tmp/aaa','rm –r /tmp/aaa',' rmdir –r /tmp/aaa','rmdir /tmp/aaa',NULL,NULL,NULL,NULL,NULL),(48,'在 UML 提供的图中, ( ) 用于按数据顺序描述对象间的交互','Singel Choice','C',NULL,NULL,'协作图','网络图','序列图','状态图',NULL,NULL,NULL,NULL,NULL),(49,'下面有关系统并发访问数估算数据哪个最有效: （）','Singel Choice','B',NULL,NULL,'高峰时段日处理业务量 100000','高峰时段平均每秒请求数 80','同时在线用户 100','平均每秒用户请求 50',NULL,NULL,NULL,NULL,NULL),(50,'不同级别的用户对同一对象拥有不同的访问权利或某个客户端不能直接操作到某个<br />对象,但有必须和那个对象有所互动, 这种情况最好使用什么设计模式.()','Singel Choice','D',NULL,NULL,'Bridge 模式','Fa?ade 模式','Adapter 模式',' Proxy 模式',NULL,NULL,NULL,NULL,NULL),(51,'在Java的方法中定义一个常量要用const关键字。','True Or False','F',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(52,'抽象方法必须在抽象类中，所以抽象类中的方法都必须是抽象方法。','True Or False','F',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(53,'用final修饰的变量叫常量。','True Or False','T',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(54,'覆盖的同名方法中，子类方法不能比父类方法的访问权限低。','True Or False','T',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(55,'抽象类中能创建对象。','True Or False','F',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(56,'一个Java源程序是由若干个类组成。如果源文件中有多个类时，则只能有一个类是____类，并且这个类必须与源文件名同名。','Fill In The Blank','public',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(57,'char c =\'a\';System.out.println(c+1);运行结果为：___','Fill In The Blank','98',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(58,'异常处理中finally块可以确保无论是否发生异常，该块中的代码总能被执行。finally块不执行的唯一情况是在异常处理代码中执行_____;语句退出java虚拟机。','Fill In The Blank','System.exit(0)',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(59,'使用Math.random();返回带正号的double值，该值大于等于0.0且小于1.0。使用该函数生成[30，60]之间的随机整数的语句是：_____','Fill In The Blank','(int)(Math.random()*31)+30',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60,'下面程序的运行结果是? 为什么？<br />String str1 = “hello”;<br />String str2 = “he”+new String(“llo”);<br />String str3 = “he”+”llo”;<br />System.err.println(str1== str2);<br />System.err.println(str1 == str3);','Read Program','false true',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(61,'当 n = 5 时，下列函数的返回值是：<br />int foo(int n)<br />{<br /> if(n<2)return n; <br /> return foo(n-1)+foo(n-2); <br />}','Read Program','5',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(62,'下面代码的运行结果是（）<br /><br />public class Test{<br />public static void main (String[] args){<br />List<String> a = null;<br />test(a);<br />System.out.println(a.size());<br />}<br />public static void test(List<String> a){<br />a=new arrayList<String>();<br />a.add(“abc”);<br />} }','Read Program','Java.lang.NullPointerException',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(63,'执行以下程序后输出的结果是（）<br />public class Test {<br />public static void main(String[] args) {<br />StringBuffer a = new StringBuffer(\"A\");<br />StringBuffer b = new StringBuffer(\"B\");<br />operator(a,b);<br />System.out.println(a+\",\"+b);<br />}<br />public static void operator(StringBuffer x,StringBuffer y){<br />x.append(y);<br />y=x;<br />} }','Read Program','AB,B',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(64,'下列代码输出的结果是（）<br />int x= 0;<br />int y=10;<br />do{<br />y--;<br />++x;<br />}while(x<6);<br />System.out.println();<br />}','Read Program',' 6,5',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(65,'下列程序段输出的结果是（）<br />Void complicatedexpression_f(){<br />int x=20,y=30;<br />boolean j;<br />j=x>50&&y>60|| x>50&& y<-60 || x<-50&&y>60 || x<-50&& y<-60;<br />System.out.println(j);<br />}','Read Program','false',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(66,'下列代码执行结果是（）<br /><br />public static void main (String args[]){<br />Thread t = new Thread(){<br />public void run(){<br />pong();<br />}<br />};<br />t.run();<br />System.out.print(\"ping\");<br />}<br />static void pong(){<br />System.out.print(\"pong\");<br />}','Read Program',' pongping',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(67,'根据以下代码回答问题：输出结果是print out “Finally”<br />public class Foo{<br />public static void main(String args[]){<br />try{___;}<br />____{System.out.println(“Finally”);}<br />} }','Program Fill In The Blank','return   finally',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(68,'下列 JSP 代码，以下（）可放置在//1 处，不会发生编译错误。<br /><html><br /><body><br /><%<br />for(int i = 0; i < 10; i++) {<br />//1<br />}<br />%><br /></body><br /></html>','Program Fill In The Blank','%><%= i %><%',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(69,'考虑下面 JSP 文件代码片断：test2.jsp里填什么<br /><br /><HTML><br /><BODY><br /><jsp:include page=”test2.jsp”><br /><jsp:param name=”username” value=”zhangsan”/><br /></jsp:include><br /></BODY><br /></HTML>','Program Fill In The Blank','<%=request.getParameter(“username”)%>',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70,'Java的源代码中定义几个类，编译结果就生成几个以“.class”后缀的字节码','True Or False','T',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(71,'接口中可以包含非静态成员。','True Or False','F',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(72,'抽象类中不能创建对象。','True Or False','T',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(73,'final 方法不能被覆盖','True Or False','T',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(74,'成员变量的值会因为对象的不同而不同。','True Or False','T',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(75,'final 类可以有子类。','True Or False','F',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(76,'Java语言中，类的成员变量在整个类内都有效，其有效性与它在类体中的位置无关，方法中的局部变量的有效性与它在方法体中的位置无关。','True Or False','F',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(77,'Java 语言规定，任何一个子类的构造方法都必须调用其父类的构造方法 包括隐式调用），并且调用父类构造方法的语句必须是子类构造方法的第一条语句。','True Or False','T',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(78,'abstract方法只能在abstract类或接口。','True Or False','T',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(79,'在编译Java源程序时，计算机根本不会去识别各个变量名的具体含义，因此命名规范对编写 Java程序而言不是必要的。','True Or False','F',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80,'一个类中含有几个构造方法，称为构造方法的重载。对于重载的方法，其参数列表可以相同。','True Or False','T',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(81,'Java的类不允许多重继承，但接口支持多重继承','True Or False','T',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(82,'在java继承机制中，父类中的私有 private）成员不能被子类继承','True Or False','F',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(83,'有时候为了避免引起混淆，构造方法的方法名可以不与所属类名同名。','True Or False','F',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(84,'Java 语言的标识符是不区分大小写的。','True Or False','T',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(85,'JAVA所定义的版本中不包括：（）','Singel Choice','D',NULL,NULL,' JAVA2 EE','JAVA2 Card','JAVA2 ME','JAVA2 HE',NULL,NULL,NULL,NULL,NULL),(86,'下列说法正确的是（）','Singel Choice','A',NULL,NULL,'JAVA程序的main方法必须写在类里面','JAVA程序中可以有多个main方法','JAVA程序中类名必须与文件名一样',' JAVA程序的main方法中如果只有一条语句，可以不用{}(大括号)括起来',NULL,NULL,NULL,NULL,NULL),(87,' 变量命名规范说法正确的是（）','Singel Choice','B',NULL,NULL,'变量由字母、下划线、数字、$符号随意组成；','变量不能以数字作为开头；','A和a在java中是同一个变量；','不同类型的变量，可以起相同的名字；',NULL,NULL,NULL,NULL,NULL),(88,'下列javaDoc注释正确的是（）','Singel Choice','C',NULL,NULL,' /*我爱北京天安门*/','//我爱北京天安门*/',' /**我爱北京天安门*/',' /*我爱北京天安门**/',NULL,NULL,NULL,NULL,NULL),(89,'为一个boolean类型变量赋值时，可以使用()方式','Singel Choice','B',NULL,NULL,'boolean = 1;',' boolean a = (9 >= 10);','boolean a=\"真\";','boolean a = = false;',NULL,NULL,NULL,NULL,NULL),(90,'以下()不是合法的标识符','Singel Choice','C',NULL,NULL,'STRING','x3x;','void','de$f',NULL,NULL,NULL,NULL,NULL),(91,'表达式(11+3*8)/4%3的值是()','Singel Choice','D',NULL,NULL,'31',' 0','1',' 2',NULL,NULL,NULL,NULL,NULL),(92,'（）表达式不可以作为循环条件','Singel Choice','A',NULL,NULL,' i++;',' i>5;',' bEqual = str.equals(\"q\");',' count = = i;   ',NULL,NULL,NULL,NULL,NULL),(93,'运算符优先级别排序正确的是（）','Singel Choice','A',NULL,NULL,'由高向低分别是：()、!、算术运算符、关系运算符、逻辑运算符、赋值运算符；',' 由高向低分别是：()、关系运算符、算术运算符、赋值运算符、!、逻辑运算符；','由高向低分别是：()、算术运算符、逻辑运算符、关系运算符、!、赋值运算符；','由高向低分别是：()、!、关系运算符、赋值运算符、算术运算符、逻辑运算符；',NULL,NULL,NULL,NULL,NULL),(94,' 下列值不为true的表达式有（）。','Singel Choice','C',NULL,NULL,' \"john\" = = \"john\"','\"john\".equals(\"john\")','\"john\" = \"john\"','\"john\".equals(new String(\"john\"))',NULL,NULL,NULL,NULL,NULL),(95,'浮点型数据根据数据存储长度和数值精度的不同，进一步分为float和( )两种类型。','Fill In The Blank',' double',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(96,'使用关键字()来定义逻辑变量。','Fill In The Blank',' boolean ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(97,'一个long型数据在内存占( )个字节。','Fill In The Blank',' 8',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(98,'表示制表符(水平空格)的字符常量是(  )。','Fill In The Blank','\'\\t\'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99,'定义字符串变量s存储空字符串的表达式是(  )。','Fill In The Blank','String s = \"\";',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(100,'定义字符串变量s存储空格构成的字符串的表达式是( )。','Fill In The Blank',' String s= \" \";',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(101,'表达式(int)((double)(3)/2)的值是( )。','Fill In The Blank','1 ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(102,'已知某一天是一周的第3天(从1开始)，则计算该天之后第n天是一周的第几天的表达式为( )。','Fill In The Blank','(2+n) % 7 + 1 ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(103,'执行语句inta, b, c; a=1; b=3; c=(a+b>3 ? ++a : b++);后，b的值为(  )。','Fill In The Blank','3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(104,'已知字符\'1\'的Unicode值为49，则语句System.out.println(\'1\'+2);输出(  )。','Fill In The Blank','51',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz`
--

DROP TABLE IF EXISTS `quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quiz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` varchar(255) CHARACTER SET latin1 NOT NULL,
  `start_time` bigint(20) NOT NULL,
  `end_time` bigint(20) DEFAULT NULL,
  `quiz_name` varchar(100) DEFAULT NULL,
  `teacher_id` int(11) NOT NULL,
  `quiz_type` varchar(20) DEFAULT NULL,
  `student_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz`
--

LOCK TABLES `quiz` WRITE;
/*!40000 ALTER TABLE `quiz` DISABLE KEYS */;
INSERT INTO `quiz` VALUES (19,'[1,5,10,11,12,3,4,14,15,16,8,20,21,22,23,9,24,6,27,37,30,36]',1577860200000,1577863800000,'testprivate',2,'private','[9,10,11,12,13]'),(20,'[1,5,10,11,12,3,4,14,15,16,8,20,21,22,23,9,24,6,27,37,30,36]',1589104080000,1589107680000,'formaltest',2,'private','[13]'),(21,'[13,12,11,10,5,18,16,15,14,4,23,21,22,20,8,34,24,29,27,37,36,30]',1590422400000,1590474600000,'testpublic',2,'public','[]'),(22,'[1,5,10,12,11,3,4,14,15,16,8,20,21,22,23,9,34,6,27,37,30,36]',1577860200000,1577871000000,'testfinal',2,'public','[]'),(23,'[1,5,10,11,13,4,14,15,16,18,8,20,21,22,23,9,24,6,27,37,30,36]',1590498000000,1590503400000,'testtest',2,'public','[]'),(24,'[1,5,10,11,12,4,14,15,16,18,8,20,21,22,23,9,24,6,27,37,30,36]',1590508800000,1590561180000,'淮阴工学院JAVA月考',2,'private','[8,10,12,13,49,52,62]');
/*!40000 ALTER TABLE `quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quizlog`
--

DROP TABLE IF EXISTS `quizlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quizlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quiz_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quizlog`
--

LOCK TABLES `quizlog` WRITE;
/*!40000 ALTER TABLE `quizlog` DISABLE KEYS */;
/*!40000 ALTER TABLE `quizlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) CHARACTER SET latin1 NOT NULL,
  `password` varchar(20) CHARACTER SET latin1 NOT NULL,
  `grade` varchar(10) CHARACTER SET latin1 NOT NULL,
  `class_name` varchar(5) CHARACTER SET latin1 DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_account_uindex` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'1101325829','1101325829','2000','1172','kazemi','teacher'),(3,'admin','admin','1000','1000','admin','admin'),(8,'1171325721','1171325721','2017','1172','黄从刚2','student'),(9,'1171325807','1171325807','2017','1172','王紫剑\r','student'),(10,'1171325809','1171325809','2017','1172','王江南\r','student'),(11,'1171325820','1171325820','2017','1171','杨超\r','student'),(12,'1171325821','1171325821','2017','1171','高鹏\r','student'),(13,'1171325829','1171325829','2017','1172','夏崇康','student'),(14,'1101325831','1101325831','1000',NULL,'xck3','teacher'),(15,'1161308433','1161308433','1000',NULL,'chenjiaye','teacher'),(49,'1171325801','1171325801','2016','1161','xck','student'),(51,'1101325833','1101325833','1000',NULL,'kzz','teacher'),(52,'1171325802','1171325802','2020','2027','kz','student'),(62,'1151308321','1151308321','2015','1153','wxs','student');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-29 11:06:45
