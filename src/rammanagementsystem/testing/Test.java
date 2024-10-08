package rammanagementsystem.testing;

public abstract class Test {

    /**
     * This method should return the name of the test.
     * 
     * @return String - the name of the test.
     */
    protected abstract String testName();

    /**
     * This method should contain the test logic and return true if the test is
     * successful, false otherwise.
     * 
     * @return boolean - true if the test is successful, false otherwise.
     */
    protected abstract boolean doTest();

    /**
     * This method is used to perform the test and print the result.
     */
    public void test() {
        System.out.println("Performing test...");
        System.out.print("Result: ");
        if (doTest()) {
            System.out.print("Success \n");
        } else {
            System.out.println("Failed.");
        }
    }
}
