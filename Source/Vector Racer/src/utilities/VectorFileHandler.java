package utilities;

import model.ModelAPI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class VectorFileHandler {

    private ModelAPI model;
    private List<String> tokenList;

    public VectorFileHandler(ModelAPI model){
        this.model = model;
    }

    /**
     * Loads model objects from file into current model
     * @param filename
     */
    public void loadFromFile(String filename){
        String currentLine = null;
        String opcode = null;
        List<String> operands = null;

        try{
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null){
                if(currentLine.length() > 0){
                    opcode = getOpcode(currentLine);
                    operands = getOperands(currentLine);
                    loadObjectIntoModel(opcode, operands);
                }
            }


            br.close();
        }catch(IOException iox){
            System.out.println("Error reading from file '"+filename+"': ");
            iox.printStackTrace();
        }
    }

    /**
     * Saves current model objects to file
     * @param filename the filename to save model objects to
     */
    public void saveToFile(String filename){
        String outputToFile;




        // todo


    }

    /**
     * Loads an individual object into the current model
     * @param opcode dictates what kind of object data will be loaded
     * @param operands object metadata
     */
    public void loadObjectIntoModel(String opcode, List<String> operands){
        opcode = opcode.toLowerCase();

        for(String currentOperand: operands){
            currentOperand = currentOperand.toLowerCase();  // todo unsure if this works?
        }

        try {
            if (opcode.equals("blank")) {
                // blank line/object, do nothing
            } else if (opcode.equals("dimensions")) {
                // set dimensions of the racetrack in rows and columns
                model.setRacetrackDimensions(Integer.parseInt(operands.get(0)), Integer.parseInt(operands.get(1)));
            } else if (opcode.equals("tile")) {
                // abstract tile object
                if(operands.get(0).equals("sand")) {
                    // add sand tile to model
                    model.addSandTile(Integer.parseInt(operands.get(1)), Integer.parseInt(operands.get(2)));
                }else if(operands.get(0).equals("ice")) {
                    // add ice tile to model
                    model.addSandTile(Integer.parseInt(operands.get(1)), Integer.parseInt(operands.get(2)));
                }else if(operands.get(0).equals("air")){
                    // add "regular" air tile to model
                    model.addAirTile(Integer.parseInt(operands.get(1)), Integer.parseInt(operands.get(2)));
                }else{
                    // assume "regular" air tile and add to model
                    model.addAirTile(Integer.parseInt(operands.get(1)), Integer.parseInt(operands.get(2)));
                }
            } else {
                // invalid opcode, do nothing
                System.out.println("Unrecognised opcode");
            }
        }catch(NullPointerException ex){
        System.out.println("NullPointerException setting racetrack dimensions from file");
        System.out.println("Operand(s) may be missing");
        ex.printStackTrace();
    }

    }

    /**
     * Gets an opcode from an input line
     * @param line the String representing the current file line to be read
     * @return the opcode from this line as a String
     */
    public String getOpcode(String line){
        try {
            return getTokens(line).get(0);
        }catch(NullPointerException e){
            return "blank";
        }
    }

    /**
     * Gets a series of operands from an input line
     * @param line the String representing the current file line to be read
     * @return the operand(s) from this line as a String
     */
    public List<String> getOperands(String line){
        List<String> operands = getTokens(line);

        try{
            operands.remove(0);
            try{
                return operands;
            }catch(NullPointerException e){
                operands.add("blank");
                return operands;
            }
        }catch(NullPointerException e){
            operands.add("blank");
            return operands;
        }
    }

    /**
     * Separates individual elements of a line
     * @param line the String representing the line to be separated
     * @return a list of Strings formed from the original String being separated
     */
    public List<String> getTokens(String line){
        tokenList = new ArrayList<>();

        StringTokenizer tokens = new StringTokenizer(line);
        while(tokens.hasMoreTokens()){
            tokenList.add(tokens.nextToken());
        }

        return tokenList;
    }

}
