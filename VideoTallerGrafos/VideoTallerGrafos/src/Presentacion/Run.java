package Presentacion;

import java.util.List;

import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.*;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import Modelo.Station;

import Modelo.Route;;

public class Run {

	public static void main(String[] args) {

		Graph<Station, Route> grafo = new SparseMultigraph<Station, Route>();

		Station stationA = new Station("Bogota");
		Station stationB = new Station("Medellin");
		Station stationC = new Station("Cali");
		Station stationD = new Station("Barranquilla");
		Station stationE = new Station("Cartagena");
		Station stationF = new Station("Bucaramanga");
		Station stationG = new Station("Pereira");
		Station stationH = new Station("Manizales");
		Station stationI = new Station("Armenia");
		Station stationJ = new Station("Ibague");

		grafo.addVertex(stationA);
		grafo.addVertex(stationB);
		grafo.addVertex(stationC);
		grafo.addVertex(stationD);
		grafo.addVertex(stationE);
		grafo.addVertex(stationF);
		grafo.addVertex(stationG);
		grafo.addVertex(stationH);
		grafo.addVertex(stationI);
		grafo.addVertex(stationJ);

		Route bogotaMedellin = new Route(stationA, stationB, 506.5);
		Route bogotaCali = new Route(stationA, stationC, 465.7);
		Route bogotaBarranquilla = new Route(stationA, stationD, 1000);
		Route barranquillaCartagena = new Route(stationD, stationE, 134.3);
		Route medellinPereira = new Route(stationB, stationG, 212.14);
		Route medellinManizales = new Route(stationB, stationH, 195.7);
		Route manizalesArmenia = new Route(stationH, stationI, 97.1);
		Route armeniaPereira = new Route(stationI, stationG, 45.4);
		Route caliPereira = new Route(stationC, stationG, 209.3);
		Route caliBucaramanga = new Route(stationC, stationF, 764.6);
		Route bucaramangaBogota = new Route(stationF, stationA, 424.2);
		Route bucaramangaMedellin = new Route(stationF, stationB, 381.9);
		Route pereiraIbague = new Route(stationG, stationJ, 115.4);
		Route ibagueBogota = new Route(stationJ, stationA, 211.1);

		grafo.addEdge(bogotaMedellin, stationA, stationB, EdgeType.UNDIRECTED);
		grafo.addEdge(bogotaCali, stationA, stationC, EdgeType.UNDIRECTED);
		grafo.addEdge(bogotaBarranquilla, stationA, stationD, EdgeType.UNDIRECTED);
		grafo.addEdge(barranquillaCartagena, stationD, stationE, EdgeType.UNDIRECTED);
		grafo.addEdge(medellinPereira, stationB, stationG, EdgeType.UNDIRECTED);
		grafo.addEdge(medellinManizales, stationB, stationH, EdgeType.UNDIRECTED);
		grafo.addEdge(manizalesArmenia, stationH, stationI, EdgeType.UNDIRECTED);
		grafo.addEdge(armeniaPereira, stationI, stationG, EdgeType.UNDIRECTED);
		grafo.addEdge(caliPereira, stationC, stationG, EdgeType.UNDIRECTED);
		grafo.addEdge(caliBucaramanga, stationC, stationF, EdgeType.UNDIRECTED);
		grafo.addEdge(bucaramangaBogota, stationF, stationA, EdgeType.UNDIRECTED);
		grafo.addEdge(bucaramangaMedellin, stationF, stationB, EdgeType.UNDIRECTED);
		grafo.addEdge(pereiraIbague, stationG, stationJ, EdgeType.UNDIRECTED);
		grafo.addEdge(ibagueBogota, stationJ, stationA, EdgeType.UNDIRECTED);

		DijkstraShortestPath<Station, Route> dijkstra = new DijkstraShortestPath<Station, Route>(grafo);

		VisualizationViewer<Station, Route> PantallaGrafo = new VisualizationViewer<Station, Route>(
				new KKLayout<Station, Route>(grafo), new Dimension(600, 500));
		PantallaGrafo.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Station>());
		PantallaGrafo.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);

		JFrame frame = new JFrame("Principales Rutas en Colombia");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(PantallaGrafo);

		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Buscar ruta"));
		JTextField ciudadInicial = new JTextField(10);
		JTextField ciudadFinal = new JTextField(10);
		JButton buscarBtn = new JButton("Buscar");
		panel.add(new JLabel("Desde: "));
		panel.add(ciudadInicial);
		panel.add(new JLabel("Hasta: "));
		panel.add(ciudadFinal);
		panel.add(buscarBtn);
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		JPanel infoPanel = new JPanel(new FlowLayout());
		infoPanel.setBorder(BorderFactory.createTitledBorder("Información de la ruta"));
		JLabel distanciaLabel = new JLabel();
		infoPanel.add(distanciaLabel);
		frame.getContentPane().add(infoPanel, BorderLayout.SOUTH);

		buscarBtn.addActionListener((ActionEvent) -> {
			Station estacionInicial = null;
			for (Station nodo : grafo.getVertices()) {
				if (ciudadInicial.getText().equalsIgnoreCase(nodo.getName())) {
					estacionInicial = nodo;
					break;
				}
			}
			Station estacionFinal = null;
			for (Station nodo : grafo.getVertices()) {
				if (ciudadFinal.getText().equalsIgnoreCase(nodo.getName())) {
					estacionFinal = nodo;
					break;
				}
			}

			double distanciaRecorrida = 0.0;
			try {
				for (Route v : dijkstra.getPath(estacionInicial, estacionFinal)) {
					distanciaRecorrida += Double.valueOf(v.getWeight());
				}
				distanciaLabel.setText("La ruta más corta desde " + ciudadInicial.getText() + " hasta "
						+ ciudadFinal.getText() + " es de " + distanciaRecorrida + " Km.");
			} catch (Exception ex) {
				distanciaLabel.setText("No se pudo encontrar una ruta válida.");
			}

		});

		frame.pack();
		frame.setVisible(true);
	}

}
