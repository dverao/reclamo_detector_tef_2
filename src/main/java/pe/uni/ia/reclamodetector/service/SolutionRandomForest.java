package pe.uni.ia.reclamodetector.service;

import org.apache.log4j.Logger;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;


public class SolutionRandomForest implements ISolutionMethod {
	final static Logger log = Logger.getLogger(SolutionBayesNet.class);
    @Override
    public String evaluate(String parameterNameFileTraining, String parameterNameFileModel) {
        try{
            log.info("Stage01MassiveEvaluationRandomForest::executeEvaluationRandomForest::INICIO");

            log.info("Stage01MassiveEvaluationRandomForest::executeEvaluationRandomForest::nameDiskDrive = [" + parameterNameFileModel + "]");
            log.info("Stage01MassiveEvaluationRandomForest::executeEvaluationRandomForest::nameFileTraining = [" + parameterNameFileTraining + "]");

            RandomForest objectAlgorithm = new RandomForest();
            objectAlgorithm = (RandomForest)weka.core.SerializationHelper.read(parameterNameFileModel);

            log.info("Stage01MassiveEvaluationRandomForest::executeEvaluationRandomForest::objectAlgorithm.getBatchSize = " + objectAlgorithm.getBatchSize());

            DataSource objectDataSource = new DataSource(parameterNameFileTraining);
            Instances instancesOrigin = objectDataSource.getDataSet();
            instancesOrigin.setClassIndex(9);

            log.info("Stage01MassiveEvaluationRandomForest::executeEvaluationRandomForest::instancesOrigin.size() = [" + instancesOrigin.size() + "]");
            log.info("Stage01MassiveEvaluationRandomForest::executeEvaluationRandomForest::instancesOrigin.numAttributes() = [" + instancesOrigin.numAttributes() + "]");

            String[] optionsAttribute = new String[2];
            optionsAttribute[0] = "-R";
            optionsAttribute[1] = "1";

            log.info("Stage01MassiveEvaluationRandomForest::executeEvaluationRandomForest::optionsAttribute[0] = [" + optionsAttribute[0] + "]");
            log.info("Stage01MassiveEvaluationRandomForest::executeEvaluationRandomForest::optionsAttribute[1] = [" + optionsAttribute[1] + "]");

            Remove remove = new Remove();
            remove.setOptions(optionsAttribute);
            remove.setInputFormat(instancesOrigin);

            Instances instancesFilter = Filter.useFilter(instancesOrigin, remove);
            instancesFilter.setClassIndex(8);

            log.info("Stage01MassiveEvaluationRandomForest::executeEvaluationRandomForest::instancesFilter.size() = [" + instancesFilter.size() + "]");
            log.info("Stage01MassiveEvaluationRandomForest::executeEvaluationRandomForest::instancesFilter.numAttributes() = [" + instancesFilter.numAttributes() + "]");

            String[] optionsAlgorithm = new String[14];
            optionsAlgorithm[0] = "-P";
            optionsAlgorithm[1] = "100";
            optionsAlgorithm[2] = "-I";
            optionsAlgorithm[3] = "100";
            optionsAlgorithm[4] = "-num-slots";
            optionsAlgorithm[5] = "1";
            optionsAlgorithm[6] = "-K";
            optionsAlgorithm[7] = "0";
            optionsAlgorithm[8] = "-M";
            optionsAlgorithm[9] = "1.0";
            optionsAlgorithm[10] = "-V";
            optionsAlgorithm[11] = "0.001";
            optionsAlgorithm[12] = "-S";
            optionsAlgorithm[13] = "1";

            for (int i = 0; i < optionsAlgorithm.length; i ++) {
                log.info("Stage01MassiveEvaluationRandomForest::executeTrainingRandomForest::optionsAlgorithm[" + i + "] = [" + optionsAlgorithm[i] + "]");
            }

            Evaluation evaluationTest = new Evaluation(instancesFilter);

            double[] evaluationValue = evaluationTest.evaluateModel(objectAlgorithm, instancesFilter);

            log.info("Stage01MassiveEvaluationRandomForest::executeEvaluationRandomForest::evaluationValue.length = [" + evaluationValue.length + "]");

            //String actual = "?";
            double prediction = 0.0;

            for (int i = 0; i < instancesFilter.numInstances(); i++) {
                //actual = instancesFilter.instance(i).toString(15);
                prediction = Double.valueOf(instancesFilter.instance(i).classAttribute().value((int) evaluationValue[i])).doubleValue();

                //log.info("Stage01MassiveEvaluationRandomForest::executeEvaluationRandomForest::instancia:: número = " + (i+1) + "; actual = " + actual + "; prediction = " + prediction);
                log.info("Stage01MassiveEvaluationRandomForest::executeEvaluationRandomForest::instancia:: número = " + (i+1) + "; prediction = " + prediction);

                return prediction == 1.0 ? "SI" : "NO";

            }

            log.info("Stage01MassiveEvaluationRandomForest::executeEvaluationRandomForest::FIN");

        } catch(Exception e) {
            log.info("Stage01MassiveEvaluationRandomForest::executeEvaluationRandomForest::Exception" + e.getMessage());
        }

        return "NO";
    }
}
