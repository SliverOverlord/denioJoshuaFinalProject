/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectv3;


/**
 *
 * @author Turtle
 */
public class Question {
    private int keyID;
    private int typeID;
    private String questionStr;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String solutionStr;
    
    Question(int tmpKey, int tmpType, String tmpQuestion, String tmpOpt1,String tmpOpt2,
            String tmpOpt3, String tmpOpt4, String tmpSolution){
        keyID = tmpKey;
        typeID = tmpType;
        questionStr = tmpQuestion;
        option1 = tmpOpt1;
        option2 = tmpOpt2;
        option3 = tmpOpt3;
        option4 = tmpOpt4;
        solutionStr = tmpSolution;
    }
    public String toString(){
        return "keyID: "+Integer.toString(keyID)+" "+
                "type: "+typeID+'\n'+"Question:"+questionStr+'\n'+
                "option1: "+option1+'\n'+"option2: "+option2+'\n'+
                "option3: "+option3+'\n'+"option4: "+option4+'\n'+
                "Solution: "+solutionStr;
    }

    /**
     * @return the keyID
     */
    public int getKeyID() {
        return keyID;
    }

    /**
     * @param keyID the keyID to set
     */
    public void setKeyID(int keyID) {
        this.keyID = keyID;
    }

    /**
     * @return the typeID
     */
    public int getTypeID() {
        return typeID;
    }

    /**
     * @param typeID the typeID to set
     */
    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    /**
     * @return the questionStr
     */
    public String getQuestionStr() {
        return questionStr;
    }

    /**
     * @param questionStr the questionStr to set
     */
    public void setQuestionStr(String questionStr) {
        this.questionStr = questionStr;
    }

    /**
     * @return the option1
     */
    public String getOption1() {
        return option1;
    }

    /**
     * @param option1 the option1 to set
     */
    public void setOption1(String option1) {
        this.option1 = option1;
    }

    /**
     * @return the option2
     */
    public String getOption2() {
        return option2;
    }

    /**
     * @param option2 the option2 to set
     */
    public void setOption2(String option2) {
        this.option2 = option2;
    }

    /**
     * @return the option3
     */
    public String getOption3() {
        return option3;
    }

    /**
     * @param option3 the option3 to set
     */
    public void setOption3(String option3) {
        this.option3 = option3;
    }

    /**
     * @return the option4
     */
    public String getOption4() {
        return option4;
    }

    /**
     * @param option4 the option4 to set
     */
    public void setOption4(String option4) {
        this.option4 = option4;
    }

    /**
     * @return the solutionStr
     */
    public String getSolutionStr() {
        return solutionStr;
    }

    /**
     * @param solutionStr the solutionStr to set
     */
    public void setSolutionStr(String solutionStr) {
        this.solutionStr = solutionStr;
    }


    
}
