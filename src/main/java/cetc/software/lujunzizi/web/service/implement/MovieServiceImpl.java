package cetc.software.lujunzizi.web.service.implement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import cetc.software.lujunzizi.data.dao.MovieDao;
import cetc.software.lujunzizi.data.dataobject.Movie;
import cetc.software.lujunzizi.util.FileOperateUtil;
import cetc.software.lujunzizi.util.MovieUtil;
import cetc.software.lujunzizi.util.StringUtil;
import cetc.software.lujunzizi.web.service.MovieService;
import cetc.software.lujunzizi.web.service.convertor.MovieConvertor;
import cetc.software.lujunzizi.web.service.model.MovieJsonModel;
import cetc.software.lujunzizi.web.service.model.MovieModel;
import cetc.software.lujunzizi.web.service.model.PointModel;
import cetc.software.lujunzizi.web.service.model.RatingModel;
import cetc.software.lujunzizi.web.service.model.SimpleMovie;

@Component("movieService")
public class MovieServiceImpl implements MovieService {
	private MovieDao movieDao;

	public MovieDao getMovieDao() {
		return movieDao;
	}

	@Resource(name = "movieDao")
	public void setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
	}

	@Override
	public String getAllMovie() {
		List<Movie> movieList = movieDao.getRecentMovie();

		// Iterator it = movieList.iterator();
		// while (it.hasNext()) {
		// Movie movie = (Movie) it.next();
		// String movie_countries = MovieUtil.convertCountryToCountryArr(movie
		// .getCountry());
		// int movie_year = MovieUtil.ConvertStringToYear(movie.getDate());
		// movieDao.updateMovieYear(movie_year, movie.getTarget_id());
		// if (!movieDao.updateMovie(movie_countries, movie.getTarget_id()))
		// System.out.println("target_id: " + movie.getTarget_id());

		// }
		List<MovieModel> movieModelList = MovieConvertor
				.doToModelList(movieList);
		MovieJsonModel[][] movieArray = MovieUtil
				.handleMovieList(movieModelList);
		Gson gson = new Gson();
		String jsonObject = gson.toJson(movieArray);
		System.out.println(jsonObject);
		return jsonObject;
	}

	@Override
	public void insertAllActors() {
		List<Movie> movieList = movieDao.getRecentMovie();
		List<MovieModel> movieModelList = MovieConvertor
				.doToModelList(movieList);
		Map<String, List<SimpleMovie>> smMap = MovieUtil
				.initActorsMap(movieModelList);
		Iterator it = smMap.keySet().iterator();
		while (it.hasNext()) {
			String actor = (String) it.next();
			List<SimpleMovie> smList = smMap.get(actor);
			String movieArr = "";
			String pointArr = "";
			String pointNumArr = "";
			int pointNumTotal = 0;
			double pointTotal = 0;
			double pointAvg = 0.0;
			for (SimpleMovie sm : smList) {
				movieArr += sm.getTitle() + "/";
				pointArr += sm.getPoint() + "/";
				pointNumArr += sm.getRatingNum() + "/";
				pointNumTotal += sm.getRatingNum();
				pointTotal += sm.getRatingNum() * sm.getPoint();
			}
			DecimalFormat df2 = new DecimalFormat("###.0");
			pointAvg = Double.parseDouble(df2
					.format(pointTotal / pointNumTotal));
			movieDao.insertActor(actor, movieArr, pointArr, pointNumArr,
					pointAvg, pointNumTotal);
		}
		System.out.println("job finished!");
	}

	@Override
	public void insertAllDirectors() {
		List<Movie> movieList = movieDao.getRecentMovie();
		List<MovieModel> movieModelList = MovieConvertor
				.doToModelList(movieList);
		Map<String, List<SimpleMovie>> smMap = MovieUtil
				.initDirectorsMap(movieModelList);
		Iterator it = smMap.keySet().iterator();
		while (it.hasNext()) {
			String director = (String) it.next();
			List<SimpleMovie> smList = smMap.get(director);
			String movieArr = "";
			String pointArr = "";
			String pointNumArr = "";
			int pointNumTotal = 0;
			double pointTotal = 0;
			double pointAvg = 0.0;
			for (SimpleMovie sm : smList) {
				movieArr += sm.getTitle() + "/";
				pointArr += sm.getPoint() + "/";
				pointNumArr += sm.getRatingNum() + "/";
				pointNumTotal += sm.getRatingNum();
				pointTotal += sm.getRatingNum() * sm.getPoint();
			}
			DecimalFormat df2 = new DecimalFormat("###.0");
			pointAvg = Double.parseDouble(df2
					.format(pointTotal / pointNumTotal));
			movieDao.insertDirector(director, movieArr, pointArr, pointNumArr,
					pointAvg, pointNumTotal);
		}
		System.out.println("job finished!");
	}

	@Override
	public void updateAllMovieDate() {
		List<Movie> movieList = movieDao.getRecentMovie();
		for (Movie movie : movieList) {
			String date = movie.getDate();
			if (StringUtil.isBlank(movie.getDate_new()) && date.length() >= 10
					&& date.charAt(7) == '-' && date.charAt(4) == '-') {
				String newDate = date.substring(0, 10);
				movieDao.updateMovieDate(movie.getTarget_id(), newDate);
			}
		}
		System.out.println("job finished");
	}

	@Override
	public void handleMoviePoints() {
		List<Movie> movieList = movieDao.getAllMovieNew();
		for (Movie movie : movieList) {
			String actors = movie.getActors(), directors = movie.getDirector(), screens = movie
					.getScreenWriter(), target_id = movie.getTarget_id(), title = movie
					.getTitle(), date = movie.getDate_new();
			double point = movie.getPoint();
			int ratingNums = movie.getPoint_num();

			// director
			if (!StringUtil.isBlank(directors)) {
				String directorName = directors.split("/")[0];
				movieDao.insertDirectorNew(directorName, title, point,
						ratingNums, date, target_id);
			}

			// screen
			if (!StringUtil.isBlank(screens)) {
				String screenName = screens.split("/")[0];
				movieDao.insertScreenNew(screenName, title, point, ratingNums,
						date, target_id);
			}

			// actors
			if (!StringUtil.isBlank(actors)) {
				String[] actorsNames = actors.split("/");

				movieDao.insertActorNew(actorsNames[0], title, point,
						ratingNums, date, target_id);

				if (actorsNames.length > 1)
					movieDao.insertActorNew(actorsNames[1], title, point,
							ratingNums, date, target_id);
			}
		}
		System.out.println("job finished");
	}

	@Override
	public void handleMovieForRF() {
		List<Movie> movieList = movieDao.getAllMovieNew();
		for (Movie movie : movieList) {
			String actors = movie.getActors(), directors = movie.getDirector(), screens = movie
					.getScreenWriter(), target_id = movie.getTarget_id(), title = movie
					.getTitle(), date = movie.getDate_new();
			double point = movie.getPoint(), director_point = 0, actor_point_1 = 0, actor_point_2 = 0, screen_point = 0;
			int ratingNums = movie.getPoint_num();

			// director
			if (!StringUtil.isBlank(directors)) {
				String directorName = directors.split("/")[0];
				List<PointModel> pointList = movieDao
						.getRecentMoviePointByDirector(directorName, date);
				int endIndex = pointList.size() > 5 ? 5 : pointList.size();
				double fenzi = 0, fenmu = 0;
				for (int i = 0; i < endIndex; i++) {
					PointModel p = pointList.get(i);
					fenzi += p.getPoint() * p.getPoint_num();
					fenmu += p.getPoint_num();
				}
				if (fenzi > 0)
					director_point = fenzi / fenmu;
			}

			// screen
			if (!StringUtil.isBlank(screens)) {
				String screenName = screens.split("/")[0];
				List<PointModel> pointList = movieDao
						.getRecentMoviePointByScreen(screenName, date);
				int endIndex = pointList.size() > 5 ? 5 : pointList.size();
				double fenzi = 0, fenmu = 0;
				for (int i = 0; i < endIndex; i++) {
					PointModel p = pointList.get(i);
					fenzi += p.getPoint() * p.getPoint_num();
					fenmu += p.getPoint_num();
				}
				if (fenzi > 0)
					screen_point = fenzi / fenmu;
			}

			// actors
			if (!StringUtil.isBlank(actors)) {
				String[] actorsNames = actors.split("/");
				String actorName_1 = actorsNames[0];
				List<PointModel> pointList1 = movieDao
						.getRecentMoviePointByActor(actorName_1, date);
				int endIndex1 = pointList1.size() > 5 ? 5 : pointList1.size();
				double fenzi1 = 0, fenmu1 = 0;
				for (int i = 0; i < endIndex1; i++) {
					PointModel p = pointList1.get(i);
					fenzi1 += p.getPoint() * p.getPoint_num();
					fenmu1 += p.getPoint_num();
				}
				if (fenzi1 > 0)
					actor_point_1 = fenzi1 / fenmu1;
				if (actorsNames.length > 1) {
					String actorName_2 = actorsNames[1];
					List<PointModel> pointList2 = movieDao
							.getRecentMoviePointByActor(actorName_2, date);
					int endIndex2 = pointList2.size() > 5 ? 5 : pointList2
							.size();
					double fenzi2 = 0, fenmu2 = 0;
					for (int i = 0; i < endIndex2; i++) {
						PointModel p = pointList2.get(i);
						fenzi2 += p.getPoint() * p.getPoint_num();
						fenmu2 += p.getPoint_num();
					}
					if (fenzi2 > 0)
						actor_point_2 = fenzi2 / fenmu2;
				}

			}
			movieDao.insertMoviePoint(title, target_id, director_point, point,
					actor_point_1, actor_point_2, screen_point, date);
		}
		System.out.println("job finished");

	}

	@Override
	public void saveTxt() {

		String trainingFileName = "traingData.txt";
		File trainFile = FileOperateUtil.creatFolder("", trainingFileName);
		String testFileName = "testData.txt";
		File testFile = FileOperateUtil.creatFolder("", testFileName);
		BufferedWriter bw = null;
		BufferedWriter bw1 = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(trainFile), "utf-8"));
			bw1 = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(testFile), "utf-8"));
			List<RatingModel> pointList = movieDao.getRatingsByRange(
					"2000-1-1", "2016-1-1");
			for (RatingModel model : pointList) {
				try {
					String txt = model.getPoint() + " "
							+ model.getActor_point() + " "
							+ model.getDirector_point() + " "
							+ model.getScreen_point() + " " + model.getTitle();
					bw.append(txt);
					bw.flush();
					bw.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			List<RatingModel> pointList1 = movieDao.getRatingsByRange(
					"2016-1-1", "2018-1-1");
			for (RatingModel model : pointList1) {
				try {
					String txt = model.getPoint() + " "
							+ model.getActor_point() + " "
							+ model.getDirector_point() + " "
							+ model.getScreen_point() + " " + model.getTitle();
					bw1.append(txt);
					bw1.flush();
					bw1.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw1.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
