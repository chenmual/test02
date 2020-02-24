package com.test.calc;

import com.test.calc.factor.FactorMainQuestion;
import com.test.calc.factor.FactorParam;

import java.util.ArrayList;
import java.util.List;

public class testCalc{

	// X = Y * A
	// X - Y = B

	public static void main(String[] args){
		List<String> strs = new ArrayList<>();
//		strs.add(q1());
//		strs.add(q2());
//		strs.add(q3());
//		strs.add(q4());
//		strs.add(q5());
		strs.add(q6());
		strs.add(q7());

		System.out.println();
		for(int i = 0; i < strs.size(); i++){
			System.out.println("[" + i + "]=" + strs.get(i));
		}
	}

	public static String q1(){
		String title = "已知一张桌子的价钱是一把椅子的%s倍，又知每张桌子比每把椅子多%s元。\n问桌子和椅子各多少元？\n";

		FactorMainQuestion factorMainQuestion = new FactorMainQuestion(title);

		FactorParam chair = new FactorParam(0, factorMainQuestion);
		chair.setRandom(40, 80);
		chair.setResultIndex(1);
		factorMainQuestion.addParam(chair);

		FactorParam multiple = new FactorParam(1, factorMainQuestion);
		multiple.setRandom(3, 10);
		multiple.setConditionIndex(0);
		factorMainQuestion.addParam(multiple);

		FactorParam desk = new FactorParam(2, factorMainQuestion);
		desk.setOpt(0, 1, FactorParam.OPT_MUL);
		desk.setResultIndex(0);
		factorMainQuestion.addParam(desk);

		FactorParam diff = new FactorParam(3, factorMainQuestion);
		diff.setOpt(2, 0, FactorParam.OPT_DEC);
		diff.setConditionIndex(1);
		factorMainQuestion.addParam(diff);

		factorMainQuestion.calc();
		return factorMainQuestion.returnResult();
	}


	public static String q2(){
		String title = "%s箱苹果重%s千克。一箱梨比一箱苹果多%s千克，\n问%s箱梨重多少千克？\n";

		FactorMainQuestion factorMainQuestion = new FactorMainQuestion(title);

		FactorParam app = new FactorParam(0, factorMainQuestion);
		app.setRandom(12, 21);
		factorMainQuestion.addParam(app);

		FactorParam diff = new FactorParam(1, factorMainQuestion);
		diff.setRandom(3, 7);
		diff.setConditionIndex(2);
		factorMainQuestion.addParam(diff);

		FactorParam li = new FactorParam(2, factorMainQuestion);
		li.setOpt(0, 1, FactorParam.OPT_ADD);
		factorMainQuestion.addParam(li);

		FactorParam appXiang = new FactorParam(3, factorMainQuestion);
		appXiang.setRandom(2, 4);
		appXiang.setConditionIndex(0);
		factorMainQuestion.addParam(appXiang);

		FactorParam appX = new FactorParam(4, factorMainQuestion);
		appX.setOpt(0, 3, FactorParam.OPT_MUL);
		appX.setConditionIndex(1);
		factorMainQuestion.addParam(appX);

		FactorParam liXiang = new FactorParam(5, factorMainQuestion);
		liXiang.setRandom(2, 4);
		liXiang.setConditionIndex(3);
		factorMainQuestion.addParam(liXiang);


		FactorParam liX = new FactorParam(6, factorMainQuestion);
		liX.setOpt(2, 5, FactorParam.OPT_MUL);
		liX.setResultIndex(0);
		factorMainQuestion.addParam(liX);

		factorMainQuestion.calc();
		return factorMainQuestion.returnResult();
	}


	public static String q3(){
		String title = "甲乙二人从两地同时相对而行，经过%s小时，在距离中点%s千米处相遇。甲比乙速度快，\n问甲每小时比乙快多少千米？\n";

		FactorMainQuestion factorMainQuestion = new FactorMainQuestion(title);

		FactorParam half = new FactorParam(0, factorMainQuestion);
		half.setRandom(1, 5);
		factorMainQuestion.addParam(half);

		FactorParam diff = new FactorParam(1, factorMainQuestion);
		diff.setConstOpt(0, 2, FactorParam.OPT_CONST_MUL);
		diff.setResultIndex(0);
		factorMainQuestion.addParam(diff);

		FactorParam hour = new FactorParam(2, factorMainQuestion);
		hour.setRandom(2, 12);
		hour.setConditionIndex(0);
		factorMainQuestion.addParam(hour);

		FactorParam dis = new FactorParam(3, factorMainQuestion);
		dis.setOpt(0, 2, FactorParam.OPT_MUL);
		dis.setConditionIndex(1);
		factorMainQuestion.addParam(dis);

		factorMainQuestion.calc();
		return factorMainQuestion.returnResult();
	}


	public static String q4(){
		String title = "张三和李四花同样的钱买了同一种笔.之后张三要了%s支,李四要了%s支,张三又给李四%s元,\n请问每支笔多少元钱？\n";

		FactorMainQuestion factorMainQuestion = new FactorMainQuestion(title);

		FactorParam per = new FactorParam(0, factorMainQuestion);
		per.setRandom(1, 14);
		per.setResultIndex(0);
		factorMainQuestion.addParam(per);

		FactorParam buyCount = new FactorParam(1, factorMainQuestion);
		buyCount.setRandom(8, 13);
		factorMainQuestion.addParam(buyCount);

		FactorParam more = new FactorParam(2, factorMainQuestion);
		more.setRandom(1, 5);
		factorMainQuestion.addParam(more);

		FactorParam a = new FactorParam(3, factorMainQuestion);
		a.setOpt(1, 2, FactorParam.OPT_ADD);
		a.setConditionIndex(0);
		factorMainQuestion.addParam(a);

		FactorParam b = new FactorParam(4, factorMainQuestion);
		b.setOpt(1, 2, FactorParam.OPT_DEC);
		b.setConditionIndex(1);
		factorMainQuestion.addParam(b);

		FactorParam give = new FactorParam(5, factorMainQuestion);
		give.setOpt(2, 0, FactorParam.OPT_MUL);
		give.setConditionIndex(2);
		factorMainQuestion.addParam(give);

		factorMainQuestion.calc();
		return factorMainQuestion.returnResult();
	}

	public static String q5(){

		String title = "甲乙两辆客车上午%s时同时从两个车站出发，相向而行，经过一段时间，两车同时到达一条河的两岸。由于河上的桥正在维修，车辆禁止通行，两车需交换乘客，然后按原路返回各自出发的车站，到站时已是下午%s点。甲车每小时行%s千米，乙车每小时行%s千米，\n问两地相距多少千米？（交换乘客的时间略去不计）";

		FactorMainQuestion factorMainQuestion = new FactorMainQuestion(title);

		FactorParam halfTime = new FactorParam(0, factorMainQuestion);
		halfTime.setRandom(1, 5);
		factorMainQuestion.addParam(halfTime);

		FactorParam fullTime = new FactorParam(1, factorMainQuestion);
		fullTime.setConstOpt(0, 2, FactorParam.OPT_CONST_MUL);
		factorMainQuestion.addParam(fullTime);

		FactorParam time1 = new FactorParam(2, factorMainQuestion);
		time1.setRandom(1, 1);
		factorMainQuestion.addParam(time1);


		FactorParam startHour = new FactorParam(3, factorMainQuestion);
		startHour.setRandomIndex(2, 1);
		factorMainQuestion.addParam(startHour);

		FactorParam startTime = new FactorParam(4, factorMainQuestion);
		startTime.setConstOpt(3, 12, FactorParam.OPT_CONST_BE_DEC);
		startTime.setConditionIndex(0);
		factorMainQuestion.addParam(startTime);


		FactorParam endHour = new FactorParam(5, factorMainQuestion);
		endHour.setOpt(1, 3, FactorParam.OPT_DEC);
		factorMainQuestion.addParam(endHour);


		FactorParam endTime = new FactorParam(6, factorMainQuestion);
		endTime.setConstOpt(5, 0, FactorParam.OPT_CONST_ADD);
		endTime.setConditionIndex(1);
		factorMainQuestion.addParam(endTime);

		FactorParam speedA = new FactorParam(7, factorMainQuestion);
		speedA.setRandom(31, 48);
		speedA.setConditionIndex(2);
		factorMainQuestion.addParam(speedA);

		FactorParam speedB = new FactorParam(8, factorMainQuestion);
		speedB.setRandom(36, 44);
		speedB.setConditionIndex(3);
		factorMainQuestion.addParam(speedB);

		FactorParam speed = new FactorParam(9, factorMainQuestion);
		speed.setOpt(7, 8, FactorParam.OPT_ADD);
		factorMainQuestion.addParam(speed);

		FactorParam distance = new FactorParam(10, factorMainQuestion);
		distance.setOpt(9, 0, FactorParam.OPT_MUL);
		distance.setResultIndex(0);
		factorMainQuestion.addParam(distance);

		factorMainQuestion.calc();
		return factorMainQuestion.returnResult();
	}


	public static String q6(){

		String title = "学校组织两个课外兴趣小组郊游,第一组每小时走%s米,第二组每小时行%s米.两组同时出发%s分钟后,第一小组停下来参观果园,用了%s分钟,再去追第二小组.\n问多少分钟能追上第二小组?\n";

		FactorMainQuestion factorMainQuestion = new FactorMainQuestion(title);

		FactorParam o1 = new FactorParam(0, factorMainQuestion);
		o1.setRandom(2, 3);
		factorMainQuestion.addParam(o1);

		FactorParam o2 = new FactorParam(1, factorMainQuestion);
		o2.setRandom(4, 8);
		factorMainQuestion.addParam(o2);

		FactorParam k = new FactorParam(2, factorMainQuestion);
		k.setRandom(1, 3);
		factorMainQuestion.addParam(k);


		FactorParam t2 = new FactorParam(3, factorMainQuestion);
		t2.setOpt(1, 2, FactorParam.OPT_MUL);
		factorMainQuestion.addParam(t2);

		FactorParam tAll = new FactorParam(4, factorMainQuestion);
		tAll.setOpt(0, 2, FactorParam.OPT_MUL);
		factorMainQuestion.addParam(tAll);


		FactorParam vb = new FactorParam(5, factorMainQuestion);
		vb.setConstOpt(0, 1, FactorParam.OPT_CONST_MUL);
		factorMainQuestion.addParam(vb);


		FactorParam vDiff = new FactorParam(6, factorMainQuestion);
		vDiff.setConstOpt(1, 1, FactorParam.OPT_CONST_MUL);
		factorMainQuestion.addParam(vDiff);

		FactorParam pMax = new FactorParam(7, factorMainQuestion);
		pMax.setConstOpt(4, 1, FactorParam.OPT_CONST_DEC);
		factorMainQuestion.addParam(pMax);

		FactorParam pMin = new FactorParam(8, factorMainQuestion);
		pMin.setRandom(1, 1);
		factorMainQuestion.addParam(pMin);

		FactorParam catchTime = new FactorParam(9, factorMainQuestion);
		catchTime.setRandomIndex(8, 7);
		factorMainQuestion.addParam(catchTime);

		FactorParam t1 = new FactorParam(10, factorMainQuestion);
		t1.setOpt(4, 9, FactorParam.OPT_DEC);
		factorMainQuestion.addParam(t1);

		FactorParam va = new FactorParam(11, factorMainQuestion);
		va.setOpt(6, 5, FactorParam.OPT_ADD);
		factorMainQuestion.addParam(va);

		FactorParam speed1 = new FactorParam(12, factorMainQuestion);
		speed1.setConstOpt(11, 500, FactorParam.OPT_CONST_MUL);
		speed1.setConditionIndex(0);
		factorMainQuestion.addParam(speed1);

		FactorParam speed2 = new FactorParam(13, factorMainQuestion);
		speed2.setConstOpt(5, 500, FactorParam.OPT_CONST_MUL);
		speed2.setConditionIndex(1);
		factorMainQuestion.addParam(speed2);

		FactorParam time1 = new FactorParam(14, factorMainQuestion);
		time1.setConstOpt(10, 30, FactorParam.OPT_CONST_MUL);
		time1.setConditionIndex(2);
		factorMainQuestion.addParam(time1);

		FactorParam time2 = new FactorParam(15, factorMainQuestion);
		time2.setConstOpt(3, 30, FactorParam.OPT_CONST_MUL);
		time2.setConditionIndex(3);
		factorMainQuestion.addParam(time2);

		FactorParam timeCatch = new FactorParam(16, factorMainQuestion);
		timeCatch.setConstOpt(9, 30, FactorParam.OPT_CONST_MUL);
		timeCatch.setResultIndex(0);
		factorMainQuestion.addParam(timeCatch);


		factorMainQuestion.calc();
		return factorMainQuestion.returnResult();
	}


	public static String q7(){

		String title = "有甲乙两个仓库，每个仓库平均储存粮食%s吨。甲仓的存粮吨数比乙仓的%s倍少%s吨，\n甲、乙两仓各储存粮食多少吨？\n";

		FactorMainQuestion factorMainQuestion = new FactorMainQuestion(title);

		FactorParam halfDiff = new FactorParam(0, factorMainQuestion);
		halfDiff.setRandom(1, 6);
		factorMainQuestion.addParam(halfDiff);

		FactorParam diff = new FactorParam(1, factorMainQuestion);
		diff.setConstOpt(0, 2, FactorParam.OPT_CONST_MUL);
		diff.setConditionIndex(2);
		factorMainQuestion.addParam(diff);

		FactorParam bei = new FactorParam(2, factorMainQuestion);
		bei.setRandom(2, 4);
		bei.setConditionIndex(1);
		factorMainQuestion.addParam(bei);


		FactorParam bPart = new FactorParam(3, factorMainQuestion);
		bPart.setRandom(2, 5);
		factorMainQuestion.addParam(bPart);

		FactorParam b = new FactorParam(4, factorMainQuestion);
		b.setConstOpt(3, 10, FactorParam.OPT_CONST_MUL);
		b.setResultIndex(1);
		factorMainQuestion.addParam(b);


		FactorParam aPart = new FactorParam(5, factorMainQuestion);
		aPart.setOpt(4, 2, FactorParam.OPT_MUL);
		factorMainQuestion.addParam(aPart);


		FactorParam a = new FactorParam(6, factorMainQuestion);
		a.setOpt(5, 1, FactorParam.OPT_DEC);
		a.setResultIndex(0);
		factorMainQuestion.addParam(a);

		FactorParam full = new FactorParam(7, factorMainQuestion);
		full.setOpt(6, 4, FactorParam.OPT_ADD);
		factorMainQuestion.addParam(full);

		FactorParam avg = new FactorParam(8, factorMainQuestion);
		avg.setConstOpt(7, 2, FactorParam.OPT_CONST_DIV);
		avg.setConditionIndex(0);
		factorMainQuestion.addParam(avg);

		factorMainQuestion.calc();
		return factorMainQuestion.returnResult();
	}
}
