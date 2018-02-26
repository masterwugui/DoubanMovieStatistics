package cetc.software.lujunzizi.data.dao;

import java.util.List;

import cetc.software.lujunzizi.data.dataobject.Movie;
import cetc.software.lujunzizi.web.service.model.PointModel;
import cetc.software.lujunzizi.web.service.model.RatingModel;

public interface MovieDao {

	List<Movie> getAllMovie();

	List<Movie> getRecentMovie();

	List<Movie> getAllMovieNew();
	
	List<PointModel> getRecentMoviePointByActor(String actor_name, String date);
	
	List<PointModel> getRecentMoviePointByDirector(String director_name, String date);
	
	List<PointModel> getRecentMoviePointByScreen(String screen_name, String date);
	
	boolean updateMovie(String country, String target_id);

	boolean updateMovieYear(int year, String target_id);

	boolean insertActor(String actorName, String titleArr, String pointArr,
			String pointNumArr, double pointAvg, int ratingNumAll);
	
	boolean insertDirector(String directorName, String titleArr, String pointArr,
			String pointNumArr, double pointAvg, int ratingNumAll);
	
	boolean updateMovieDate(String movieId, String date);
	
	boolean insertActorNew(String actorName, String title, Double point,
			int ratingNums, String date, String target_id);
	
	boolean insertScreenNew(String actorName, String title, Double point,
			int ratingNums, String date, String target_id);
	
	boolean insertDirectorNew(String actorName, String title, Double point,
			int ratingNums, String date, String target_id);
	
	boolean insertMoviePoint(String title, String target_id, double director_point, Double point,
			double actor_point_1, double actor_point_2, double screen_point, String date);

	List<RatingModel> getRatingsByRange(String beginDate, String endDate);
}
