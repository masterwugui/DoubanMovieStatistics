package cetc.software.lujunzizi.data.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6275366043931887192L;

	double point;
	int point_num;
	double star5;
	double star4;
	double star3;
	double star2;
	double star1;
	String title;
	String director;
	String screenWriter;
	String actors;
	String types;
	String country;
	String language;
	String date;
	String length;
	String imdb;
	String target_id;
	String country_updated;
	String date_new;
	@Column(name = "date_new")
	public String getDate_new() {
		return date_new;
	}
	public void setDate_new(String date_new) {
		this.date_new = date_new;
	}
	@Column(name = "country_updated")
	public String getCountry_updated() {
		return country_updated;
	}
	public void setCountry_updated(String country_updated) {
		this.country_updated = country_updated;
	}
	@Column(name = "point")
	public double getPoint() {
		return point;
	}
	public void setPoint(double point) {
		this.point = point;
	}
	@Column(name = "point_num")
	public int getPoint_num() {
		return point_num;
	}
	public void setPoint_num(int point_num) {
		this.point_num = point_num;
	}
	@Column(name = "5Star")
	public double getStar5() {
		return star5;
	}
	public void setStar5(double star5) {
		this.star5 = star5;
	}
	@Column(name = "4Star")
	public double getStar4() {
		return star4;
	}
	public void setStar4(double star4) {
		this.star4 = star4;
	}
	@Column(name = "3Star")
	public double getStar3() {
		return star3;
	}
	public void setStar3(double star3) {
		this.star3 = star3;
	}
	@Column(name = "2Star")
	public double getStar2() {
		return star2;
	}
	public void setStar2(double star2) {
		this.star2 = star2;
	}
	@Column(name = "1Star")
	public double getStar1() {
		return star1;
	}
	public void setStar1(double star1) {
		this.star1 = star1;
	}
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "director")
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	@Column(name = "screenWriter")
	public String getScreenWriter() {
		return screenWriter;
	}
	public void setScreenWriter(String screenWriter) {
		this.screenWriter = screenWriter;
	}
	@Column(name = "actors")
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	@Column(name = "types")
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	@Column(name = "country")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Column(name = "language")
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	@Column(name = "date")
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Column(name = "length")
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	
	@Column(name = "imdb")
	public String getImdb() {
		return imdb;
	}
	public void setImdb(String imdb) {
		this.imdb = imdb;
	}
	
	@Id
	@Column(name = "target_id", unique = true, nullable = false)
	public String getTarget_id() {
		return target_id;
	}
	public void setTarget_id(String target_id) {
		this.target_id = target_id;
	}
}
