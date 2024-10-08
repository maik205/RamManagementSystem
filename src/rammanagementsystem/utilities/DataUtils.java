package rammanagementsystem.utilities;

import rammanagementsystem.models.RamItem;
import rammanagementsystem.models.enums.RamType;
import rammanagementsystem.services.DataServiceProvider;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.time.YearMonth;

public final class DataUtils {
    public final static List<RamItem> generateRandomRamItems(int count) {
        List<RamItem> ramItems = new ArrayList<>();
        Random random = new Random();

        String[] brands = { "Corsair", "G.Skill", "Kingston", "Samsung", "Crucial", "Adata" };

        for (int i = 0; i < count; i++) {
            // Generate random values

            RamType ramType = RamType.values()[random.nextInt(RamType.values().length)];
            String productId = DataServiceProvider.ramItemsDataService.provideNewUID(ramType);
            int busSpeed = getRandomInRange(2400, 5600, random); // Bus speed between 2400MHz and 5600MHz
            String brand = brands[random.nextInt(brands.length)];
            int quantity = getRandomInRange(1, 17, random); // Random quantity between 1 and 16
            YearMonth productionMonthYear = YearMonth.of(getRandomInRange(2015, 2025, random),
                    getRandomInRange(1, 12, random));
            boolean isActive = random.nextBoolean();

            // Create new RamItem
            RamItem ramItem = new RamItem(productId, ramType, busSpeed, brand, quantity, productionMonthYear, isActive);
            ramItems.add(ramItem);
        }

        return ramItems;
    }

    public static int getRandomInRange(int min, int max, Random random) {
        return random.nextInt((max - min) + 1) + min;
    }
}
