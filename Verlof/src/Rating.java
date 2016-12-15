
public class Rating {
	
	private double rating;
	
	public Rating(double rating){
		this.rating = rating;
	}	
	
	public double increaseRating(double rating){
		if ((this.rating + rating) >= 0 && (this.rating + rating) <= 100){
			this.rating += rating;			
		}
		return this.rating;
	}
	
	public double decreaseRating(double rating){
		if ((this.rating + rating) >= 0 && (this.rating + rating) <= 100){
			this.rating -= rating;
		}
		return this.rating;
	}
	
	public double getRating(){
		return this.rating;
	}
	
	public double setRating(double rating){
		this.rating = rating;
		return this.rating;
	}
	
	
	
}
