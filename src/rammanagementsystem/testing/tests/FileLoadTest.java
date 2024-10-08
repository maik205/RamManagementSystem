package rammanagementsystem.testing.tests;

import rammanagementsystem.testing.Test;

public class FileLoadTest extends Test {

    @Override
    public boolean doTest() {
        //TODO: get test data from file
        return true;
        // try {
        // String[] expectedKeys = { "C001", "C002" };
        // String file = FSUtils.readTextFile(Constants.CATEGORY_FILE_NAME);
        // String[][] res = FSUtils.splitFields(file, 2);
        // // Map<String, Category> categories = new HashMap<>();
        // for (String[] cat : res) {
        // categories.put(cat[0],
        // new Category(cat[0], cat[1]));
        // }
        // System.out.println(categories.keySet());

        // for (String key : expectedKeys) {
        // if (!categories.keySet().contains(key))
        // return false;
        // }
        // return true;
        // } catch (Exception e) {
        // e.printStackTrace();
        // return false;
        // }
    }

    @Override
    protected String testName() {
        return "File reading test";
    }

}
