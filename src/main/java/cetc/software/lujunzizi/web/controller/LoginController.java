package cetc.software.lujunzizi.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cetc.software.lujunzizi.data.dataobject.Movie;
import cetc.software.lujunzizi.web.service.MovieService;
import cetc.software.lujunzizi.web.service.XtglyhbService;

@Controller
public class LoginController implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 5934993450538015681L;
	/**
	 *  
	 */
	private MovieService movieService;
	
	public MovieService getMovieService() {
		return movieService;
	}
	
	@Resource(name = "movieService")
	public void setMovieService(MovieService movieService) {
		this.movieService = movieService;
	}

	@RequestMapping(value = "index.do")
	public ModelAndView showLogin(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		ModelAndView mav = new ModelAndView("welcome");
		return mav;
	}
	
	@RequestMapping(value = "index1.do")
	public ModelAndView showDashBoard(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		ModelAndView mav = new ModelAndView("dashboard");
		return mav;
	}
	
	@RequestMapping(value = "getAllMovieData.json")
	public ModelAndView getAllMovieData(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		ModelAndView mav = new ModelAndView();
		String arr = movieService.getAllMovie();
		mav.addObject("arr",arr);
		return mav;
	}
	
	@RequestMapping(value = "insertActors.json")
	public void insertActors(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		//movieService.insertAllActors();
		//movieService.insertAllDirectors();
		//movieService.updateAllMovieDate();
		//movieService.handleMoviePoints();
		//movieService.handleMovieForRF();
		movieService.saveTxt();
	}
	
}
