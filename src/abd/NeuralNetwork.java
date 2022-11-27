
package pkg abd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class NeuralNetwork {

    public static void main(String[] args) {
        String [][] data = readFile(); // creates data matrix from file.
        double count = 0; // accuracy count
        
        
        for(int i = 0; i<data.length; i++){ //for each flower
            // creates 3 neuron for that flower's input with diffierent weights.
            Neuron n1 = new Neuron(data[i]); 
            Neuron n2 = new Neuron(data[i]);
            Neuron n3 = new Neuron(data[i]);
            Neuron biggestNeuron = findBiggestOutput(n1, n2, n3); //finds the neuron that has the biggest output
            Neuron expectedNeuron = null; // expected neuron.
            
            if(n1.getTarget().equals("Iris-setosa") ){
                n1.expected = 1;
                expectedNeuron = n1;
                n2.expected = 0;
                n3.expected = 0;
               
                
            }
            else if(n2.getTarget().equals("Iris-versicolor") ){
                n1.expected = 0;
                n2.expected = 1;
                expectedNeuron = n2;
                n3.expected = 0;
                
                
            }
            else if(n3.getTarget().equals( "Iris-virginica")){
                n1.expected = 0;
                n2.expected = 0;
                n3.expected = 1;
                expectedNeuron = n3;
                
                
            }
            
            System.out.println();
            System.out.println("--------BEFORE TRAINING---------------");
            System.out.println(n1.calculateOutput() + " target: " + n1.getTarget()+ " expected: " + n1.expected );
            System.out.println(n2.calculateOutput() + " target: " +n2.getTarget()+ " expected: " + n2.expected);
            System.out.println(n3.calculateOutput() +" target: " + n3.getTarget()+ " expected: " + n3.expected);
     
            
            
            for(int y=0; y<20; y++){ 
                trainNeuron(0.025, biggestNeuron, expectedNeuron);
                
            }
     
            
            
            if(biggestNeuron == expectedNeuron){ // accuracy 
                count+=1;
            }
            
            System.out.println();
            System.out.println("----------AFTER TRAINING-------------");
            System.out.println(n1.calculateOutput() + " target: " + n1.getTarget()+ " expected: " + n1.expected );
            System.out.println(n2.calculateOutput() + " target: " +n2.getTarget()+ " expected: " + n2.expected);
            System.out.println(n3.calculateOutput() +" target: " + n3.getTarget()+ " expected: " + n3.expected);
            
            

            
        }
        System.out.printf(String.format("Accuracy: %.2f",(count/150)*100)); // prints accuracy
    }
    
    
    
    
    
    
    // read file method.
    public static String [][] readFile(){
        String [][] data = new String[150][5];
        int i= 0;
        BufferedReader reader;
            try {
		reader = new BufferedReader(new FileReader(
					"/Users/abd/Desktop/data/iris.data"));
		String line = reader.readLine();
		while (line != null) {
                    
                    data[i] = line.split(",");
                    line = reader.readLine();
                    i++;
		}
                reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return data;    
    }
    
    // find the neuron that has the biggest output
    public static Neuron findBiggestOutput(Neuron n1, Neuron n2, Neuron n3){
        Neuron biggest;
        
        if(n1.calculateOutput() > n2.calculateOutput() && n1.calculateOutput() > n3.calculateOutput()){
            biggest = n1;
        }
        else if(n2.calculateOutput() > n1.calculateOutput() && n2.calculateOutput() > n3.calculateOutput()){
            biggest = n2;
        }
        else{
            biggest = n3;
        }
        return biggest;
    }
    
    // calculates new weights according to instraction.
    public static void trainNeuron(double LEARNING_RATE, Neuron biggest, Neuron expected){
        for(int j= 0; j<4; j++){
                     biggest.weights[j] = biggest.weights[j] - Double.parseDouble(biggest.inputs[j]) * LEARNING_RATE;
                     expected.weights[j] = expected.weights[j] + Double.parseDouble(expected.inputs[j]) * LEARNING_RATE;
                }
        
    }
   
    
}
