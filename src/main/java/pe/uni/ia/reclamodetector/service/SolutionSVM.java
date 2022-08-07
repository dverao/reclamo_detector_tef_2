package pe.uni.ia.reclamodetector.service;

import org.apache.log4j.Logger;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.LibSVM;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class SolutionSVM implements ISolutionMethod {
	final static Logger log = Logger.getLogger(SolutionBayesNet.class);
    @Override
    public String evaluate(String parameterNameFileTraining, String parameterNameFileModel) {
        try {

            log.info("Stage01MassiveEvaluationLibSVM::executeEvaluationLibSVM::INICIO");

            log.info("Stage01MassiveEvaluationLibSVM::executeEvaluationLibSVM::nameDiskDrive = [" + parameterNameFileModel + "]");
            log.info("Stage01MassiveEvaluationLibSVM::executeEvaluationLibSVM::nameFileTraining = [" + parameterNameFileTraining + "]");

            LibSVM objectAlgorithm = new LibSVM();
            objectAlgorithm = (LibSVM)weka.core.SerializationHelper.read(parameterNameFileModel);

            log.info("Stage01MassiveEvaluationLibSVM::executeEvaluationLibSVM::objectAlgorithm.getBatchSize = " + objectAlgorithm.getBatchSize());

            DataSource objectDataSource = new DataSource(parameterNameFileTraining);
            Instances instancesOrigin = objectDataSource.getDataSet();
            instancesOrigin.setClassIndex(9);

            log.info("Stage01MassiveEvaluationLibSVM::executeEvaluationLibSVM::instancesOrigin.size() = [" + instancesOrigin.size() + "]");
            log.info("Stage01MassiveEvaluationLibSVM::executeEvaluationLibSVM::instancesOrigin.numAttributes() = [" + instancesOrigin.numAttributes() + "]");

            String[] optionsAttribute = new String[2];
            optionsAttribute[0] = "-R";
            optionsAttribute[1] = "1";

            log.info("Stage01MassiveEvaluationLibSVM::executeEvaluationLibSVM::optionsAttribute[0] = [" + optionsAttribute[0] + "]");
            log.info("Stage01MassiveEvaluationLibSVM::executeEvaluationLibSVM::optionsAttribute[1] = [" + optionsAttribute[1] + "]");

            Remove remove = new Remove();
            remove.setOptions(optionsAttribute);
            remove.setInputFormat(instancesOrigin);

            Instances instancesFilter = Filter.useFilter(instancesOrigin, remove);
            instancesFilter.setClassIndex(8);

            log.info("Stage01MassiveEvaluationLibSVM::executeEvaluationLibSVM::instancesFilter.size() = [" + instancesFilter.size() + "]");
            log.info("Stage01MassiveEvaluationLibSVM::executeEvaluationLibSVM::instancesFilter.numAttributes() = [" + instancesFilter.numAttributes() + "]");

            String[] optionsAlgorithm = new String[22];
            optionsAlgorithm[0] = "-S";
            optionsAlgorithm[1] = "0";
            optionsAlgorithm[2] = "-K";
            optionsAlgorithm[3] = "2";
            optionsAlgorithm[4] = "-D";
            optionsAlgorithm[5] = "3";
            optionsAlgorithm[6] = "-G";
            optionsAlgorithm[7] = "0.0";
            optionsAlgorithm[8] = "-R";
            optionsAlgorithm[9] = "0.0";
            optionsAlgorithm[10] = "-N";
            optionsAlgorithm[11] = "0.5";
            optionsAlgorithm[12] = "-M";
            optionsAlgorithm[13] = "40.0";
            optionsAlgorithm[14] = "-C";
            optionsAlgorithm[15] = "1.0";
            optionsAlgorithm[16] = "-E";
            optionsAlgorithm[17] = "0.001";
            optionsAlgorithm[18] = "-P";
            optionsAlgorithm[19] = "0.1";
            optionsAlgorithm[20] = "-seed";
            optionsAlgorithm[21] = "1";

            for (int i = 0; i < optionsAlgorithm.length; i ++) {
                log.info("Stage01MassiveEvaluationLibSVM::executeEvaluationLibSVM::optionsAlgorithm[" + i + "] = [" + optionsAlgorithm[i] + "]");
            }

            Evaluation evaluationTest = new Evaluation(instancesFilter);

            double[] evaluationValue = evaluationTest.evaluateModel(objectAlgorithm, instancesFilter);

            log.info("Stage01MassiveEvaluationLibSVM::executeEvaluationLibSVM::evaluationValue.length = [" + evaluationValue.length + "]");

            //String actual = "?";
            double prediction = 0.0;

            for (int i = 0; i < instancesFilter.numInstances(); i++) {
                //actual = instancesFilter.instance(i).toString(15);
                prediction = Double.valueOf(instancesFilter.instance(i).classAttribute().value((int) evaluationValue[i])).doubleValue();

                //log.info("Stage01MassiveEvaluationLibSVM::executeEvaluationLibSVM::instancia:: número = " + (i+1) + "; actual = " + actual + "; prediction = " + prediction);
                log.info("Stage01MassiveEvaluationLibSVM::executeEvaluationLibSVM::instancia:: número = " + (i+1) + "; prediction = " + prediction);
                
                return prediction == 1.0 ? "SI" : "NO";
            }

            log.info("Stage01MassiveEvaluationLibSVM::executeEvaluationLibSVM::FIN");

        } catch(Exception e) {
            log.info("Stage01MassiveEvaluationLibSVM::executeEvaluationLibSVM::Exception" + e.getMessage());
        }

        return "NO";
    }
}
