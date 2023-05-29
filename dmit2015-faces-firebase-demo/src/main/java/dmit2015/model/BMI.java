package dmit2015.model;

// no lombok as to practice vanilla java
/**
 * This class is use to calculate a person's body mass index (BMI) and their BMI Category.
 * @author Tom P
 * @version 2023.05.29
 */
public class BMI {

    private int weight; // pounds (lbs)

    private int height; // inches

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BMI() {
    }

    public BMI(int weight, int height){
        this.weight = weight;
        this.height = height;
    }

    /**
     * Calculate the body mass index (BMI) using the weight and height of the person.
     * The BMI of a person is calculated using the formula: BMI = 700 * weight / (height * height)
     * where weight is in pounds and height is in inches.
     * @return the body mass index (BMI) value of the person
     */
    public double bmi(){
        return (703 * getWeight()) / Math.pow(getHeight(), 2);
    }

    /**
     * Determines the BMI Category of the person using their BMI value and
     * comparing it against the following table.
     * <table>
     * <thead>
     * <tr>
     * <th>BMI Range</th>
     * <th>BMI Category</th>
     * </tr>
     * </thead>
     * <tbody>
     * <tr>
     * <td>< 18.5</td>
     * <td>underweight</td>
     * </tr>
     * <tr>
     * <td>>= 18.5 and < 25</td>
     * <td>normal</td>
     * </tr>
     * <tr>
     * <td>>= 25 and < 30</td>
     * <td>overweight</td>
     3.
     http://localhost:8080/javawockee-web/sections/dmit2015/winter2016/exe...
     of 4 1/13/2016 5:21 PM
     * </tr>
     * <tr>
     * <td>>= 30</td>
     * <td>obese</td>
     * </tr>
     * </tbody>
     * </table>
     *
     * @return one of following: underweight, normal, overweight, obese.
     */
    public String bmiCategory(){
        String category = "N/A";
        if(bmi() < 18.5){
            category = "underweight";
        }
        else if(bmi() >= 18.5 && bmi() <= 24.9){
            category = "normal";
        }
        else if(bmi() >= 25 && bmi() <= 29.9){
            category = "overweight";
        }
        else{
            category = "obese";
        }
        return category;
    }

}
