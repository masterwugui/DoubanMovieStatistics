package cetc.software.lujunzizi.web.service.convertor;

import java.util.ArrayList;
import java.util.List;

import cetc.software.lujunzizi.data.dataobject.Movie;
import cetc.software.lujunzizi.util.MovieUtil;
import cetc.software.lujunzizi.web.service.model.MovieModel;

public class MovieConvertor {
	public static MovieModel doToModel(Movie movie) {
		MovieModel model = new MovieModel();
		model.setPoint(movie.getPoint());
		model.setPoint_num(movie.getPoint_num());
		model.setStar5(movie.getStar5());
		model.setStar4(movie.getStar4());
		model.setStar3(movie.getStar3());
		model.setStar2(movie.getStar2());
		model.setStar1(movie.getStar1());
		model.setTitle(movie.getTitle());
		model.setDirectors(MovieUtil.ConvertStringToStringArray(movie
				.getDirector()));
		model.setScreenWriters(MovieUtil.ConvertStringToStringArray(movie
				.getScreenWriter()));
		model.setActors(MovieUtil.ConvertStringToStringArray(movie.getActors()));
		model.setTypes(MovieUtil.ConvertStringToStringArray(movie.getTypes()));
		model.setCountries(movie.getCountry_updated().split("/"));
		model.setDate(movie.getDate());
		model.setYear(MovieUtil.ConvertStringToYear(model.getDate()));
		model.setLength(MovieUtil.ConvertStringToFilmLength(movie.getLength()));
		model.setImdb(movie.getImdb());
		model.setTarget_id(movie.getTarget_id());
		return model;
	}

	public static List<MovieModel> doToModelList(List<Movie> movieList) {
		List<MovieModel> MovieModelList = new ArrayList<MovieModel>();
		if (movieList != null) {
			for (Movie movie : movieList) {
				MovieModelList.add(doToModel(movie));

			}
		}
		return MovieModelList;
	}
}
