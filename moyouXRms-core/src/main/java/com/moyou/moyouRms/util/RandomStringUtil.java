package com.moyou.moyouRms.util;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

/**
 * 随机工具类
 * 
 * @author yubing
 * @time 2016年9月26日
 */
public class RandomStringUtil {

	/**
	 * 生成 0 至指定位数范围内的随机数
	 * 
	 * @param range
	 *            随机数范围上限
	 * @return 生成String类型随机数
	 */
	public static long random(int range) {
		return new Random().nextInt(range);
	}

	/**
	 * 生成指定长度的数字串
	 * 
	 * @param range
	 * @return
	 */
	public static String randomLen(int len) {
		long range = 1;
		for (int i = 0; i < len - 1; i++) {
			range = range * 10;
		}
		String ran = String.valueOf((long) ((Math.random() * 9 + 1) * range));
		return ran;
	}

	/**
	 * 生成用户登录令牌，不重复
	 * 
	 * @return 生成唯一的uuid字符串
	 */
	public static String token() {
		return UUID.fromString(
				UUID.nameUUIDFromBytes(UUID.randomUUID().toString().getBytes()).toString())
				.toString();
	}

	public static String captcha() {
		return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
	}

	/**
	 * 随机生成数字
	 * 
	 * @param randomSome
	 *            随机数个数
	 * @return
	 */
	public static String randomStr(int randomSome) {
		if (randomSome == 1) {
			return new Random().nextInt(10) + "";
		}
		int rangeNumber = 1;
		for (int i = 0; i < (randomSome - 1); i++) {
			rangeNumber = rangeNumber * 10;
		}
		return String.valueOf((int) ((Math.random() * ((1 << 3) + 1) + 1) * rangeNumber));
	}

	static Random random = new Random();
	static final int MONEY_MIN = 1;// 金额取值最小范围 单位分
	static {
		random.setSeed(System.currentTimeMillis());
	}

	/**
	 * 生产min和max之间的随机数，但是概率不是平均的，从min到max方向概率逐渐加大。
	 * 先平方，然后产生一个平方值范围内的随机数，再开方，这样就产生了一种“膨胀”再“收缩”的效果。
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	static long xRandom(long min, long max) {
		return sqrt(nextLong(sqr(max - min)));
	}

	/**
	 * 
	 * @param total
	 *            红包总额
	 * @param count
	 *            红包个数
	 * @param max
	 *            每个小红包的最大额
	 * @param min
	 *            每个小红包的最小额
	 * @return 存放生成的每个小红包的值的数组
	 */
	public static long[] generate(long total, int count, long max, long min) {
		long[] result = new long[count];

		/*
		 * if ((max * count) < total) {// 最大区间不能大于总金额 return null; }
		 */
		if (count == 0) {// 个数最小为1
			return null;
		}
		if (total / count <= 0) {// 每个红包必须>=1
			return null;
		}
		long average = total / count;
		if (average >= max) {
			max = average + 1;
		}
		// long a = average - min;
		// long b = max - min;

		//
		// 这样的随机数的概率实际改变了，产生大数的可能性要比产生小数的概率要小。
		// 这样就实现了大部分红包的值在平均数附近。大红包和小红包比较少。
		// long range1 = sqr(average - min);
		// long range2 = sqr(max - average);

		for (int i = 0; i < result.length; i++) {
			// 因为小红包的数量通常是要比大红包的数量要多的，因为这里的概率要调换过来。
			// 当随机数>平均值，则产生小红包
			// 当随机数<平均值，则产生大红包
			if (nextLong(min, max) > average) {
				// 在平均线上减钱
				// long temp = min + sqrt(nextLong(range1));
				long temp = min + xRandom(min, average);
				result[i] = temp;
				total -= temp;
				// System.out.println("大大"+i);
			} else {
				// 在平均线上加钱
				// System.out.println("小小"+i);
				// long temp = max - sqrt(nextLong(range2));
				long temp = max - xRandom(average, max);
				result[i] = temp;
				total -= temp;
			}
		}
		// 如果还有余钱，则尝试加到红包里，如果加不进去，则尝试下一个。
		int len = result.length;
		Random ran = new Random();
		while (total > 0) {

			// for (int i = 0; i < result.length; i++) {
			// if (total > 0 && result[i] <= max) {
			result[ran.nextInt(len)]++;
			total--;
			// }
			// }
		}
		// 如果钱是负数了，还得从已生成的红包中抽取回来
		while (total < 0) {
			// for (int i = 0; i < result.length; i++) {
			// if (total < 0 && result[i] >= min) {
			int index = ran.nextInt(len);
			if (result[index] >= 2) {
				result[index]--;
				total++;
			}

			// }
			// }
		}
		return result;
	}

	/**
	 * 
	 * @param total
	 *            红包总额
	 * @param count
	 *            红包个数
	 * @param max
	 *            每个小红包的最大额
	 * @param min
	 *            每个小红包的最小额
	 * @return 存放生成的每个小红包的值的数组
	 */
	public static Long[] generateLong(long total, int count, long max, long min) {
		Long[] result = new Long[count];

		/*
		 * if ((max * count) < total) {// 最大区间不能大于总金额 return null; }
		 */
		if (count == 0) {// 个数最小为1
			return null;
		}
		if (total / count <= 0) {// 每个红包必须>=1
			return null;
		}
		long average = total / count;
		if (average >= max) {
			max = average + 1;
		}
		// long a = average - min;
		// long b = max - min;

		//
		// 这样的随机数的概率实际改变了，产生大数的可能性要比产生小数的概率要小。
		// 这样就实现了大部分红包的值在平均数附近。大红包和小红包比较少。
		// long range1 = sqr(average - min);
		// long range2 = sqr(max - average);

		for (int i = 0; i < result.length; i++) {
			// 因为小红包的数量通常是要比大红包的数量要多的，因为这里的概率要调换过来。
			// 当随机数>平均值，则产生小红包
			// 当随机数<平均值，则产生大红包
			if (nextLong(min, max) > average) {
				// 在平均线上减钱
				// long temp = min + sqrt(nextLong(range1));
				long temp = min + xRandom(min, average);
				result[i] = temp;
				total -= temp;
				// System.out.println("大大"+i);
			} else {
				// 在平均线上加钱
				// System.out.println("小小"+i);
				// long temp = max - sqrt(nextLong(range2));
				long temp = max - xRandom(average, max);
				result[i] = temp;
				total -= temp;
			}
		}
		// 如果还有余钱，则尝试加到红包里，如果加不进去，则尝试下一个。
		int len = result.length;
		Random ran = new Random();
		while (total > 0) {

			// for (int i = 0; i < result.length; i++) {
			// if (total > 0 && result[i] <= max) {
			result[ran.nextInt(len)]++;
			total--;
			// }
			// }
		}
		// 如果钱是负数了，还得从已生成的红包中抽取回来
		while (total < 0) {
			// for (int i = 0; i < result.length; i++) {
			// if (total < 0 && result[i] >= min) {
			int index = ran.nextInt(len);
			if (result[index] >= 2) {
				result[index]--;
				total++;
			}

			// }
			// }
		}
		return result;
	}

	public static long[] generate(long total, int count, long max) {
		return generate(total, count, max, MONEY_MIN);
	}

	public static Long[] generateLong(long total, int count, long max) {
		long[] lon = generate(total, count, max, MONEY_MIN);
		Long[] newLong = new Long[lon.length];
		for (int i = 0; i < lon.length; i++) {
			newLong[i] = lon[i];
		}
		return newLong;
	}

	public static long[] generate(long total, int count) {
		long max = total / 3 * 2;
		BigDecimal moneyBD = new BigDecimal(total + "");// 防止精度问题，用字符串
		BigDecimal divisor = new BigDecimal(3 + "");
		long doubleMax = moneyBD.divide(divisor, 2, BigDecimal.ROUND_DOWN).longValue();// 小数后2位，多余位数直接截断
		if (doubleMax <= 0) {
			max = total;
		} else {
			max = doubleMax;
		}
		return generate(total, count, max, MONEY_MIN);
	}

	public static Long[] generateLong(long total, int count) {
		long max = total / 3 * 2;
		BigDecimal moneyBD = new BigDecimal(total + "");// 防止精度问题，用字符串
		BigDecimal divisor = new BigDecimal(3 + "");
		long doubleMax = moneyBD.divide(divisor, 2, BigDecimal.ROUND_DOWN).longValue();// 小数后2位，多余位数直接截断
		if (doubleMax <= 0) {
			max = total;
		} else {
			max = doubleMax;
		}
		return generateLong(total, count, max, MONEY_MIN);
	}

	static long sqrt(long n) {
		// 开方
		return (long) Math.sqrt(n);
	}

	static long sqr(long n) {
		// 平方
		return n * n;
	}

	static long nextLong(long n) {
		if (n == 0)
			return 0L;
		return random.nextInt((int) n);
	}

	static long nextLong(long min, long max) {
		return random.nextInt((int) (max - min + 1)) + min;
	}

	public static double getRandomMoney(RedPackage _redPackage) {
		// remainSize 剩余的红包数量
		// remainMoney 剩余的钱
		if (_redPackage.remainSize == 1) {
			_redPackage.remainSize--;
			return (double) Math.round(_redPackage.remainMoney * 100) / 100;
		}
		Random r = new Random();
		double min = 0.01; //
		double max = _redPackage.remainMoney / _redPackage.remainSize * 2;
		double money = r.nextDouble() * max;
		money = money <= min ? 0.01 : money;
		money = Math.floor(money * 100) / 100;
		_redPackage.remainSize--;
		_redPackage.remainMoney -= money;
		return money;
	}

	static class RedPackage {
		private int remainSize;
		private double remainMoney;
	}

	public static void main(String[] args) {

		long[] lo = getNumberByCount(20, 171);
		long lon = 0;
		for (int i = 0; i < lo.length; i++) {
			lon += lo[i];
			System.out.println(lo[i]);
		}
		System.out.println(lon);
	}

	static Long[] generateLongs(Long total, int count) {
		Long max = total / count;
		Long longg = 0L;
		Long[] longs = new Long[count];
		for (int i = 0; i < longs.length; i++) {
			longs[i] = (long) new Random().nextInt(Integer.valueOf(max.toString()));
			longg += longs[i];
			System.out.println(longs[i]);
		}
		System.out.println(longg + "--------" + total);
		return longs;
	}

	public static long[] getNumberByCount(int count, long sum) {
		long[] lo = new long[count];
		int num = (int) sum / count;
		for (int i = 0; i < count; i++) {
			long l = (int) (Math.random() * 10) + 1;
			if (i % 2 == 0) {
				lo[i] = l + num;
			} else {
				if ((num - l) < 0) {
					int index = (int) (Math.random() * 5) + 1;
					lo[i - 1] = num > index ? num + index : num;
					lo[i] = num > index ? num - index : num;
				} else {
					lo[i] = num - l;
				}
			}

		}
		return lo;
	}

}
