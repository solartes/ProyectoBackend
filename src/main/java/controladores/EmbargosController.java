package controladores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modelo.Demandado;
import modelo.Embargo;
import util.AnnotationExclusionStrategy;

public class EmbargosController {
	public static boolean guardarEmbargo(Embargo embargo) {
		Gson gson = new GsonBuilder().setExclusionStrategies(new AnnotationExclusionStrategy()).create();
		String embargoJson=gson.toJson(embargo);
		System.out.println(embargoJson);
		return false;
		
	}

}
