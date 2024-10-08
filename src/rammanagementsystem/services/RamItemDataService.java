package rammanagementsystem.services;

import java.io.IOException;
import java.util.Collection;
import java.util.Random;

import rammanagementsystem.models.RamItem;
import rammanagementsystem.models.enums.RamType;
import rammanagementsystem.exceptions.InvalidItemException;
import rammanagementsystem.utilities.FSUtils;
import ui.Router;

public class RamItemDataService extends DataService<RamItem> {

    public RamItemDataService(Collection<RamItem> data) throws InvalidItemException {
        super(data);
    }

    public RamItemDataService() {
    }

    @Override
    public void addItem(RamItem product) throws InvalidItemException {
        if (this.containsKey(product.getId()))
            throw new InvalidItemException("Item already exists");
        super.addItem(product);
        save();
    }

    public void setActive(String id, boolean active) {
        this.get(id).setActive(active);
        save();
    }

    @Override
    public RamItem remove(Object key) {
        this.get(key).setActive(false);
        save();
        return this.get(key);
    }

    public String provideNewUID(RamType type) {
        String res = String.format("RAM%s_%d", type, this.size());
        return res;
    }

    private void save() {
        try {
            FSUtils.saveItemDataService(this);
        } catch (IOException e) {
            Router.setMotd("There was an error saving the data to disk: "
                    + e.getMessage() +
                    " Your data may be lost.");
        }
    }

}
