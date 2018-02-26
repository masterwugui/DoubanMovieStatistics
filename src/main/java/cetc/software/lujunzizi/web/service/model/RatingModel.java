package cetc.software.lujunzizi.web.service.model;

public class RatingModel {
	double point;
	String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	double director_point;
	double actor_point;
	double screen_point;

	public double getDirector_point() {
		return director_point;
	}

	public void setDirector_point(double director_point) {
		this.director_point = director_point;
	}

	public double getActor_point() {
		return actor_point;
	}

	public void setActor_point(double actor_point) {
		this.actor_point = actor_point;
	}

	public double getScreen_point() {
		return screen_point;
	}

	public void setScreen_point(double screen_point) {
		this.screen_point = screen_point;
	}

}
