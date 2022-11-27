
package abd;


import java.util.Random;

/**
 *
 * @author abd
 */
public class Neuron {
    
    String[] inputs = new String[5]; // takes inputs from file.
    double[] weights = generateWeights(); // each neuron has own weights. between 0-1.
    int expected; // expected value for that neuron
    
    
    
  
    public Neuron(String[] inputs){
        this.inputs = inputs; //takes inputs.
    }
    
    
    public double[] generateWeights(){
        double[] weights = new double[4];
        Random r = new Random();
        for (int i = 0; i < weights.length; i++) {
            weights[i] = 1 * r.nextDouble();
        }
        return weights;
    }
    
    public double calculateOutput(){ // one flower's output
        double output = 0;
        for(int i = 0; i<4; i++){
            output += Double.parseDouble(inputs[i])/10 * weights[i]; //calculates output from file's inputs.
            
        }
        return output;
    }
    public String getTarget(){ // one flower's target
        return inputs[4];
    }
    
    }


