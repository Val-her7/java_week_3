public class Grader {
    public char convertPointsToGrade(int points){
        if (points < 0){
            throw new IllegalArgumentException("Points cannot be negatives");
        }
        else if(points < 50){
            return 'I';
        }
        else if(points < 70){
            return 'S';
        }
        else if(points < 80){
            return 'B';
        }
        else if(points < 90){
            return 'T';
        }
        else{
            return 'E';
        }
    }
}
