import java.util.*;

public class OptimalScoreInGame{
    public static void main(String[] args){

        Scanner inputReader = new Scanner(System.in);

        System.out.println("\nPlease enter the NUMBER of items (integers).\n");
        int numOfItems = inputReader.nextInt();

        System.out.println("\nPlease enter the LIST of items (integers), separated by commas.\n");
        String listOfItemsAsString = inputReader.next();
        inputReader.close();

        System.out.print("\n");

        String[] listOfItems = listOfItemsAsString.split(",");
        String[][] myArray = new String[numOfItems][numOfItems];

        if(listOfItems.length != numOfItems){

            System.out.println("The NUMBER of items you provided does not match the length of the LIST you provided.\n");

            System.exit(0);

        }

        if(numOfItems > 1){

            crossOutTrivialCells(myArray);

        }

        baseCase(myArray, listOfItems);

        if(numOfItems > 1){

            myDynamicProgrammingAlgorithm(myArray, listOfItems);

        }

        //Print chart
        for(int j=0; j<numOfItems; j++){

            for(int k=0; k<numOfItems; k++){

                System.out.print(String.format("%20s", myArray[j][k]));

            }

            System.out.print("\n");

        }

        System.out.print("\n");

    }

    public static void crossOutTrivialCells(String [][] myArray){

        for(int j = 1; j < myArray.length; j++){

            for(int k = 0; k < j; k++){

                myArray[j][k] = "N/A";

            }
        }
    }

    public static void baseCase(String[][] myArray, String[] list){

        int k = 0;

        for(int j = 0; j < myArray.length; j++, k++){

            myArray[j][k] = list[j] + " F or L";

            

            }
        }
    
    public static void myDynamicProgrammingAlgorithm(String[][] myArray, String[] list){

        int currentScore;
        int totalAmount;
        int startj;
        int startk = 0;
        
        while (myArray[0][myArray.length-1] == null){

            startj = 0;
            int startjj = startj;
            int startkk = startk;
            int update = 0;

            for(; startkk < myArray.length-1; startjj++){
                currentScore = 0;
                totalAmount = 0;
                update = 0;

                String startjjstartk = myArray[startjj][startkk].replaceAll("F","").replace("L","").replace("or","");
                int startjjstartkk = Integer.parseInt(startjjstartk.trim());
                String startjjP1startkP1 = myArray[startjj+1][startkk+1].replace("F","").replace("L","").replace("or","");
                int startjjP1startkkP1 = Integer.parseInt(startjjP1startkP1.trim());

                if(startjjstartkk < startjjP1startkkP1){
                    currentScore = Integer.parseInt(list[startkk+1]);

                    for(int i = startjj; i<=startkk; i++){
                        totalAmount += Integer.parseInt(list[i]);
                    }

                    totalAmount = totalAmount - startjjstartkk;
                    update = totalAmount + currentScore;
                    myArray[startjj][startkk+1] = Integer.toString(update) + " L";
                }

                else if(startjjP1startkkP1 < startjjstartkk){
                    currentScore = Integer.parseInt(list[startjj]);

                    for(int i = startjj+1; i<=startkk+1;i++ ){
                        totalAmount += Integer.parseInt(list[i]);
                    }

                    totalAmount = totalAmount - startjjP1startkkP1;
                    update = totalAmount + currentScore;
                    myArray[startjj][startkk+1] = Integer.toString(update) + " F";
                }

                else if(startjjP1startkkP1 == startjjstartkk){
                    currentScore = Integer.parseInt(list[startjj]);

                    for(int i = startjj+1; i<=startkk+1;i++ ){
                        totalAmount += Integer.parseInt(list[i]);
                    }

                    totalAmount = totalAmount - startjjP1startkkP1;
                    update = totalAmount + currentScore;
                    myArray[startjj][startkk+1] = Integer.toString(update) + " F or L";
                }

                startkk++;
            }

        startk++;

    }
    }
}