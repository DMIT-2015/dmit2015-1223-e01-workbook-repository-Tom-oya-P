package dmit2015.model;

/**
 * This class is use to calculate a person's chinese zodiac animal using their birth year.
 * @author Tom P
 * @version 2023.05.29
 */
public class ChineseZodiac {

    /**
     * This method calculates a person's chinese zodiac animal based on a given birth year.
     */
    public static String animal(int birthYear) {
        String animal = "N/A";
        int offset = (birthYear - 1900) % 12;
        switch (offset) {
            case 0:
                animal = "Rat";
                break;
            case 1:
                animal = "Ox";
                break;
            case 2:
                animal = "Tiger";
                break;
            case 3:
                animal = "Rabbit";
                break;
            case 4:
                animal = "Dragon";
                break;
            case 5:
                animal = "Snake";
                break;
            case 6:
                animal = "Horse";
                break;
            case 7:
                animal = "Goat";
                break;
            case 8:
                animal = "Monkey";
                break;
            case 9:
                animal = "Rooster";
                break;
            case 10:
                animal = "Dog";
                break;
            case 11:
                animal = "Pig";
                break;
        }
        return animal;
    }
}
