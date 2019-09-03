package it.polito.tdp.newufosightings.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.newufosightings.db.NewUfoSightingsDAO;

public class Model {
	
	private NewUfoSightingsDAO dao;
	private Map<String,State> idMap;
	private List<Vicini> neighbors;
	private SimpleWeightedGraph <State,DefaultWeightedEdge>grafo;	
		
	public Model() {
		 dao = new NewUfoSightingsDAO();
		 idMap = new HashMap<>();
	}

	public List<String> addForme(int anno) {
		return	dao.getForme(anno);
	
	}

	public void creaGrafo(int anno, String forma) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, dao.loadAllStates(idMap));
		
		
			neighbors =dao.addArchi(anno,forma,idMap); 		// OPZIONE 3
			
			for(Vicini s1 : neighbors) {
				if(this.grafo.containsVertex(idMap.get(s1.getState1())) && this.grafo.containsVertex(idMap.get(s1.getState2())));	
				DefaultWeightedEdge edge = grafo.getEdge(idMap.get(s1.getState1()),idMap.get(s1.getState2())); 			
				if(edge==null) {
					Graphs.addEdge(grafo,idMap.get(s1.getState1()),idMap.get(s1.getState2()),s1.getPeso()); 
		}
	}
		System.out.println("Grafo creato, vertici: "+grafo.vertexSet().size());
		System.out.println("Grafo creato, archi: "+grafo.edgeSet().size());
	}

		public Map<State,Integer> getVicini( )  {
			
		Map<State,Integer> corr = new HashMap<>();
				
				for(State s : this.grafo.vertexSet()) {
				List<State> vicini = Graphs.neighborListOf(grafo, s);
				for(State s1 : vicini) {
					DefaultWeightedEdge edge = grafo.getEdge(s,s1);
					if(corr.get(s1)!=null) {
					int peso = corr.get(s1);
					peso+=grafo.getEdgeWeight(edge);
					corr.put(s,peso );
				}else {
					corr.put(s,(int) grafo.getEdgeWeight(edge));
				}
				}
				
				}
				return corr;
		}		
}
