package Presentacion;

import java.util.List;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.*;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import Modelo.Station;

import Modelo.Route;;

public class Run {

	public static void main(String[] args) {

		Graph<Station, Route> grafo = new SparseGraph<Station, Route>();
		// Crear estaciones
		Station stationA = new Station("A");
		Station stationB = new Station("B");
		Station stationC = new Station("C");
		Station stationD = new Station("D");
		Station stationE = new Station("E");
		Station stationF = new Station("F");
		Station stationG = new Station("G");
		Station stationH = new Station("H");

		// Añadir estaciones al grafo
		grafo.addVertex(stationA);
		grafo.addVertex(stationB);
		grafo.addVertex(stationC);
		grafo.addVertex(stationD);
		grafo.addVertex(stationE);
		grafo.addVertex(stationF);
		grafo.addVertex(stationG);
		grafo.addVertex(stationH);

		// Crear rutas y añadirlas al grafo con su respectiva distancia en km
		Route ab = new Route(stationA, stationB, 2.9);
		Route ac = new Route(stationA, stationC, 3.1);
		Route bc = new Route(stationB, stationC, 1.6);
		Route bd = new Route(stationB, stationD, 2.2);
		Route da = new Route(stationD, stationA, 2.2);
		Route de = new Route(stationD, stationE, 1.8);
		Route ef = new Route(stationE, stationF, 1.4);
		Route cf = new Route(stationC, stationF, 2.7);
		Route gh = new Route(stationG, stationH, 3.9);
		Route ae = new Route(stationA, stationE, 4.5);
		Route bh = new Route(stationB, stationH, 5.1);
		Route cg = new Route(stationC, stationG, 2.2);
		Route df = new Route(stationD, stationF, 2.5);

		grafo.addEdge(ab, stationA, stationB, EdgeType.UNDIRECTED);
		grafo.addEdge(ac, stationA, stationC, EdgeType.UNDIRECTED);
		grafo.addEdge(bc, stationB, stationC, EdgeType.UNDIRECTED);
		grafo.addEdge(bd, stationB, stationD, EdgeType.DIRECTED);
		grafo.addEdge(da, stationD, stationA, EdgeType.DIRECTED);
		grafo.addEdge(de, stationD, stationE, EdgeType.DIRECTED);
		grafo.addEdge(ef, stationE, stationF, EdgeType.DIRECTED);
		grafo.addEdge(cf, stationC, stationF, EdgeType.UNDIRECTED);
		grafo.addEdge(gh, stationG, stationH, EdgeType.DIRECTED);
		grafo.addEdge(ae, stationA, stationE, EdgeType.DIRECTED);
		grafo.addEdge(bh, stationB, stationH, EdgeType.DIRECTED);
		grafo.addEdge(cg, stationC, stationG, EdgeType.DIRECTED);
		grafo.addEdge(df, stationD, stationF, EdgeType.DIRECTED);

		
		VisualizationViewer<Station, Route> PantallaGrafo = new VisualizationViewer<Station, Route>(
				new CircleLayout<Station, Route>(grafo), new Dimension(400, 400));

		PantallaGrafo.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Station>());
		PantallaGrafo.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);

		JFrame frame = new JFrame("Grafo Sobre estaciones de una ciudad imaginaria");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(PantallaGrafo);
		frame.pack();
		frame.setVisible(true);

		double distanciaRecorrida = 0.0;

		
		DijkstraShortestPath<Station, Route> dijkstra = new DijkstraShortestPath<Station, Route>(grafo);
		String ciudadInicial = JOptionPane.showInputDialog("Digite la ciudad desde donde quiere ir:");
		Station estacionInicial = null;
		for (Station nodo : grafo.getVertices()) {
			if (ciudadInicial.equalsIgnoreCase(nodo.getName())) {
				estacionInicial = nodo;
				break;
			}
		}
		String ciudadFinal = JOptionPane.showInputDialog("Digite la ciudad a la que quiere llegar:");
		Station estacionFinal = null;
		for (Station nodo : grafo.getVertices()) {
			if (ciudadFinal.equalsIgnoreCase(nodo.getName())) {
				estacionFinal = nodo;
				break;
			}
		}
		try {

			for (Route v : dijkstra.getPath(estacionInicial, estacionFinal)) {
				distanciaRecorrida += Double.valueOf(v.getWeight());

			}
			JOptionPane.showMessageDialog(null, "La ruta más corta desde " + ciudadInicial + " hasta " + ciudadFinal
					+ " son " + distanciaRecorrida + " Km");
		} catch (Exception e) {
		
			JOptionPane.showMessageDialog(null, "La ciudad ingresada no es valida");
		}
	}

}
