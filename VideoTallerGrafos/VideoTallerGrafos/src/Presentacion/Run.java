package Presentacion;

import java.util.List;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Modelo.Station;
import Modelo.Route;;

public class Run {

	public static void main(String[] args) {

		Graph<Station, Route> grafo = new SparseGraph<Station, Route>();

		// Agregar estaciones al grafo
		Station a = new Station("A");
		Station b = new Station("B");
		Station c = new Station("C");
		Station d = new Station("D");
		grafo.addVertex(a);
		grafo.addVertex(b);
		grafo.addVertex(c);
		grafo.addVertex(d);

		// Agregar rutas al grafo
		Route ab = new Route(a, b, 10);
		Route ac = new Route(a, c, 15);
		Route bc = new Route(b, c, 5);
		Route cd = new Route(c, d, 12);
		Route bd = new Route(b, d, 8);
		grafo.addEdge(ab, a, b, EdgeType.DIRECTED);
		grafo.addEdge(ac, a, c, EdgeType.DIRECTED);
		grafo.addEdge(bc, b, c, EdgeType.DIRECTED);
		grafo.addEdge(cd, c, d, EdgeType.DIRECTED);
		grafo.addEdge(bd, b, d, EdgeType.DIRECTED);
		// Graph<Station, Route> grafo = new SparseGraph<Station, Route>();

		// Agregar estaciones y rutas al grafo
		// ...

		// Imprimir el grafo
		System.out.println("Grafo:");
		System.out.println(grafo);

		// Encontrar el camino más corto desde A hasta D usando el algoritmo de Dijkstra
		DijkstraShortestPath<Station, Route> dijkstra = new DijkstraShortestPath<Station, Route>(grafo,
				Route.getWeightTransformer());
		List<Route> camino = dijkstra.getPath(a, d);

		// Imprimir el camino más corto
		System.out.println("Camino más corto desde A hasta D:");
		System.out.println(camino);
	}

}
