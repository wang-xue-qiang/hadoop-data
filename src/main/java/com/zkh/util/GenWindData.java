package com.zkh.util;



/**
 * 
 * @Description:模拟生成数据
 * @author wangxueqiang
 * @date 2018年9月25日 下午2:31:45
 * 内蒙，新疆，河北和内蒙交界的地方（张家口附近），风力发电机一般的启动风速在3-4m/s，切出风速（大风停机风速）为19-25m/s，
 * 我们一般看的不是风速多少，而是看的是一年有多少标准小时，所谓的标准小时就是指风力发电机满发的风速，
 * 一般在11-15m/s，一年能达到2000个标准小时，就是风资源很丰富的地区了，至于风向，常年变动不大最好，变化大了也没关系，因为风机是可以自动对风的
 * 3-30m/s
 */
public class GenWindData {
	//风机类型
	private final static String FANS[] = {"外置式轴流风机","双城风机 TBNS增压鼓风机","离心风机","插入式风机","工业风机","重力通风机"};
	//内蒙古主要风场
	private final static String WINDFIELDS[] = {"通辽扎鲁特风电场","内蒙古卓资风电场","内蒙古赤峰赛罕坝风电场","内蒙古辉腾锡勒风电场","内蒙古平顶山风电场","内蒙古珠日河风电场"};
	//风速与电转换率
	private final static double WINDTOELECTRATE[] = {0.23,0.19,0.22,0.40,0.15,0.1};
	//最大风速与最小风速
	private final static int MAXSPEED =30,MINSPEED=3;
	
	//生成某一范围内风速
	public static double genWindSpeed(){
	     return  MAXSPEED*Math.random()+MINSPEED;
	}
	//生成风机
	public static String genFan(){
		int index = (int)(Math.random()*FANS.length);
	     return  FANS[index] ;
	}
	//生成风场
	public static String genWindField(){
		int index = (int)(Math.random()*WINDFIELDS.length);
	     return  WINDFIELDS[index] ;
	}
	//风能转换为电能
	public static double genElec(double windSpeed){
		int index = (int)Math.random()*WINDTOELECTRATE.length+1;
	     return  WINDTOELECTRATE[index] * windSpeed;
	}
	//测试
	public static void main(String[] args) {
		for (int i = 0; i < 60; i++) {
			System.out.println("==================内蒙古风电场====================");
			System.out.println("风场："+genFan());
			System.out.println("风机："+genWindField());
			System.out.println("风速："+genWindSpeed());
			System.out.println("转化电能："+genElec(genWindSpeed()));
			System.out.println("==================内蒙古风电场====================");	
		}
	}
}
