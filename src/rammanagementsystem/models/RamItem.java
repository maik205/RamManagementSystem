
package rammanagementsystem.models;

import rammanagementsystem.interfaces.Queriable;
import rammanagementsystem.models.enums.RamType;

import java.io.Serializable;
import java.time.YearMonth;

/**
 * @author ASUS
 */
public class RamItem implements Queriable, Serializable, Cloneable {
    private String productId;
    private RamType type;
    private int busSpeed;
    private String brand;
    private int quantity;
    private YearMonth productionMonthYear;
    private boolean isActive;

    /**
     * 
     * @param productId           the product id
     * @param type                the type of ram
     * @param busSpeed            the bus speed in mhz
     * @param brand               the brand of the ram
     * @param quantity            the quantity of the ram
     * @param productionMonthYear the production month year in YearMonth format
     * @param isActive            if the item is active
     */
    public RamItem(String productId, RamType type, int busSpeed, String brand, int quantity,
            YearMonth productionMonthYear,
            boolean isActive) {
        this.productId = productId;
        this.type = type;
        this.busSpeed = busSpeed;
        this.brand = brand;
        this.quantity = quantity;
        this.productionMonthYear = productionMonthYear;
        this.isActive = isActive;
    }

    public RamItem() {
    }

    @Override
    /**
     * gets the product id
     */
    public String getId() {
        return this.productId;
    }

    /**
     * gets the string for querying the object
     */
    @Override
    public String getQueryString() {
        return "RamItem [productId=" + productId
                + ", type=" + type +
                ", busSpeed=" + busSpeed +
                ", brand=" + brand
                + ", quantity=" + quantity +
                ", productionMonthYear=" + productionMonthYear
                + ", isActive=" + isActive
                + "]";
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %s", productId, type, busSpeed, brand, quantity,
                productionMonthYear);
    }

    /**
     * sets the product id
     * 
     * @param productId the product id to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * gets the product id
     * 
     * @return productId
     */
    public RamType getType() {
        return type;
    }

    /**
     * sets the product type
     * 
     * @param type the product type to set
     */
    public void setType(RamType type) {
        this.type = type;
    }

    /**
     * gets the bus speed
     * 
     * @return busSpeed in mhz
     */
    public int getBusSpeed() {
        return busSpeed;
    }

    /**
     * sets the bus speed
     * 
     * @param busSpeed the bus speed to set
     */
    public void setBusSpeed(int busSpeed) {
        this.busSpeed = busSpeed;
    }

    /**
     * gets the brand
     * 
     * @return
     */
    public String getBrand() {
        return brand;
    }

    /**
     * sets the brand
     * 
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * gets the quantity
     * 
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * sets the quantity
     * 
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * gets the production month year
     * 
     * @return productionMonthYear in YearMonth format
     */
    public YearMonth getProductionMonthYear() {
        return productionMonthYear;
    }

    /**
     * sets the production month year
     * 
     * @param productionMonthYear
     */
    public void setProductionMonthYear(YearMonth productionMonthYear) {
        this.productionMonthYear = productionMonthYear;
    }

    /**
     * checks if the item is active
     * 
     * @return
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * sets the item to active or inactive
     * inactive items are not displayed
     * 
     * @param isActive
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

}
