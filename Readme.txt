日记小软件，

1. 使用GridView制作日历，
	- MainActivity			用来完成整体任务，
		-dataFactory()	返回 List<Map<String,Object>>
			完成数据加载任务，
		
		- monthData（int year,int month） 计算当月初第一天，是星期几，（星期天记作0）
		- month数组从0开始，这里需要注意减一，week从0开始需要加一
		- 设置传入年月，根据传入年月计算该月份第一天是星期几
		* 计算年月这个小模块应该抽取出来，使用JUnit单元测试进行测测试，节省时间，放在模拟器中会浪费大量时间
		
			
	- calendar.xml 			用来作为 GridView的布局文件
	- calendar_item.xml		用来实现日历每个块所显示的内容
	-
	
	
2，