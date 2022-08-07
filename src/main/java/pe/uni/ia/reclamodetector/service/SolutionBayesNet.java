package pe.uni.ia.reclamodetector.service;

import org.apache.log4j.Logger;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class SolutionBayesNet implements ISolutionMethod {

	final static Logger log = Logger.getLogger(SolutionBayesNet.class);
	
    @Override
    public String evaluate(String parameterNameFileTraining,
                        String parameterNameFileModel) {
        try {
            log.info("Stage01MassiveEvaluationBayesNet::executeEvaluationBayesNet::INICIO");

            log.info("Stage01MassiveEvaluationBayesNet::executeEvaluationBayesNet::nameFileModel = [" + parameterNameFileModel + "]");
            log.info("Stage01MassiveEvaluationBayesNet::executeEvaluationBayesNet::nameFileTraining = [" + parameterNameFileTraining + "]");

            BayesNet objectAlgorithm = new BayesNet();
            objectAlgorithm = (BayesNet)weka.core.SerializationHelper.read(parameterNameFileModel);

            log.info("Stage01MassiveEvaluationBayesNet::executeEvaluationBayesNet::objectAlgorithm.getBatchSize = " + objectAlgorithm.getBatchSize());

            DataSource objectDataSource = new DataSource(parameterNameFileTraining);
            Instances instancesOrigin = objectDataSource.getDataSet();
            instancesOrigin.setClassIndex(9);

            log.info("Stage01MassiveEvaluationBayesNet::executeEvaluationBayesNet::instancesOrigin.size() = [" + instancesOrigin.size() + "]");
            log.info("Stage01MassiveEvaluationBayesNet::executeEvaluationBayesNet::instancesOrigin.numAttributes() = [" + instancesOrigin.numAttributes() + "]");

            String[] optionsAttribute = new String[2];
            optionsAttribute[0] = "-R";
            optionsAttribute[1] = "1";

            log.info("Stage01MassiveEvaluationBayesNet::executeEvaluationBayesNet::optionsAttribute[0] = [" + optionsAttribute[0] + "]");
            log.info("Stage01MassiveEvaluationBayesNet::executeEvaluationBayesNet::optionsAttribute[1] = [" + optionsAttribute[1] + "]");

            Remove remove = new Remove();
            remove.setOptions(optionsAttribute);
            remove.setInputFormat(instancesOrigin);

            Instances instancesFilter = Filter.useFilter(instancesOrigin, remove);
            instancesFilter.setClassIndex(8);

            log.info("Stage01MassiveEvaluationBayesNet::executeEvaluationBayesNet::instancesFilter.size() = [" + instancesFilter.size() + "]");
            log.info("Stage01MassiveEvaluationBayesNet::executeEvaluationBayesNet::instancesFilter.numAttributes() = [" + instancesFilter.numAttributes() + "]");

            String[] optionsAlgorithm = new String[4];
            optionsAlgorithm[0] = "-C";
            optionsAlgorithm[1] = "0.25";
            optionsAlgorithm[2] = "-M";
            optionsAlgorithm[3] = "2";

            for (int i = 0; i < optionsAlgorithm.length; i ++) {
                log.info("Stage01MassiveEvaluationBayesNet::executeEvaluationBayesNet::optionsAlgorithm[" + i + "] = [" + optionsAlgorithm[i] + "]");
            }

            Evaluation evaluationTest = new Evaluation(instancesFilter);

            double[] evaluationValue = evaluationTest.evaluateModel(objectAlgorithm, instancesFilter);

            log.info("Stage01MassiveEvaluationBayesNet::executeEvaluationBayesNet::evaluationValue.length = [" + evaluationValue.length + "]");

            //String actual = "?";
            double prediction = 0.0;

            for (int i = 0; i < instancesFilter.numInstances(); i++) {
                //actual = instancesFilter.instance(i).toString(15);
                prediction = Double.valueOf(instancesFilter.instance(i).classAttribute().value((int) evaluationValue[i])).doubleValue();

                //log.info("Stage01MassiveEvaluationBayesNet::executeEvaluationBayesNet::instancia:: número = " + (i+1) +
               //         "; actual = " + actual + "; prediction = " + prediction);
                log.info("Stage01MassiveEvaluationBayesNet::executeEvaluationBayesNet::instancia:: número = " + (i+1) + "; prediction = " + prediction);

                return prediction == 1.0 ? "SI" : "NO";
            }

            log.info("Stage01MassiveEvaluationBayesNet::executeEvaluationBayesNet::FIN");

        } catch(Exception e) {
            log.info("Stage01MassiveEvaluationBayesNet::executeEvaluationBayesNet::Exception" + e.getMessage());
        }

        return "NO";
    }
}
