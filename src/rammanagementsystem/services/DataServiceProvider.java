package rammanagementsystem.services;

import java.io.IOException;

import rammanagementsystem.exceptions.InvalidFormatException;
import rammanagementsystem.exceptions.InvalidItemException;
import rammanagementsystem.utilities.FSUtils;
import ui.Router;

/**
 * DataServiceProvider
 */
public final class DataServiceProvider {
    public static final RamItemDataService ramItemsDataService;

    /**
     * Static block to load data from disk if available, otherwise create a new
     */
    static {
        RamItemDataService loadedDS = null;

        try {
            loadedDS = FSUtils.loadItemDataService();
        } catch (InvalidFormatException e) {
            Router.setMotd("Invalid data found, please input a new file. " + e.getMessage());
        } catch (InvalidItemException e) {
            Router.setMotd("Invalid item found in collection, please input a new file." + e.getMessage());
        } catch (IOException e) {
            Router.setMotd("Can not read data from disk for file: " + e.getMessage());
        }

        if (loadedDS == null) {
            ramItemsDataService = new RamItemDataService();
            try {
                FSUtils.saveItemDataService(ramItemsDataService);
            } catch (IOException e) {
                Router.setMotd("Can not save data to disk for file: " + e.getMessage());
            }
            Router.setMotd("No data found, created a new data file.");
        } else {
            ramItemsDataService = loadedDS;
        }
    }
}