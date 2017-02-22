日记小软件，

1. 使用GridView制作日历，
	- MainActivity			用来完成整体任务，
		-dataFactory()	返回 List<Map<String,Object>>
			完成数据加载任务，
		
		- monthData（int year,int month） 计算当月初第一天，是星期几，（星期天记作0）
		- month数组从0开始，这里需要注意减一，week从0开始需要加一
		- 设置传入年月，根据传入年月计算该月份第一天是星期几
		* 计算年月这个小模块应该抽取出来，使用JUnit单元测试进行测测试，节省时间，放在模拟器中会浪费大量时间
		  月份计算容易出错，
		  完成
		  
		  
		- 左右滑动实现月份的切换 
		使用 simpleGusTureListener 实现了对GridView滑动监听操作，
		* 重新显示数据时需要清空原先的数据，否则ListView会变得越来越长
			**设置左右滑动监听器后Item监听器居然无法监听
			失误，我的失误，两个监听器没冲突实现基本的功能
		完成
		
		
		- 对每天进行点击可以查看当天日记等信息
		实现接口 onItemClickListener接口，
		* 注意：需要加载监听器到GridView
		完成
		
		
		- 如果当天有日记，则改变他的颜色
		
		
		
		- 每天下面有两个小TextView 用来标记当天记单词数量
		
			
			
	- calendar.xml 			用来作为 GridView的布局文件
	- calendar_item.xml		用来实现日历每个块所显示的内容
	- calendar_item_head.xml显示星期一，二，三
	- 为了设置为圆角在 drawable中加入：background.xml	background_head.xml
	
	
2，