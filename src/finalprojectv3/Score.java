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
public class Score {
    private String firstN;
    private String lastN;
    private String stateH;
    private String numSelected;
    private String scoreSubmitted;
    Score(String tmpFN, String tmpLN, String tmpState, String tmpNum, String tmpScore){
        firstN = tmpFN;
        lastN = tmpLN;
        stateH = tmpState;
        numSelected = tmpNum;
        scoreSubmitted = tmpScore;
    }

    /**
     * @return the firstN
     */
    public String getFirstN() {
        return firstN;
    }

    /**
     * @param firstN the firstN to set
     */
    public void setFirstN(String firstN) {
        this.firstN = firstN;
    }

    /**
     * @return the lastN
     */
    public String getLastN() {
        return lastN;
    }

    /**
     * @param lastN the lastN to set
     */
    public void setLastN(String lastN) {
        this.lastN = lastN;
    }

    /**
     * @return the stateH
     */
    public String getStateH() {
        return stateH;
    }

    /**
     * @param stateH the stateH to set
     */
    public void setStateH(String stateH) {
        this.stateH = stateH;
    }

    /**
     * @return the numSelected
     */
    public String getNumSelected() {
        return numSelected;
    }

    /**
     * @param numSelected the numSelected to set
     */
    public void setNumSelected(String numSelected) {
        this.numSelected = numSelected;
    }

    /**
     * @return the scoreSubmitted
     */
    public String getScoreSubmitted() {
        return scoreSubmitted;
    }

    /**
     * @param scoreSubmitted the scoreSubmitted to set
     */
    public void setScoreSubmitted(String scoreSubmitted) {
        this.scoreSubmitted = scoreSubmitted;
    }
}
