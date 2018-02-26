package cetc.software.lujunzizi.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cetc.software.lujunzizi.data.dataobject.Movie;
import cetc.software.lujunzizi.web.service.model.MovieJsonModel;
import cetc.software.lujunzizi.web.service.model.MovieModel;
import cetc.software.lujunzizi.web.service.model.SimpleMovie;

public class MovieUtil {
	static String[] countryArr = new String[] { "阿富汗", "安哥拉", "阿尔巴尼亚", "阿联酋",
			"阿根廷", "亚美尼亚", "法属南半球和南极领地", "澳大利亚", "奥地利", "阿塞拜疆", "布隆迪", "比利时",
			"贝宁", "布基纳法索", "孟加拉国", "保加利亚", "巴哈马", "波斯尼亚和黑塞哥维那", "白俄罗斯", "伯利兹",
			"百慕大", "玻利维亚", "巴西", "文莱", "不丹", "博茨瓦纳", "中非共和国", "加拿大", "瑞士",
			"智利", "中国", "象牙海岸", "喀麦隆", "刚果民主共和国", "刚果共和国", "哥伦比亚", "哥斯达黎加",
			"古巴", "北塞浦路斯", "塞浦路斯", "捷克共和国", "德国", "吉布提", "丹麦", "多明尼加共和国",
			"阿尔及利亚", "厄瓜多尔", "埃及", "厄立特里亚", "西班牙", "爱沙尼亚", "埃塞俄比亚", "芬兰", "斐济",
			"福克兰群岛", "法国", "加蓬", "英国", "格鲁吉亚", "加纳", "几内亚", "冈比亚", "几内亚比绍",
			"赤道几内亚", "希腊", "格陵兰", "危地马拉", "法属圭亚那", "圭亚那", "洪都拉斯", "克罗地亚", "海地",
			"匈牙利", "印尼", "印度", "爱尔兰", "伊朗", "伊拉克", "冰岛", "以色列", "意大利", "牙买加",
			"约旦", "日本", "哈萨克斯坦", "肯尼亚", "吉尔吉斯斯坦", "柬埔寨", "韩国", "科索沃", "科威特",
			"老挝", "黎巴嫩", "利比里亚", "利比亚", "斯里兰卡", "莱索托", "立陶宛", "卢森堡", "拉脱维亚",
			"摩洛哥", "摩尔多瓦", "马达加斯加", "墨西哥", "马其顿", "马里", "缅甸", "黑山", "蒙古",
			"莫桑比克", "毛里塔尼亚", "马拉维", "马来西亚", "纳米比亚", "新喀里多尼亚", "尼日尔", "尼日利亚",
			"尼加拉瓜", "荷兰", "挪威", "尼泊尔", "新西兰", "阿曼", "巴基斯坦", "巴拿马", "秘鲁", "菲律宾",
			"巴布亚新几内亚", "波兰", "波多黎各", "北朝鲜", "葡萄牙", "巴拉圭", "卡塔尔", "罗马尼亚", "俄罗斯",
			"卢旺达", "西撒哈拉", "沙特阿拉伯", "苏丹", "南苏丹", "塞内加尔", "所罗门群岛", "塞拉利昂",
			"萨尔瓦多", "索马里兰", "索马里", "塞尔维亚共和国", "苏里南", "斯洛伐克", "斯洛文尼亚", "瑞典",
			"斯威士兰", "叙利亚", "乍得", "多哥", "泰国", "塔吉克斯坦", "土库曼斯坦", "东帝汶",
			"特里尼达和多巴哥", "突尼斯", "土耳其", "坦桑尼亚联合共和国", "乌干达", "乌克兰", "乌拉圭", "美国",
			"乌兹别克斯坦", "委内瑞拉", "越南", "瓦努阿图", "西岸", "也门", "南非", "赞比亚", "津巴布韦",
			"香港", "台湾", "新加坡" };

	public static String convertCountryToCountryArr(String country) {
		List<String> countryList = new ArrayList<String>();
		for (int i = 0; i < countryArr.length; i++) {
			if (country.contains(countryArr[i]))
				countryList.add(countryArr[i]);
		}
		if (countryList.size() > 0) {
			String result = "";
			for (int i = 0; i < countryList.size() - 1; i++)
				result += (countryList.get(i) + "/");
			result += countryList.get(countryList.size() - 1);
			return result;
		} else {
			return "";
		}
	}

	public static int ConvertStringToYear(String date) {
		if (StringUtil.isBlank(date))
			return -1;
		return Integer.parseInt(date.substring(0, 4));
	}

	public static int ConvertStringToFilmLength(String length) {
		if (StringUtil.isBlank(length))
			return 0;
		String testStr = length.substring(0, length.indexOf("分钟")).trim();
		int FirstdigitIndex = testStr.length() - 1;
		for (int i = testStr.length() - 1; i >= 0; i--) {
			if (Character.isDigit(testStr.charAt(i))) {
				FirstdigitIndex = i;
				continue;
			} else {
				break;
			}
		}
		return Integer.parseInt(testStr.substring(FirstdigitIndex));
	}

	public static String[] ConvertStringToStringArray(String directors) {
		if (StringUtil.isBlank(directors))
			return new String[] {};
		return directors.split("/");
	}

	public static MovieJsonModel[][] handleMovieList(List<MovieModel> movieList) {
		Map<Integer, Map<String, Double[]>> movieMap = new HashMap<Integer, Map<String, Double[]>>();
		MovieJsonModel[][] movieArray = init(movieList);
		Iterator it = movieList.iterator();
		while (it.hasNext()) {
			MovieModel m = (MovieModel) it.next();
			for (String country : m.getCountries()) {
				Integer year = m.getYear();
				if (movieMap.containsKey(year)) {
					Map<String, Double[]> mMap = movieMap.get(year);
					if (mMap.containsKey(country)) {
						Double[] pointArr = mMap.get(country);
						// 人数
						pointArr[0] += m.getPoint_num();
						// 总分
						pointArr[1] += (m.getPoint_num() * m.getPoint());
						mMap.put(country, pointArr);
					} else {
						Double[] pointArr = new Double[] {
								(double) m.getPoint_num(),
								m.getPoint_num() * m.getPoint() };
						mMap.put(country, pointArr);
					}
					movieMap.put(year, mMap);
				} else {
					Map<String, Double[]> mMap = new HashMap<String, Double[]>();
					Double[] pointArr = new Double[] {
							(double) m.getPoint_num(),
							m.getPoint_num() * m.getPoint() };
					mMap.put(country, pointArr);
					movieMap.put(year, mMap);
				}
			}
		}
		for (int i = 0; i < movieArray.length; i++) {
			for (int j = 0; j < movieArray[0].length; j++) {
				MovieJsonModel m = movieArray[i][j];
				if (movieMap.containsKey(m.getYear())) {
					if (movieMap.get(m.getYear()).containsKey(m.getName())) {
						Double[] pointArr = movieMap.get(m.getYear()).get(
								m.getName());
						DecimalFormat df2 = new DecimalFormat("###.0");
						int movieNum = (int) (Double.parseDouble(df2
								.format(pointArr[1] / pointArr[0])) * 10);
						m.setValue(movieNum);
						movieArray[i][j] = m;
					}
				}
			}
		}
		return movieArray;
	}

	private static MovieJsonModel[][] init(List<MovieModel> movieList) {
		Set<String> countrySet = new HashSet<String>();
		Set<Integer> yearSet = new HashSet<Integer>();
		for (MovieModel m : movieList) {
			for (String country : m.getCountries()) {
				countrySet.add(country);
			}
			yearSet.add(m.getYear());
		}
		MovieJsonModel[][] movieArray = new MovieJsonModel[yearSet.size()][countrySet
				.size()];
		List<String> countryList = new ArrayList<String>(countrySet);
		List<Integer> yearList = new ArrayList<Integer>(yearSet);
		Collections.sort(yearList);
		for (int i = 0; i < yearList.size(); i++) {
			int year = yearList.get(i);
			for (int j = 0; j < countryList.size(); j++) {
				MovieJsonModel m = new MovieJsonModel();
				m.setName(countryList.get(j));
				m.setValue(0);
				m.setRank(j + 1);
				m.setYear(year);
				movieArray[i][j] = m;
			}
		}
		return movieArray;
	}
	
	public static Map<String,List<SimpleMovie>> initActorsMap(List<MovieModel> movieList){
		Map<String,List<SimpleMovie>> actorsMap = new HashMap<String,List<SimpleMovie>>();
		Iterator it = movieList.iterator();
		while(it.hasNext()){
			MovieModel m = (MovieModel) it.next();
			String[] actors = m.getActors();
			SimpleMovie sm = new SimpleMovie();
			sm.setTitle(m.getTitle());
			sm.setPoint(m.getPoint());
			sm.setRatingNum(m.getPoint_num());
			for(String actor:actors){
				List<SimpleMovie> tmpList = new ArrayList<SimpleMovie>();
				if(actorsMap.containsKey(actor)){
					tmpList = actorsMap.get(actor);
				}
				tmpList.add(sm);
				actorsMap.put(actor, tmpList);
			}
		}
		return actorsMap;
	}
	
	public static Map<String,List<SimpleMovie>> initDirectorsMap(List<MovieModel> movieList){
		Map<String,List<SimpleMovie>> directorsMap = new HashMap<String,List<SimpleMovie>>();
		Iterator it = movieList.iterator();
		while(it.hasNext()){
			MovieModel m = (MovieModel) it.next();
			String[] directors = m.getDirectors();
			SimpleMovie sm = new SimpleMovie();
			sm.setTitle(m.getTitle());
			sm.setPoint(m.getPoint());
			sm.setRatingNum(m.getPoint_num());
			for(String director:directors){
				List<SimpleMovie> tmpList = new ArrayList<SimpleMovie>();
				if(directorsMap.containsKey(director)){
					tmpList = directorsMap.get(director);
				}
				tmpList.add(sm);
				directorsMap.put(director, tmpList);
			}
		}
		return directorsMap;
	}
}
