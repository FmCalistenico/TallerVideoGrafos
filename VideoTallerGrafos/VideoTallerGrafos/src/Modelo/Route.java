package Modelo;

import org.apache.commons.collections15.Transformer;

//import org.apache.commons.collections15.Transformer;

public class Route {
	private Station source;
	private Station target;
	private int weight;

	public Route(Station source, Station target, int weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
	}

	public Station getSource() {
		return source;
	}

	public Station getTarget() {
		return target;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return source + " -> " + target + " (" + weight + ")";
	}

	public static Transformer<Route, Double> getWeightTransformer() {
		return new Transformer<Route, Double>() {
			public Double transform(Route edge) {
				return (double) edge.getWeight();
			}
		};
	}
}
