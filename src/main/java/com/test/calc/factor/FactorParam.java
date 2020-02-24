package com.test.calc.factor;

/**
 * 参数因子
 */
public class FactorParam{

	public static final int OPT_ADD = 1;
	public static final int OPT_DEC = 2;
	public static final int OPT_MUL = 3;
	public static final int OPT_DIV = 4;

	public static final int OPT_CONST_DEFAULT = -1;
	public static final int OPT_CONST_NONE = 0;
	public static final int OPT_CONST_ADD = 1;
	public static final int OPT_CONST_DEC = 2;
	public static final int OPT_CONST_MUL = 3;
	public static final int OPT_CONST_DIV = 4;
	public static final int OPT_CONST_BE_DEC = 5;

	private FactorMainQuestion question;

	private int factorIndex = -1;
	private int result = -1;

	private boolean calc = false;

	/**
	 * 是否可成为条件-1可以, 0+第几个条件
	 */
	private int conditionIndex = -1;
	private int resultIndex = -1;

	private int conditionBeforeOpt = -1;
	private int conditionAfterOpt = -1;
	private int opt = -1;// 0:预留 1:+ 2:- 3:* 4:整除

	private int min = -1;
	private int max = -1;

	private int constant = 0;
	private int constOpt = OPT_CONST_DEFAULT;
	private int constIndex = -1;

	private int minIndex = -1;
	private int maxIndex = -1;

	public FactorParam(int factorIndex, FactorMainQuestion question){
		this.factorIndex = factorIndex;
		this.question = question;
	}


	public int getMin(){
		return min;
	}

	public int getMax(){
		return max;
	}

	public void setRandom(int min, int max){
		this.max = max;
		this.min = min;
	}

	public void setRandomIndex(int minIndex, int maxIndex){
		this.minIndex = minIndex;
		this.maxIndex = maxIndex;
	}

	public int getConstant(){
		return constant;
	}

	public int getConstOpt(){
		return constOpt;
	}

	public int getConstIndex(){
		return constIndex;
	}

	public int getFactorIndex(){
		return factorIndex;
	}

	public void setFactorIndex(int factorIndex){
		this.factorIndex = factorIndex;
	}

	public int getConditionIndex(){
		return conditionIndex;
	}

	public void setConditionIndex(int conditionIndex){
		this.conditionIndex = conditionIndex;
	}

	public int getResultIndex(){
		return resultIndex;
	}

	public void setResultIndex(int resultIndex){
		this.resultIndex = resultIndex;
	}

	public int getConditionBeforeOpt(){
		return conditionBeforeOpt;
	}

	public int getConditionAfterOpt(){
		return conditionAfterOpt;
	}

	public int getOpt(){
		return opt;
	}

	public void setOpt(int beforeIndex, int afterIndex, int opt){
		this.conditionBeforeOpt = beforeIndex;
		this.conditionAfterOpt = afterIndex;
		this.opt = opt;
	}

	public FactorMainQuestion getQuestion(){
		return question;
	}

	public String getResult(){
		return String.valueOf(result);
	}

	public void setConstOpt(int beforeIndex, int afterConst, int opt){
		this.constIndex = beforeIndex;
		this.constant = afterConst;
		this.constOpt = opt;
	}

	public int calc(){
		if(calc){
			return result;
		}else if(conditionBeforeOpt != -1 && conditionAfterOpt != -1 && opt != -1){
			FactorParam paramBefore = question.getParamByIndex(conditionBeforeOpt);
			FactorParam paramAfter = question.getParamByIndex(conditionAfterOpt);
			int before = paramBefore.calc();
			int after = paramAfter.calc();
			switch(opt){
				case OPT_ADD:
					result = before + after;
					break;
				case OPT_DEC:
					result = before - after;
					break;
				case OPT_MUL:
					result = before * after;
					break;
				case OPT_DIV:
					result = before / after;
					break;
			}
			calc = true;
		}else if(min != -1 && max != -1){
			result = RandomUtil.getRandom(min, max);
			calc = true;
		}else if(constIndex != -1 && constOpt != OPT_CONST_DEFAULT){
			FactorParam paramBefore = question.getParamByIndex(constIndex);
			int before = paramBefore.calc();
			int after = constant;
			switch(constOpt){
				case OPT_CONST_ADD:
					result = before + after;
					break;
				case OPT_CONST_DEC:
					result = before - after;
					break;
				case OPT_CONST_MUL:
					result = before * after;
					break;
				case OPT_CONST_DIV:
					result = before / after;
					break;
				case OPT_CONST_BE_DEC:
					result = after - before;
					break;
			}
			calc = true;
		}else if(minIndex > -1 && maxIndex > -1){
			FactorParam minParam = question.getParamByIndex(minIndex);
			FactorParam maxParam = question.getParamByIndex(maxIndex);
			result = RandomUtil.getRandom(minParam.calc(), maxParam.calc());
			calc = true;
		}
//		System.out.println(factorIndex + "的结果=" + result);
		return result;
	}
}
