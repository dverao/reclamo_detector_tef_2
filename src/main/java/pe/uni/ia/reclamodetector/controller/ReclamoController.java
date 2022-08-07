package pe.uni.ia.reclamodetector.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pe.uni.ia.reclamodetector.model.ReclamoDetectorParams;
import pe.uni.ia.reclamodetector.service.ISolutionMethod;
import pe.uni.ia.reclamodetector.service.SolutionBayesNet;
import pe.uni.ia.reclamodetector.service.SolutionJ48;
import pe.uni.ia.reclamodetector.service.SolutionRandomForest;
import pe.uni.ia.reclamodetector.service.SolutionSVM;

@Controller
public class ReclamoController {

	
	  final static Logger log = Logger.getLogger(ReclamoController.class);
	 ISolutionMethod iSolutionMethod = null;
	 
	
	@GetMapping("/reclamo2")
	public String listarProductos2(Model model) {
		model.addAttribute("reclamodetector", new ReclamoDetectorParams());
		return "reclamodetector2";
	}
	
	//Metodo para validar la autenticación del usuario en el Login
	@PostMapping("/evaluar")
	public String evaluarReclamo(@ModelAttribute("reclamodetector") ReclamoDetectorParams reclamo, Model model) throws IOException {
		System.out.println("Reclamo: "+reclamo);
		//String retorno=null;
	
		 String resultado = null;
		

       String directory = "C:/ia-dataset/";

       String fileModel = directory + "model/j48.model";

       String original = "evaluate/default-evaluate.arff";
       String uuid = "evaluate/evaluate.arff";

       switch (reclamo.getMethod()) {
           case "J48":
               log.info("METHOD J48");
               iSolutionMethod = new SolutionJ48();
               fileModel = directory + "model/j48.model";
               break;
           case "BAYES":
               log.info("METHOD BAYES");
               iSolutionMethod = new SolutionBayesNet();
               fileModel = directory + "model/bayes.model";
               break;
           case "RANDOM":
               log.info("METHOD RANDOM");
               iSolutionMethod = new SolutionRandomForest();
               fileModel = directory + "model/random-forest.model";
               break;
           case "SVM":
               log.info("SVM");
               iSolutionMethod = new SolutionSVM();
               fileModel = directory + "model/svm.model";
               break;
       }

	        String parameterNameFileTraining = directory + uuid;
	        Files.copy(Paths.get(directory + original), Paths.get(parameterNameFileTraining), StandardCopyOption.REPLACE_EXISTING);
	        FileWriter myWriter = new FileWriter(parameterNameFileTraining, true);
	        myWriter.write(reclamo.toString());
	        myWriter.close();

	        resultado = iSolutionMethod.evaluate(parameterNameFileTraining, fileModel);
		
		
		if(resultado=="NO") {
			model.addAttribute("mensajeNO", "Es POCO PROBABLE que el cliente genere un RECLAMO.");
			return "reclamodetector2";
		}else {
			model.addAttribute("mensajeSI", "Es MUY PROBABLE que el cliente genere un RECLAMO.");
			return "reclamodetector2";
		}
		
		//return retorno;
		//return "reclamodetector2";
	}
}
