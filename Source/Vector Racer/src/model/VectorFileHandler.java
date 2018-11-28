package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class VectorFileHandler {

    private ModelAPI model;
    private List<String> tokenList;

    public VectorFileHandler(ModelAPI model){
        this.model = model;
    }

    public void loadFromFile(String filename){
        String currentLine = null;
        String opcode = null;
        List<String> operands = null;

        try{
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null){
                if(currentLine.length() > 0){
//                    opcode = getOpcode(currentLine);
//                    operands = getOperands(currentLine);
//                    loadObjectIntoModel(opcode, operands);
                }
            }


            br.close();
        }catch(IOException iox){
            System.out.println("Error reading from file '"+filename+"': ");
            iox.printStackTrace();
        }
    }

}
