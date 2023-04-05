package Modelo;

public class Route {
	private Station source;
	private Station target;
	private double weight;

	public Route(Station source, Station target, double weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
	}

	public Station getSource() {
		return source;
	}

	public void setSource(Station source) {
		this.source = source;
	}

	public Station getTarget() {
		return target;
	}

	public void setTarget(Station target) {
		this.target = target;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}


}
