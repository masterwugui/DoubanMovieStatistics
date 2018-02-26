package cetc.software.lujunzizi.data.dao.implement;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import cetc.software.lujunzizi.data.dao.MovieDao;
import cetc.software.lujunzizi.data.dataobject.Movie;
import cetc.software.lujunzizi.web.service.model.PointModel;
import cetc.software.lujunzizi.web.service.model.RatingModel;

@Component("movieDao")
public class MovieDaoHibernate extends BaseHibernateDAO implements MovieDao {

	@Override
	public List<Movie> getAllMovie() {
		String sql = "SELECT * FROM movie_new where date REGEXP '^[[:digit:]]{4}' ";
		Query query = getMySession().createSQLQuery(sql).addEntity(Movie.class);
		List<Movie> movieList = query.list();
		return movieList;
	}

	@Override
	public boolean updateMovie(String country, String target_id) {
		String sql = "update movie_new set country_updated = ? where target_id = ?";
		Query query = getMySession().createSQLQuery(sql);
		query.setString(0, country);
		query.setString(1, target_id);
		return query.executeUpdate() == 1;
	}

	@Override
	public boolean updateMovieYear(int year, String target_id) {
		String sql = "update movie_new set year = ? where target_id = ?";
		Query query = getMySession().createSQLQuery(sql);
		query.setInteger(0, year);
		query.setString(1, target_id);
		return query.executeUpdate() == 1;
	}

	@Override
	public List<Movie> getRecentMovie() {
		String sql = "SELECT * FROM movie_new where date > 1980 and point > 0";
		Query query = getMySession().createSQLQuery(sql).addEntity(Movie.class);
		List<Movie> movieList = query.list();
		return movieList;
	}

	@Override
	public List<Movie> getAllMovieNew() {
		String sql = "SELECT * FROM movie_new where date_new != ''";
		Query query = getMySession().createSQLQuery(sql).addEntity(Movie.class);
		List<Movie> movieList = query.list();
		return movieList;
	}

	@Override
	public boolean insertActor(String actorName, String titleArr,
			String pointArr, String pointNumArr, double pointAvg,
			int ratingNumAll) {
		String sql = "insert into movie_actor(name,movie,movie_point,movie_rating_num,"
				+ "movie_rating_avg,movie_rating_num_total) values (?,?,?,?,?,?)";
		Query query = getMySession().createSQLQuery(sql);
		query.setString(0, actorName);
		query.setString(1, titleArr);
		query.setString(2, pointArr);
		query.setString(3, pointNumArr);
		query.setDouble(4, pointAvg);
		query.setInteger(5, ratingNumAll);
		return query.executeUpdate() == 1;
	}

	@Override
	public boolean insertDirector(String directorName, String titleArr,
			String pointArr, String pointNumArr, double pointAvg,
			int ratingNumAll) {
		String sql = "insert into movie_director(name,movie,movie_point,movie_rating_num,"
				+ "movie_rating_avg,movie_rating_num_total) values (?,?,?,?,?,?)";
		Query query = getMySession().createSQLQuery(sql);
		query.setString(0, directorName);
		query.setString(1, titleArr);
		query.setString(2, pointArr);
		query.setString(3, pointNumArr);
		query.setDouble(4, pointAvg);
		query.setInteger(5, ratingNumAll);
		return query.executeUpdate() == 1;
	}

	@Override
	public boolean updateMovieDate(String movieId, String date) {
		String sql = "update movie_new set date_new = ? where target_id = ?";
		Query query = getMySession().createSQLQuery(sql);
		query.setString(0, date);
		query.setString(1, movieId);
		return query.executeUpdate() == 1;
	}

	@Override
	public boolean insertActorNew(String actorName, String title, Double point,
			int ratingNums, String date, String target_id) {
		String sql = "insert into movie_actor_new(target_id,title,point,date,"
				+ "actor_name,point_num) values (?,?,?,?,?,?)";
		Query query = getMySession().createSQLQuery(sql);
		query.setString(0, target_id);
		query.setString(1, title);
		query.setDouble(2, point);
		query.setString(3, date);
		query.setString(4, actorName);
		query.setInteger(5, ratingNums);
		return query.executeUpdate() == 1;
	}

	@Override
	public boolean insertDirectorNew(String actorName, String title,
			Double point, int ratingNums, String date, String target_id) {
		String sql = "insert into movie_director_new(target_id,title,point,date,"
				+ "director_name,point_num) values (?,?,?,?,?,?)";
		Query query = getMySession().createSQLQuery(sql);
		query.setString(0, target_id);
		query.setString(1, title);
		query.setDouble(2, point);
		query.setString(3, date);
		query.setString(4, actorName);
		query.setInteger(5, ratingNums);
		return query.executeUpdate() == 1;
	}

	@Override
	public boolean insertScreenNew(String actorName, String title,
			Double point, int ratingNums, String date, String target_id) {
		String sql = "insert into movie_screen_new(target_id,title,point,date,"
				+ "screen_name,point_num) values (?,?,?,?,?,?)";
		Query query = getMySession().createSQLQuery(sql);
		query.setString(0, target_id);
		query.setString(1, title);
		query.setDouble(2, point);
		query.setString(3, date);
		query.setString(4, actorName);
		query.setInteger(5, ratingNums);
		return query.executeUpdate() == 1;
	}

	@Override
	public boolean insertMoviePoint(String title, String target_id,
			double director_point, Double point, double actor_point_1,
			double actor_point_2, double screen_point, String date) {
		String sql = "insert into movie_point(title,target_id,point,date,"
				+ "director_point,screen_point,actor_point_1,actor_point_2) values (?,?,?,?,?,?,?,?)";
		Query query = getMySession().createSQLQuery(sql);
		query.setString(0, title);
		query.setString(1, target_id);
		query.setDouble(2, point);
		query.setString(3, date);
		query.setDouble(4, director_point);
		query.setDouble(5, screen_point);
		query.setDouble(6, actor_point_1);
		query.setDouble(7, actor_point_2);
		return query.executeUpdate() == 1;
	}

	@Override
	public List<PointModel> getRecentMoviePointByActor(String actor_name,
			String date) {
		List<PointModel> movieList = new ArrayList<PointModel>();
		String sql = "SELECT point, point_num FROM movie_actor_new where actor_name = ? && date < ? order by date desc";
		Query query = getMySession().createSQLQuery(sql);
		query.setString(0, actor_name);
		query.setString(1, date);
		if (query.list() != null) {
			List<Object[]> reList = query.list();
			for (Object[] oa : reList) {
				double point = (double) oa[0];
				int point_num = (int) oa[1];
				PointModel model = new PointModel();
				model.setPoint(point);
				model.setPoint_num(point_num);
				movieList.add(model);
			}
		}
		return movieList;
	}

	@Override
	public List<PointModel> getRecentMoviePointByDirector(String director_name,
			String date) {
		List<PointModel> movieList = new ArrayList<PointModel>();
		String sql = "SELECT point, point_num FROM movie_director_new where director_name = ? && date < ? order by date desc";
		Query query = getMySession().createSQLQuery(sql);
		query.setString(0, director_name);
		query.setString(1, date);
		if (query.list() != null) {
			List<Object[]> reList = query.list();
			for (Object[] oa : reList) {
				double point = (double) oa[0];
				int point_num = (int) oa[1];
				PointModel model = new PointModel();
				model.setPoint(point);
				model.setPoint_num(point_num);
				movieList.add(model);
			}
		}
		return movieList;
	}

	@Override
	public List<PointModel> getRecentMoviePointByScreen(String screen_name,
			String date) {
		List<PointModel> movieList = new ArrayList<PointModel>();
		String sql = "SELECT point, point_num FROM movie_screen_new where screen_name = ? && date < ? order by date desc";
		Query query = getMySession().createSQLQuery(sql);
		query.setString(0, screen_name);
		query.setString(1, date);
		if (query.list() != null) {
			List<Object[]> reList = query.list();
			for (Object[] oa : reList) {
				double point = (double) oa[0];
				int point_num = (int) oa[1];
				PointModel model = new PointModel();
				model.setPoint(point);
				model.setPoint_num(point_num);
				movieList.add(model);
			}
		}
		return movieList;
	}

	@Override
	public List<RatingModel> getRatingsByRange(String beginDate, String endDate) {
		List<RatingModel> pointList = new ArrayList<RatingModel>();
		String sql = "SELECT point, director_point, screen_point, actor_point_1,actor_point_2,"
				+ " title FROM douban.movie_point where director_point > 0"
				+ " and screen_point > 0 and actor_point_1 > 0 and actor_point_2"
				+ " >0 and date > ? and date < ?";
		Query query = getMySession().createSQLQuery(sql);
		query.setString(0, beginDate);
		query.setString(1, endDate);
		if (query.list() != null) {
			List<Object[]> reList = query.list();
			for (Object[] oa : reList) {
				double point = (double) oa[0];
				double director_point = (double) oa[1];
				double screen_point = (double) oa[2];
				double actor_point = ((double) oa[3] + (double) oa[4]) / 2;
				RatingModel model = new RatingModel();
				model.setPoint(point);
				model.setDirector_point(director_point);
				model.setScreen_point(screen_point);
				model.setActor_point(actor_point);
				model.setTitle((String) oa[5]);
				pointList.add(model);
				
			}
		}
		return pointList;
	}

}
