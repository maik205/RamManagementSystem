package rammanagementsystem.utilities;

import rammanagementsystem.exceptions.InvalidFormatException;
import rammanagementsystem.exceptions.InvalidItemException;
import rammanagementsystem.services.DataService;
import rammanagementsystem.services.RamItemDataService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import rammanagementsystem.interfaces.Queriable;
import rammanagementsystem.models.RamItem;

public final class FSUtils {

    /**
     * @param <E>  Type of the data stored in the DataService, data must implement
     *             the Queriable interface.
     * @param path Path to the file to be loaded.
     * @return DataService object loaded from the file.
     * @throws FileNotFoundException  If the file is not found.
     * @throws IOException            If there is an error reading the file.
     * @throws InvalidFormatException If the file is not in the correct format or
     *                                can't be parsed by the DataService.
     */
    public static <E extends Queriable> DataService<E> loadDataService(String path)
            throws FileNotFoundException, IOException, InvalidFormatException, InvalidItemException {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        DataService<E> dataService;
        try {
            Object o = ois.readObject();
            if (!(o instanceof DataService<?>))
                throw new InvalidFormatException();
            dataService = (DataService<E>) o;
            if (!dataService.selfTest())
                throw new InvalidItemException();
        } catch (ClassNotFoundException e) {
            throw new InvalidFormatException();
        } catch (ClassCastException e) {
            throw new InvalidFormatException();
        } finally {
            ois.close();
            fis.close();
        }
        return dataService;
    }

    /**
     * 
     * @param <E>         Type of the data stored in the DataService, data must
     *                    implement the Queriable interface.
     * @param path        Path to the file to be saved.
     * @param dataService DataService object to be saved.
     * @throws IOException If there is an error writing to the file.
     */
    public static <E extends Queriable> void saveDataService(String path, RamItemDataService dataService)
            throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(dataService);
        oos.close();
        fos.close();
    }

    public static void saveItemDataService(RamItemDataService dataService) throws IOException {
        saveDataService(Constants.RAM_ITEM_FILE_NAME, dataService);
    }

    public static RamItemDataService loadItemDataService()
            throws FileNotFoundException, IOException, InvalidFormatException, InvalidItemException {
        return (RamItemDataService) FSUtils.<RamItem>loadDataService(Constants.RAM_ITEM_FILE_NAME);
    }
}
